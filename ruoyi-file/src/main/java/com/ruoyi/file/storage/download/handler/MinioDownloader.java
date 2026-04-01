package com.ruoyi.file.storage.download.handler;

import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.file.storage.config.MinioConfig;
import com.ruoyi.file.storage.download.Downloader;
import com.ruoyi.file.storage.download.domain.DownloadFile;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.errors.MinioException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * minio 下载器
 */
@Slf4j
@Component
public class MinioDownloader extends Downloader {

    @Autowired
    private MinioConfig minioConfig;
    @Autowired
    private MinioClient minioClient;

    @Override
    public InputStream getInputStream(DownloadFile downloadFile) {
        try {
            if (downloadFile.getRange() != null) {
                return minioClient.getObject(GetObjectArgs.builder()
                        .bucket(minioConfig.getBucketName())
                        .object(downloadFile.getFileUrl())
                        .offset(downloadFile.getRange().getStart())
                        .length((long) downloadFile.getRange().getLength())
                        .build());
            }
            return minioClient.getObject(GetObjectArgs.builder()
                    .bucket(minioConfig.getBucketName())
                    .object(downloadFile.getFileUrl())
                    .build());
        } catch (MinioException e) {
            log.error("MinIO 下载文件失败：{}", e.getMessage(), e);
            throw new BaseException("从 MinIO 获取文件失败：" + e.getMessage());
        } catch (IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            log.error("下载文件出现 IO 或安全异常：{}", e.getMessage(), e);
            throw new BaseException("下载文件出现异常：" + e.getMessage());
        }
    }

}
