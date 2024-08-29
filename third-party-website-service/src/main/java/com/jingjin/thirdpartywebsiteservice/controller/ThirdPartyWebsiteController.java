package com.jingjin.thirdpartywebsiteservice.controller;

import com.jingjin.common.result.ErrorCode;
import com.jingjin.common.result.ResultUtil;
import com.jingjin.model.thirdPartyWebsite.po.ThirdPartyWebsite;
import com.jingjin.model.thirdPartyWebsite.vo.ThirdPartyWebsiteSimpleVO;
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
import java.util.List;

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
    public BaseResult<List<ThirdPartyWebsiteSimpleVO>> getThirdPartyWebsiteDetail(){
        List<ThirdPartyWebsiteSimpleVO> websiteSimpleVOList = thirdPartyWebsiteService.getAll();
        return ResultUtil.success(websiteSimpleVOList);
    }

    /**
     * 按类型获取第三方网站列表
     *
     * @param Type 类型
     * @return {@link BaseResult}<{@link List}<{@link ThirdPartyWebsiteSimpleVO}>>
     */
    @Operation(summary = "按类型获取第三方网站列表")
    @GetMapping("/get/type/{type}")
    @Transactional
    public BaseResult<List<ThirdPartyWebsiteSimpleVO>> getThirdPartyWebsiteDetailByType(@PathVariable("type") Integer Type){
        List<ThirdPartyWebsiteSimpleVO> websiteSimpleVOList = thirdPartyWebsiteService.getByType(Type);
        return ResultUtil.success(websiteSimpleVOList);
    }

    /**
     * 查询第三方网站列表
     *
     * @param name 姓名
     * @return {@link BaseResult}<{@link List}<{@link ThirdPartyWebsiteSimpleVO}>>
     */
    @Operation(summary = "查询第三方网站列表")
    @GetMapping("/search")
    @Transactional
    public BaseResult<List<ThirdPartyWebsiteSimpleVO>> searchThirdPartyWebsite(String name){
        List<ThirdPartyWebsiteSimpleVO> websiteSimpleVOList = thirdPartyWebsiteService.search(name);
        return ResultUtil.success(websiteSimpleVOList);
    }

    /**
     * 获取第三方网站详细信息
     *
     * @param ThirdPartyWebsiteId 管理员网站id
     * @return {@link BaseResult}<{@link ThirdPartyWebsiteDetailVO}>
     */
    @Operation(summary = "获取第三方网站详情")
    @GetMapping("/get/{id}")
    @Transactional
    public BaseResult<ThirdPartyWebsiteDetailVO> getThirdPartyWebsiteDetail(@PathVariable("id") Integer ThirdPartyWebsiteId){
        ThirdPartyWebsiteDetailVO websiteDetailVO = thirdPartyWebsiteService.getDetailById(ThirdPartyWebsiteId);
        return ResultUtil.success(websiteDetailVO);
    }


    /**
     * 添加第三方网站
     *
     * @param addThirdPartyWebsiteDTO 添加管理员网站dto
     * @return {@link BaseResult}<{@link String}>
     */
    @Operation(summary = "上传新第三方网站")
    @PostMapping("/add")
    public BaseResult<String> addThirdPartyWebsite(AddThirdPartyWebsiteDTO addThirdPartyWebsiteDTO){
        Boolean isSuccess = thirdPartyWebsiteService.addThirdPartyWebsite(addThirdPartyWebsiteDTO);
        return isSuccess?ResultUtil.success("新增第三方网站成功"):ResultUtil.error(ErrorCode.SYSTEM_ERROR);
    }

    /**
     * 删除第三方网站
     *
     * @param ThirdPartyWebsiteId 管理员网站id
     * @return {@link BaseResult}<{@link String}>
     */
    @Operation(summary = "删除第三方网站")
    @DeleteMapping ("/delete/{id}")
    public BaseResult<String> deleteThirdPartyWebsite(@PathVariable("id") Integer ThirdPartyWebsiteId){
        Boolean isSuccess = thirdPartyWebsiteService.removeById(ThirdPartyWebsiteId);
        return isSuccess?ResultUtil.success("删除第三方网站成功"):ResultUtil.error(ErrorCode.SYSTEM_ERROR);
    }

    /**
     * 更新第三方网站
     *
     * @param updateThirdPartyWebsiteDTO 更新管理员网站dto
     * @return {@link BaseResult}<{@link String}>
     */
    @Operation(summary = "修改第三方网站")
    @PutMapping("/update")
    public BaseResult<String> updateThirdPartyWebsite(UpdateThirdPartyWebsiteDTO updateThirdPartyWebsiteDTO){
        Boolean isSuccess = thirdPartyWebsiteService.updateThirdPartyWebsite(updateThirdPartyWebsiteDTO);
        return isSuccess?ResultUtil.success("更新第三方网站成功"):ResultUtil.error(ErrorCode.SYSTEM_ERROR);
    }




}
