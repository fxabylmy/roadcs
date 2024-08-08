package com.jingjin.adminwebsiteservice.controller;

import com.jingjin.adminwebsiteservice.service.AdminWebsiteService;
import com.jingjin.common.result.BaseResult;
import com.jingjin.common.result.ResultUtil;
import com.jingjin.model.adminWebsite.dto.AddAdminWebsiteDTO;
import com.jingjin.model.adminWebsite.dto.AddFavoritesDTO;
import com.jingjin.model.adminWebsite.dto.DeleteFavoritesDTO;
import com.jingjin.model.adminWebsite.dto.UpdateAdminWebsiteDTO;
import com.jingjin.model.adminWebsite.vo.AdminWebsiteDetailVO;
import com.jingjin.model.adminWebsite.vo.AdminWebsitePageVO;
import com.jingjin.model.user.dto.UserLoginRequest;
import com.jingjin.model.user.vo.UserDetailVO;
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
import java.util.Map;

/**
 * 站内网站控制器
 *
 * @author fxab
 * @date 2024/08/07
 */
@RefreshScope // 自动配置更新
@RestController
@RequestMapping("/")
@Slf4j
@Tag(name = "站内网站接口文档", description = "站内网站模块接口文档")
public class AdminWebsiteController {

    /**
     * 站内网站服务
     */
    @Resource
    private AdminWebsiteService adminWebsiteService;

    /**
     * 获取站内网站详细信息列表
     *
     * @return {@link BaseResult}<{@link AdminWebsitePageVO}>
     */
    @Operation(summary = "获取站内网站列表")
    @GetMapping("/get/list")
    @Transactional
    public BaseResult<AdminWebsitePageVO> getAdminWebsiteDetail(){
        //todo 获取站内网站列表
        return null;
    }

    /**
     * 获取站内网站详细信息
     *
     * @param adminWebsiteId 管理员网站id
     * @return {@link BaseResult}<{@link AdminWebsiteDetailVO}>
     */
    @Operation(summary = "获取站内网站详情")
    @GetMapping("/get/{id}")
    @Transactional
    public BaseResult<AdminWebsiteDetailVO> getAdminWebsiteDetail(@PathVariable("id") Integer adminWebsiteId){
        //todo 获取站内网站详情
        return null;
    }

    /**
     * 获取站内网站logo
     *
     * @return {@link ResponseEntity}<{@link ByteArrayResource}>
     * @throws IOException ioexception
     */
/*    @Operation(summary = "获取网站图标")
    @GetMapping("/logo/get/{id}")
    @Transactional
    public ResponseEntity<ByteArrayResource> getAdminWebsiteLogo(@PathVariable("id") Integer adminWebsiteId) throws IOException {
        //todo 获取网站图标
        return null;
    }*/

    /**
     * 添加站内网站
     *
     * @param addAdminWebsiteDTO 添加管理员网站dto
     * @return {@link BaseResult}<{@link String}>
     */
    @Operation(summary = "上传新站内网站")
    @PostMapping("/add")
    public BaseResult<String> addAdminWebsite(AddAdminWebsiteDTO addAdminWebsiteDTO){
        //todo 添加新站内网站
        return null;
    }

    /**
     * 删除站内网站
     *
     * @param adminWebsiteId 管理员网站id
     * @return {@link BaseResult}<{@link String}>
     */
    @Operation(summary = "删除站内网站")
    @DeleteMapping ("/delete/{id}")
    public BaseResult<String> deleteAdminWebsite(@PathVariable("id") Integer adminWebsiteId){
        //todo 删除站内网站
        return null;
    }

    /**
     * 更新站内网站
     *
     * @param updateAdminWebsiteDTO 更新管理员网站dto
     * @return {@link BaseResult}<{@link String}>
     */
    @Operation(summary = "修改站内网站")
    @PostMapping("/update")
    public BaseResult<String> updateAdminWebsite(UpdateAdminWebsiteDTO updateAdminWebsiteDTO){
        //todo 修改站内网站
        return null;
    }

    /**
     * 添加站内网站收藏
     *
     * @param addFavoritesDTO 添加收藏夹dto
     * @return {@link BaseResult}<{@link String}>
     */
    @Operation(summary = "新增站内网站收藏关系")
    @PostMapping("/favorites/add")
    public BaseResult<String> addFavorites(@RequestBody AddFavoritesDTO addFavoritesDTO){
        //todo 从token获取用户id
        //todo 新增站内网站收藏关系
        return null;
    }

    /**
     * 删除站内网站收藏
     *
     * @param deleteFavoritesDTO 删除收藏夹dto
     * @return {@link BaseResult}<{@link String}>
     */
    @Operation(summary = "删除站内网站收藏关系")
    @PostMapping("/favorites/delete")
    public BaseResult<String> deleteFavorites(@RequestBody DeleteFavoritesDTO deleteFavoritesDTO){
        //todo 从token获取用户id
        //todo 删除站内网站收藏关系
        return null;
    }


}
