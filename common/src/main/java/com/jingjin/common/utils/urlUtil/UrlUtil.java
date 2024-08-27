package com.jingjin.common.utils.urlUtil;

import com.jingjin.common.exception.BusinessException;
import com.jingjin.common.result.ErrorCode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;

public class UrlUtil {

    public static String urlToBase64(String imageUrl) {
        String base64Image = "默认图片的base64";
        try {
            URL url = new URL(imageUrl);
            InputStream is = url.openStream();
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            is.close();
            byte[] imageBytes = os.toByteArray();
            base64Image = Base64.getEncoder().encodeToString(imageBytes);
            return base64Image;
        }catch (Exception e){
            //todo 图片获取失败时转而返回默认图片
            throw new BusinessException(ErrorCode.PICTURE_MISSION);
        }
    }

    public static String urlToThumbBase64(String imageUrl) {
        String base64Image = "默认图片的base64";
        imageUrl = imageUrl+"?x-oss-process=image/resize,m_fixed,h_60,w_60";
        try {
            URL url = new URL(imageUrl);
            InputStream is = url.openStream();
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            is.close();
            byte[] imageBytes = os.toByteArray();
            base64Image = Base64.getEncoder().encodeToString(imageBytes);
            return base64Image;
        }catch (Exception e){
            //todo 图片获取失败时转而返回默认图片
            throw new BusinessException(ErrorCode.PICTURE_MISSION);
        }
    }
}
