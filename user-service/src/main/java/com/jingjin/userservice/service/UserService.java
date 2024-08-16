package com.jingjin.userservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jingjin.model.user.dto.user.UploadPasswordDTO;
import com.jingjin.model.user.dto.user.UserRegisterDTO;
import com.jingjin.model.user.po.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;


public interface UserService extends IService<User> {

    Map<String, Object> userLogin(String account, String password);

    Map<String, Object> refreshToken(String refreshToken);

    Boolean logout(String userId);

    Boolean userRegister(UserRegisterDTO userRegisterDTO);


    Boolean uploadAvatar(MultipartFile avatar, String userId) throws IOException;

    byte[] getAvatar(String userId) throws IOException;

    Boolean sendEmail(String email);

    Boolean confirmEmail(String email, String emailCode);

    Boolean passwordReWrite(UploadPasswordDTO uploadPasswordDTO);
}
