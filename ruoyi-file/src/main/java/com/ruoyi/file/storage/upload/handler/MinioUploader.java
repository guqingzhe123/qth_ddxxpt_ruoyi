package com.ruoyi.file.storage.upload.handler;

import com.alibaba.fastjson2.JSON;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.sign.Md5Utils;
import com.ruoyi.file.storage.config.MinioConfig;
import com.ruoyi.file.storage.contants.StorageContants;
import com.ruoyi.file.storage.enums.StorageTypeEnum;
import com.ruoyi.file.storage.enums.UploadFileStatusEnum;
import com.ruoyi.file.storage.upload.Uploader;
import com.ruoyi.file.storage.upload.domain.UploadFile;
import com.ruoyi.file.storage.upload.domain.UploadFileInfo;
import com.ruoyi.file.storage.upload.domain.UploadFileResult;
import com.ruoyi.file.storage.upload.param.MultipartFileParam;
import com.ruoyi.file.storage.util.FileStorageUtils;
import com.ruoyi.file.storage.util.FileUtil;
import io.minio.*;
import io.minio.messages.Part;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class MinioUploader extends Uploader {

    @Autowired
    private CustomMinioClient customMinioClient;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private MinioConfig minioConfig;
    @Autowired
    private MinioClient minioClient;

    @Override
    public void cancelUpload(UploadFile uploadFile) {
    }

    @Override
    protected void doUploadFileChunk(MultipartFileParam multipartFileParam, UploadFile uploadFile) {
        Multimap<String, String> headers = HashMultimap.create();
        InputStream inputStream = multipartFileParam.getUploadInputStream();
        try {
            String fileHash = Md5Utils.hash(uploadFile.getFilename() + uploadFile.getIdentifier());
            String cacheKey = StorageContants.UPLOAD_PARTRESULT_LOCK_KEY + SecurityUtils.getUserId() + Constants.COLON + fileHash;
            UploadFileInfo uploadFileInfo = JSON.parseObject((String) redisCache.getCacheObject(cacheKey), UploadFileInfo.class);
            String fileUrl = multipartFileParam.getFileUrl();

            // 取消急速上传/断点续传功能：如果是第一个分片，清理旧缓存并创建新的上传会话
            if (uploadFile.getChunkNumber() == 1) {
                if (uploadFileInfo != null) {
                    // 删除可能存在的旧上传会话缓存
                    redisCache.deleteObject(cacheKey);
                }
                uploadFileInfo = createNewMultipartUpload(fileUrl, headers, cacheKey);
            }

            // 如果缓存中没有 uploadFileInfo（非第一个分片但缓存失效），抛出异常
            if (uploadFileInfo == null) {
                throw new BaseException("上传会话已失效，请重新上传");
            }

            customMinioClient.uploadPartAsync(
                    uploadFileInfo.getBucketName(),
                    null,
                    uploadFileInfo.getKey(),
                    inputStream,
                    multipartFileParam.getSize(),
                    uploadFileInfo.getUploadId(),
                    uploadFile.getChunkNumber(),
                    headers,
                    null
            ).get();
        } catch (Exception e) {
            log.error("分片上传失败", e);
            throw new BaseException("上传失败");
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    private UploadFileInfo createNewMultipartUpload(String fileUrl, Multimap<String, String> headers, String cacheKey) throws Exception {
        CreateMultipartUploadResponse response = customMinioClient.createMultipartUploadAsync(
                minioConfig.getBucketName(),
                null,
                fileUrl,
                headers,
                null
        ).get();

        UploadFileInfo uploadFileInfo = new UploadFileInfo();
        uploadFileInfo.setBucketName(minioConfig.getBucketName());
        uploadFileInfo.setKey(fileUrl);
        uploadFileInfo.setUploadId(response.result().uploadId());
        // 缓存过期时间延长至24小时，避免大文件上传会话超时
        redisCache.setCacheObject(cacheKey, JSON.toJSONString(uploadFileInfo), 1440, TimeUnit.MINUTES);

        return uploadFileInfo;
    }

//    @Override
//    protected void doUploadFileChunk(MultipartFileParam multipartFileParam, UploadFile uploadFile) {
//        Multimap<String, String> headers = HashMultimap.create();
//        InputStream inputStream = multipartFileParam.getUploadInputStream();
//        try {
//            String fileHash = Md5Utils.hash(uploadFile.getFilename() + uploadFile.getIdentifier());
//            String cacheKey = StorageContants.UPLOAD_PARTRESULT_LOCK_KEY + SecurityUtils.getUserId() + Constants.COLON + fileHash;
//            UploadFileInfo uploadFileInfo = JSON.parseObject((String) redisCache.getCacheObject(cacheKey), UploadFileInfo.class);
//            String fileUrl = multipartFileParam.getFileUrl();
//            if (uploadFileInfo == null) {
//                CreateMultipartUploadResponse response = customMinioClient.createMultipartUploadAsync(
//                        minioConfig.getBucketName(),
//                        null,
//                        fileUrl,
//                        headers,
//                        null
//                ).get();
//                uploadFileInfo = new UploadFileInfo();
//                uploadFileInfo.setBucketName(minioConfig.getBucketName());
//                uploadFileInfo.setKey(fileUrl);
//                uploadFileInfo.setUploadId(response.result().uploadId());
//                redisCache.setCacheObject(cacheKey, JSON.toJSONString(uploadFileInfo));
//            }
//            customMinioClient.uploadPartAsync(
//                    uploadFileInfo.getBucketName(),
//                    null,
//                    uploadFileInfo.getKey(),
//                    inputStream,
//                    multipartFileParam.getSize(),
//                    uploadFileInfo.getUploadId(),
//                    uploadFile.getChunkNumber(),
//                    headers,
//                    null
//            ).get();
//        } catch (Exception e) {
//            log.error("分片上传失败", e);
//            throw new BaseException("上传失败");
//        } finally {
//            IOUtils.closeQuietly(inputStream);
//        }
//    }

    @Override
    protected UploadFileResult organizationalResults(MultipartFileParam multipartFileParam, UploadFile uploadFile) {
        String userId = SecurityUtils.getUserId();
        String fileHash = Md5Utils.hash(uploadFile.getFilename() + uploadFile.getIdentifier());
        String cacheKey = StorageContants.UPLOAD_PARTRESULT_LOCK_KEY + userId + Constants.COLON + fileHash;
        String currentChunkKey = StorageContants.UPLOAD_CHUNKNUM_LOCK_KEY + userId + Constants.COLON + fileHash;
        try {
            UploadFileResult uploadFileResult = new UploadFileResult();
            UploadFileInfo uploadFileInfo = JSON.parseObject((String) redisCache.getCacheObject(cacheKey), UploadFileInfo.class);
                
            // 如果缓存中没有 uploadFileInfo，根据情况处理
            if (uploadFileInfo == null) {
                if (uploadFile.getTotalChunks() == 1) {
                    // 单分片文件：创建新的 uploadFileInfo，不需要 uploadId
                    uploadFileInfo = new UploadFileInfo();
                    uploadFileInfo.setBucketName(minioConfig.getBucketName());
                    // 格式化文件路径，统一使用正斜杠，兼容各操作系统
                    String fileUrl = formatFilePath(multipartFileParam.getFileUrl());
                    uploadFileInfo.setKey(fileUrl);
                    // 缓存过期时间延长至 24 小时
                    redisCache.setCacheObject(cacheKey, JSON.toJSONString(uploadFileInfo), 1440, TimeUnit.MINUTES);
                } else {
                    // 多分片文件：缓存缺失，抛出异常（不应该发生，因为第一个分片应该已创建）
                    throw new BaseException("上传会话已失效，请重新上传");
                }
            }
                
            uploadFileResult.setFileUrl(uploadFileInfo.getKey());
            uploadFileResult.setFileName(multipartFileParam.getFileName());
            uploadFileResult.setExtendName(StringUtils.isBlank(multipartFileParam.getExtendName()) ? "" : multipartFileParam.getExtendName().toString());
            long size = uploadFile.getTotalSize();
            if (uploadFile.getTotalChunks() == 1) {
                size = multipartFileParam.getSize();
            }
            uploadFileResult.setFileSize(size);
            uploadFileResult.setStorageType(StorageTypeEnum.MINIO.getCode());
            uploadFileResult.setIdentifier(uploadFile.getIdentifier());
            uploadFileResult.setSort(uploadFile.getSort());
            UploadFileStatusEnum status = UploadFileStatusEnum.UNCOMPLATE;
            if (uploadFile.getChunkNumber() == uploadFile.getTotalChunks()) {
                log.info("分片上传完成，处理合并...");
                // 传入 multipartFileParam，在 completeMultipartUpload 中重新获取输入流并计算 MD5
                String md5 = completeMultipartUpload(uploadFile, multipartFileParam, size);
                uploadFileResult.setMd5(md5);
                status = UploadFileStatusEnum.SUCCESS;
            }
            uploadFileResult.setStatus(status);
            // 图片属性
            if (FileStorageUtils.isImageFile(uploadFileResult.getExtendName())) {
                InputStream in = null;
                try {
                    in = minioClient.getObject(GetObjectArgs.builder().bucket(minioConfig.getBucketName()).object(uploadFileResult.getFileUrl()).build());
                    BufferedImage src = ImageIO.read(in);
                    uploadFileResult.setBufferedImage(src);
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                } finally {
                    IOUtils.closeQuietly(in);
                }
            }
            return uploadFileResult;
        } catch (Exception e) {
            redisCache.deleteObject(currentChunkKey);
            redisCache.deleteObject(cacheKey);
            log.error("# organizationalResults 分片上传失败！", e);
            throw new BaseException("上传失败");
        }
    }

    /**
     * 完成分片上传
     * @param uploadFile 上传文件信息
     * @param multipartFileParam 文件参数
     * @param fileSize 文件大小
     * @return 文件 MD5 值
     */
    private String completeMultipartUpload(UploadFile uploadFile, MultipartFileParam multipartFileParam, long fileSize) {
        String fileHash = Md5Utils.hash(uploadFile.getFilename() + uploadFile.getIdentifier());
        String cacheKey = StorageContants.UPLOAD_PARTRESULT_LOCK_KEY + SecurityUtils.getUserId() + Constants.COLON + fileHash;
        UploadFileInfo uploadFileInfo = JSON.parseObject((String) redisCache.getCacheObject(cacheKey), UploadFileInfo.class);
            
        // 单分片文件：直接上传到 MinIO
        if (uploadFileInfo == null || StringUtils.isBlank(uploadFileInfo.getUploadId())) {
            InputStream inputStream = null;
            try {
                log.info("单分片文件，直接上传到 MinIO...");
                // 重新获取输入流，避免流被重复使用导致的 EOF 错误
                inputStream = multipartFileParam.getUploadInputStream();
                    
                // 使用 PutObjectArgs 直接上传文件到 MinIO
                PutObjectArgs args = PutObjectArgs.builder()
                        .bucket(minioConfig.getBucketName())
                        .object(uploadFileInfo.getKey())
                        .stream(inputStream, fileSize, -1)
                        .contentType(getContentType(uploadFile.getExtendName()))
                        .build();
                    
                minioClient.putObject(args);
                log.info("单分片文件已成功上传到 MinIO: {}", uploadFileInfo.getKey());
                    
                // 计算 MD5（使用 multipartFile 的 byte 数组）
                String md5 = FileUtil.getFileMd5(multipartFileParam.getUploadBytes());
                    
                // 清理缓存
                redisCache.deleteObject(cacheKey);
                return md5;
            } catch (Exception e) {
                log.error("单分片文件上传 MinIO 失败", e);
                throw new BaseException("文件上传失败：" + e.getMessage());
            } finally {
                IOUtils.closeQuietly(inputStream);
            }
        }
            
        // 多分片文件：执行合并操作
        Multimap<String, String> headers = HashMultimap.create();
    
        try {
            ListPartsResponse listPartsResponse = customMinioClient.listPartsAsync(
                    minioConfig.getBucketName(),
                    null,
                    uploadFileInfo.getKey(),
                    uploadFile.getTotalChunks() + 10,
                    0,
                    uploadFileInfo.getUploadId(),
                    headers,
                    null
            ).get();
    
            Part[] parts = listPartsResponse.result().partList().toArray(new Part[]{});
    
            customMinioClient.completeMultipartUploadAsync(
                    minioConfig.getBucketName(),
                    null,
                    uploadFileInfo.getKey(),
                    uploadFileInfo.getUploadId(),
                    parts,
                    headers,
                    null
            ).get();
    
            // 合并完成后清理缓存
            redisCache.deleteObject(cacheKey);
            return null; // 多分片文件的 MD5 在前面已经计算过
        } catch (Exception e) {
            if (e.getMessage().contains("NoSuchUpload")) {
                log.error("上传 ID 已失效，无法完成合并：{}", e.getMessage());
                // 清理失效缓存
                redisCache.deleteObject(cacheKey);
                throw new BaseException("上传会话已失效，请重新上传");
            } else {
                log.error("合并分片失败", e);
                throw new RuntimeException(e);
            }
        }
    }
        
    /**
     * 格式化文件路径，统一使用正斜杠，兼容各操作系统
     * @param filePath 原始文件路径
     * @return 格式化后的路径
     */
    private String formatFilePath(String filePath) {
        if (filePath == null) {
            return null;
        }
        // 将反斜杠替换为正斜杠
        return filePath.replace("\\", "/");
    }
        
    /**
     * 根据文件扩展名获取 Content-Type
     * @param extendName 文件扩展名
     * @return Content-Type
     */
    private String getContentType(String extendName) {
        if (StringUtils.isBlank(extendName)) {
            return "application/octet-stream";
        }
        switch (extendName.toLowerCase()) {
            case "xls":
                return "application/vnd.ms-excel";
            case "xlsx":
                return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
            case "doc":
                return "application/msword";
            case "docx":
                return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
            case "pdf":
                return "application/pdf";
            case "txt":
                return "text/plain";
            case "jpg":
            case "jpeg":
                return "image/jpeg";
            case "png":
                return "image/png";
            case "gif":
                return "image/gif";
            case "bmp":
                return "image/bmp";
            case "csv":
                return "text/csv";
            default:
                return "application/octet-stream";
        }
    }
}

