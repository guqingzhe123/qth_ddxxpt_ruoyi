package com.ruoyi.file.storage.download.handler;

import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.file.storage.download.Downloader;
import com.ruoyi.file.storage.download.domain.DownloadFile;
import com.ruoyi.file.storage.util.FileStorageUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * 本地存储下载器
 */
@Slf4j
@Component
public class LocalStorageDownloader extends Downloader {

    @Override
    public InputStream getInputStream(DownloadFile downloadFile) {
        File file = new File(FileStorageUtils.getDataPath() + downloadFile.getFileUrl());
        if (!file.exists()) {
            log.error("文件不存在：{}", file.getAbsolutePath());
            throw new BaseException("文件不存在");
        }
        
        InputStream inputStream = null;
        byte[] bytes = new byte[0];
        InputStream newInputStream = null;
        RandomAccessFile randowAccessFile = null;
        try {
            if (downloadFile.getRange() != null) {
                randowAccessFile = new RandomAccessFile(file, "r");
                randowAccessFile.seek(downloadFile.getRange().getStart());
                bytes = new byte[downloadFile.getRange().getLength()];
                randowAccessFile.read(bytes);
                newInputStream = new ByteArrayInputStream(bytes);
            } else {
                inputStream = new FileInputStream(file);
                newInputStream = IOUtils.toBufferedInputStream(inputStream);
            }
        } catch (IOException e) {
            log.error("读取本地文件失败：{}", e.getMessage(), e);
            throw new BaseException("读取本地文件失败：" + e.getMessage());
        } finally {
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(randowAccessFile);
        }
        return newInputStream;
    }
}
