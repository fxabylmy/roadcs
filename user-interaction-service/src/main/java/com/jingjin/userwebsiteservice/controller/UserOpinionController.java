package com.jingjin.userwebsiteservice.controller;

import com.jingjin.common.result.BaseResult;
import com.jingjin.model.userInteraction.dto.AddUserOpinionDTO;
import com.jingjin.model.userInteraction.dto.OpinionResponseDTO;
import com.jingjin.model.userInteraction.dto.UpdateUserOpinionDTO;
import com.jingjin.model.userInteraction.vo.BackUserOpinionPageVO;
import com.jingjin.model.userInteraction.vo.UserOpinionPageVO;
import com.jingjin.userwebsiteservice.service.UserOpinionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


/**
 * 用户意见控制器
 *
 * @author fxab
 * @date 2024/08/08
 */
@RefreshScope // 自动配置更新
@RestController
@RequestMapping("/opinion")
@Slf4j
@Tag(name = "用户意见接口文档", description = "用户意见模块接口文档")
public class UserOpinionController {

    /**
     * 用户意见服务
     */
    @Resource
    private UserOpinionService userOpinionService;

    /**
     * 添加用户意见
     *
     * @param addUserOpinionDTO 添加用户意见dto
     * @return {@link BaseResult}<{@link String}>
     */
    @Operation(summary = "新增用户意见")
    @PostMapping ("/add")
    @Transactional
    public BaseResult<String> addUserOpinion(AddUserOpinionDTO addUserOpinionDTO){
        //todo 从token获取当前用户id
        //todo 新增用户意见
        return null;
    }

    /**
     * 用户获取已提交意见
     *
     * @return {@link BaseResult}<{@link UserOpinionPageVO}>
     */
    @Operation(summary = "用户获取已提交意见")
    @GetMapping ("/get")
    @Transactional
    public BaseResult<UserOpinionPageVO> getUserOpinion(){
        //todo 从token获取当前用户id
        //todo 根据token获取当前用户意见
        return null;
    }

    /**
     * 后台获取用户意见
     *
     * @return {@link BaseResult}<{@link BackUserOpinionPageVO}>
     */
    @Operation(summary = "管理员获取用户意见")
    @GetMapping ("/back/get")
    @Transactional
    public BaseResult<BackUserOpinionPageVO> getBackUserOpinion(){
        //todo 获取全部的用户意见
        return null;
    }

    /**
     * 用户更新用户意见
     *
     * @param updateUserOpinionDTO 更新用户意见dto
     * @return {@link BaseResult}<{@link String}>
     */
    @Operation(summary = "用户修改用户意见")
    @PutMapping("/update")
    @Transactional
    public BaseResult<String> updateUserOpinion(UpdateUserOpinionDTO updateUserOpinionDTO){
        //todo 修改用户意见
        return null;
    }

    /**
     * 删除用户意见
     *
     * @param userOpinionId 用户意见id
     * @return {@link BaseResult}<{@link String}>
     */
    @Operation(summary = "删除用户意见")
    @DeleteMapping ("/delete/{id}")
    @Transactional
    public BaseResult<String> deleteUserOpinion(@PathVariable("id") Integer userOpinionId){
        //todo 删除用户意见
        return null;
    }

    /**
     * 删除用户意见
     *
     * @param opinionResponseDTO 意见回应dto
     * @return {@link BaseResult}<{@link String}>
     */
    @Operation(summary = "回应用户意见")
    @DeleteMapping ("/response")
    @Transactional
    public BaseResult<String> deleteUserOpinion(@RequestBody OpinionResponseDTO opinionResponseDTO){
        //todo 回应用户意见
        return null;
    }




}
