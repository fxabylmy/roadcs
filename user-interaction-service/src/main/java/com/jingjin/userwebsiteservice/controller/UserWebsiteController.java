package com.jingjin.userwebsiteservice.controller;

import com.jingjin.common.result.BaseResult;
import com.jingjin.common.utils.UserContext;
import com.jingjin.model.userInteraction.dto.AddUserWebsiteDTO;
import com.jingjin.model.userInteraction.dto.UpdateUserWebsiteDTO;
import com.jingjin.model.userInteraction.vo.UserWebsitePageVO;
import com.jingjin.userwebsiteservice.service.UserWebsiteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * 用户自定义网站控制器
 *
 * @author fxab
 * @date 2024/08/07
 */
@RefreshScope // 自动配置更新
@RestController
@RequestMapping("/website")
@Slf4j
@Tag(name = "用户自定义网站接口文档", description = "用户自定义网站模块接口文档")
public class UserWebsiteController {

    /**
     * 用户自定义网站服务
     */
    @Resource
    private UserWebsiteService userWebsiteService;

    /**
     * 根据token获取用户自定义网站列表
     *
     * @return {@link BaseResult}<{@link UserWebsitePageVO}>
     */
    @Operation(summary = "获取用户自定义网站列表")
    @GetMapping("/get/list")
    @Transactional
    public BaseResult<UserWebsitePageVO> getAdminWebsiteDetail(){
        // 从token获取当前用户id
        String userId = UserContext.getUserId();
        //todo 获取用户自定义网站列表
        return null;
    }


    /**
     * 获取用户自定义网站logo
     *
     * @param userWebsiteId 用户网站id
     * @return {@link ResponseEntity}<{@link ByteArrayResource}>
     * @throws IOException ioexception
     */
   /* @Operation(summary = "获取网站图标")
    @GetMapping("/logo/get/{id}")
    @Transactional
    public ResponseEntity<ByteArrayResource> getUserWebsiteLogo(@PathVariable("id") Integer userWebsiteId) throws IOException {
        //todo 获取网站图标
        return null;
    }*/

    /**
     * 添加用户自定义网站
     *
     * @param addUserWebsiteDTO 添加用户网站dto
     * @return {@link BaseResult}<{@link String}>
     */
    @Operation(summary = "上传新用户自定义网站")
    @PostMapping("/add")
    public BaseResult<String> addUserWebsite(@RequestBody AddUserWebsiteDTO addUserWebsiteDTO){
        //todo 添加新用户自定义网站
        return null;
    }

    /**
     * 删除用户自定义网站
     *
     * @param userWebsiteId 用户网站id
     * @return {@link BaseResult}<{@link String}>
     */
    @Operation(summary = "删除用户自定义网站")
    @DeleteMapping ("/delete/{id}")
    public BaseResult<String> deleteUserWebsite(@PathVariable("id") Integer userWebsiteId){
        //todo 删除用户自定义网站
        return null;
    }

    /**
     * 更新用户自定义网站
     *
     * @param updateUserWebsiteDTO 更新用户网站dto
     * @return {@link BaseResult}<{@link String}>
     */
    @Operation(summary = "修改用户自定义网站")
    @PutMapping("/update")
    public BaseResult<String> updateAdminWebsite(UpdateUserWebsiteDTO updateUserWebsiteDTO){
        //todo 修改用户自定义网站
        return null;
    }


}
