package com.jingjin.userwebsiteservice.controller;

import com.jingjin.common.result.BaseResult;
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
 * 站外网站控制器
 *
 * @author fxab
 * @date 2024/08/07
 */
@RefreshScope // 自动配置更新
@RestController
@RequestMapping("/website")
@Slf4j
@Tag(name = "站外网站接口文档", description = "站外网站模块接口文档")
public class UserWebsiteController {

    /**
     * 站外网站服务
     */
    @Resource
    private UserWebsiteService userWebsiteService;

    /**
     * 根据token获取站外网站列表
     *
     * @return {@link BaseResult}<{@link UserWebsitePageVO}>
     */
    @Operation(summary = "获取站外网站列表")
    @GetMapping("/get/list")
    @Transactional
    public BaseResult<UserWebsitePageVO> getAdminWebsiteDetail(){
        //todo 从token获取当前用户id
        //todo 获取站外网站列表
        return null;
    }


    /**
     * 获取站外网站logo
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
     * 添加站外网站
     *
     * @param addUserWebsiteDTO 添加用户网站dto
     * @return {@link BaseResult}<{@link String}>
     */
    @Operation(summary = "上传新站外网站")
    @PostMapping("/add")
    public BaseResult<String> addUserWebsite(AddUserWebsiteDTO addUserWebsiteDTO){
        //todo 添加新站外网站
        return null;
    }

    /**
     * 删除站外网站
     *
     * @param userWebsiteId 用户网站id
     * @return {@link BaseResult}<{@link String}>
     */
    @Operation(summary = "删除站外网站")
    @DeleteMapping ("/delete/{id}")
    public BaseResult<String> deleteUserWebsite(@PathVariable("id") Integer userWebsiteId){
        //todo 删除站外网站
        return null;
    }

    /**
     * 更新站外网站
     *
     * @param updateUserWebsiteDTO 更新用户网站dto
     * @return {@link BaseResult}<{@link String}>
     */
    @Operation(summary = "修改站外网站")
    @PostMapping("/update")
    public BaseResult<String> updateAdminWebsite(UpdateUserWebsiteDTO updateUserWebsiteDTO){
        //todo 修改站外网站
        return null;
    }


}
