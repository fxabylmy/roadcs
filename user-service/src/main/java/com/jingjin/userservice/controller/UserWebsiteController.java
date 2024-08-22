package com.jingjin.userservice.controller;

import com.jingjin.common.exception.ThrowUtils;
import com.jingjin.common.result.BaseResult;
import com.jingjin.common.result.ErrorCode;
import com.jingjin.common.result.PageResponse;
import com.jingjin.common.result.ResultUtil;
import com.jingjin.common.utils.UserContext;
import com.jingjin.model.userInteraction.dto.AddUserWebsiteDTO;
import com.jingjin.model.userInteraction.dto.UpdateUserWebsiteDTO;
import com.jingjin.model.userInteraction.po.UserWebsite;
import com.jingjin.model.userInteraction.vo.UserWebsiteVO;
import com.jingjin.userservice.service.UserWebsiteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * 用户自定义网站控制器
 *
 * @author fxab
 * @date 2024/08/07
 */
@RefreshScope // 自动配置更新
@RestController
@RequestMapping("/interaction/website")
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
     * @return {@link BaseResult}<{@link PageResponse}<{@link UserWebsiteVO}>>
     */
    @Operation(summary = "获取用户自定义网站列表")
    @GetMapping("/get/list")
    @Transactional
    public BaseResult<PageResponse<UserWebsiteVO>> getAdminWebsiteDetail(@RequestParam(defaultValue = "1") int pageIndex,
                                                                         @RequestParam(defaultValue = "10") int pageSize){
        // 从token获取当前用户id
        String userId = UserContext.getUserId();
        PageResponse<UserWebsiteVO> userWebsiteVOPage = userWebsiteService.getPageByUserId(userId,pageIndex,pageSize);
        return ResultUtil.success(userWebsiteVOPage);
    }


    /**
     * 添加用户自定义网站
     *
     * @param addUserWebsiteDTO 添加用户网站dto
     * @return {@link BaseResult}<{@link String}>
     */
    @Operation(summary = "上传新用户自定义网站")
    @PostMapping("/add")
    public BaseResult<String> addUserWebsite(AddUserWebsiteDTO addUserWebsiteDTO){
        String userId = UserContext.getUserId();
        Boolean isSuccess = userWebsiteService.addUserWebsite(userId,addUserWebsiteDTO);
        return isSuccess? ResultUtil.success("新增自定义网站成功"):ResultUtil.error(ErrorCode.SYSTEM_ERROR);
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
        String userId = UserContext.getUserId();
        UserWebsite userWebsite = userWebsiteService.getById(updateUserWebsiteDTO.getId());
        ThrowUtils.throwIf(!userId.equals(userWebsite.getUserId()),ErrorCode.PRTMISSION_ERROR);
        Boolean isSuccess = userWebsiteService.updateUserWebsite(updateUserWebsiteDTO);
        return isSuccess?ResultUtil.success("更新自定义网站成功"):ResultUtil.error(ErrorCode.SYSTEM_ERROR);
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
        String userId = UserContext.getUserId();
        UserWebsite userWebsite = userWebsiteService.getById(userWebsiteId);
        ThrowUtils.throwIf(!userId.equals(userWebsite.getUserId()),ErrorCode.PRTMISSION_ERROR);
        Boolean isSuccess = userWebsiteService.removeById(userWebsiteId);
        return isSuccess?ResultUtil.success("删除自定义网站成功"):ResultUtil.error(ErrorCode.SYSTEM_ERROR);
    }




}
