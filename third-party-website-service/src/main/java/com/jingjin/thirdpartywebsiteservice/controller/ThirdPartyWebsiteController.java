package com.jingjin.thirdpartywebsiteservice.controller;

import com.jingjin.thirdpartywebsiteservice.service.ThirdPartyWebsiteService;
import com.jingjin.common.result.BaseResult;
import com.jingjin.model.thirdPartyWebsite.dto.AddThirdPartyWebsiteDTO;
import com.jingjin.model.thirdPartyWebsite.dto.AddFavoritesDTO;
import com.jingjin.model.thirdPartyWebsite.dto.DeleteFavoritesDTO;
import com.jingjin.model.thirdPartyWebsite.dto.UpdateThirdPartyWebsiteDTO;
import com.jingjin.model.thirdPartyWebsite.vo.ThirdPartyWebsiteDetailVO;
import com.jingjin.model.thirdPartyWebsite.vo.ThirdPartyWebsitePageVO;
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
 * 第三方网站控制器
 *
 * @author fxab
 * @date 2024/08/07
 */
@RefreshScope // 自动配置更新
@RestController
@RequestMapping("/")
@Slf4j
@Tag(name = "第三方网站接口文档", description = "第三方网站模块接口文档")
public class ThirdPartyWebsiteController {

    /**
     * 第三方网站服务
     */
    @Resource
    private ThirdPartyWebsiteService thirdPartyWebsiteService;

    /**
     * 获取第三方网站详细信息列表
     *
     * @return {@link BaseResult}<{@link ThirdPartyWebsitePageVO}>
     */
    @Operation(summary = "获取第三方网站列表")
    @GetMapping("/get/list")
    @Transactional
    public BaseResult<ThirdPartyWebsitePageVO> getAdminWebsiteDetail(){
        //todo 获取第三方网站列表
        return null;
    }

    /**
     * 获取第三方网站详细信息
     *
     * @param adminWebsiteId 管理员网站id
     * @return {@link BaseResult}<{@link ThirdPartyWebsiteDetailVO}>
     */
    @Operation(summary = "获取第三方网站详情")
    @GetMapping("/get/{id}")
    @Transactional
    public BaseResult<ThirdPartyWebsiteDetailVO> getAdminWebsiteDetail(@PathVariable("id") Integer adminWebsiteId){
        //todo 获取第三方网站详情
        return null;
    }

    /**
     * 获取第三方网站logo
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
     * 添加第三方网站
     *
     * @param addThirdPartyWebsiteDTO 添加管理员网站dto
     * @return {@link BaseResult}<{@link String}>
     */
    @Operation(summary = "上传新第三方网站")
    @PostMapping("/add")
    public BaseResult<String> addAdminWebsite(AddThirdPartyWebsiteDTO addThirdPartyWebsiteDTO){
        //todo 添加新第三方网站
        return null;
    }

    /**
     * 删除第三方网站
     *
     * @param adminWebsiteId 管理员网站id
     * @return {@link BaseResult}<{@link String}>
     */
    @Operation(summary = "删除第三方网站")
    @DeleteMapping ("/delete/{id}")
    public BaseResult<String> deleteAdminWebsite(@PathVariable("id") Integer adminWebsiteId){
        //todo 删除第三方网站
        return null;
    }

    /**
     * 更新第三方网站
     *
     * @param updateThirdPartyWebsiteDTO 更新管理员网站dto
     * @return {@link BaseResult}<{@link String}>
     */
    @Operation(summary = "修改第三方网站")
    @PostMapping("/update")
    public BaseResult<String> updateAdminWebsite(UpdateThirdPartyWebsiteDTO updateThirdPartyWebsiteDTO){
        //todo 修改第三方网站
        return null;
    }

    /**
     * 添加第三方网站收藏
     *
     * @param addFavoritesDTO 添加收藏夹dto
     * @return {@link BaseResult}<{@link String}>
     */
    @Operation(summary = "新增第三方网站收藏关系")
    @PostMapping("/favorites/add")
    public BaseResult<String> addFavorites(@RequestBody AddFavoritesDTO addFavoritesDTO){
        //todo 从token获取用户id
        //todo 新增第三方网站收藏关系
        return null;
    }

    /**
     * 删除第三方网站收藏
     *
     * @param deleteFavoritesDTO 删除收藏夹dto
     * @return {@link BaseResult}<{@link String}>
     */
    @Operation(summary = "删除第三方网站收藏关系")
    @PostMapping("/favorites/delete")
    public BaseResult<String> deleteFavorites(@RequestBody DeleteFavoritesDTO deleteFavoritesDTO){
        //todo 从token获取用户id
        //todo 删除第三方网站收藏关系
        return null;
    }


}
