package com.jingjin.userservice.util.upload;

import cn.hutool.core.util.IdUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.jingjin.common.exception.BusinessException;
import com.jingjin.common.result.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 图片上传阿里云OSS存储工具类
 *
 * @author fxab
 * @date 2024/04/24
 */
@Component
@RefreshScope
@Slf4j
public class UploadUtil {
    /**
     * 阿里云地址
     */
    @Value("${aliyun.oss.domain:''}")
    public String ALI_DOMAIN;

    /**
     * 地域节点
     */
    @Value("${aliyun.oss.endpoint:''}")
    String endpoint;

    /**
     * 访问密钥id
     */
    @Value("${aliyun.oss.access-key-id:''}")
    String accessKeyId;

    /**
     * 访问密钥secret
     */
    @Value("${aliyun.oss.access-key-secret:''}")
    String accessKeySecret;

    /**
     * 仓库
     */
    @Value("${aliyun.oss.warehouse:''}")
    String warehouse;

    /**
     * 上传img
     *
     * @param file 文件
     * @return {@link String}
     * @throws Exception 例外
     */
    public String uploadImg(MultipartFile file) {
        //判断文件是否为空
        if (file == null || file.isEmpty()){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"文件为空");
        }
        //生成的文件名
        String uuid = IdUtil.simpleUUID();
        String originalFilename = file.getOriginalFilename();
        String ext = "." + FilenameUtils.getExtension(originalFilename);
        String fileName = uuid + ext;
        //地域节点(开头需要http://)
        //OSS客户端对象
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            ossClient.putObject(
                    warehouse, //仓库名
                    fileName, //文件名
                    file.getInputStream()
            );
        } catch (IOException e) {
            throw new BusinessException(ErrorCode.PICTURE_MISSION);
        }
        ossClient.shutdown();
        return ALI_DOMAIN + fileName;
    }

}
