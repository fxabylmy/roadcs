package com.jingjin.userservice.controller;

import com.jingjin.common.exception.ThrowUtils;
import com.jingjin.common.result.BaseResult;
import com.jingjin.common.result.ErrorCode;
import com.jingjin.common.result.PageResponse;
import com.jingjin.common.result.ResultUtil;
import com.jingjin.common.utils.UserContext;
import com.jingjin.model.userInteraction.dto.AddUserMemoDTO;
import com.jingjin.model.userInteraction.dto.UpdateUserMemoDTO;
import com.jingjin.model.userInteraction.po.UserMemo;
import com.jingjin.model.userInteraction.vo.UserMemoVO;
import com.jingjin.userservice.service.UserMemoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * 用户备忘录控制器
 *
 * @author fxab
 * @date 2024/08/08
 */
@RefreshScope // 自动配置更新
@RestController
@RequestMapping("/interaction/memo")
@Slf4j
@Tag(name = "用户备忘录接口文档", description = "用户备忘录模块接口文档")
public class UserMemoController {

    /**
     * 用户备忘录服务
     */
    @Resource
    private UserMemoService userMemoService;

    /**
     * 添加用户备忘录
     *
     * @param addUserMemoDTO 添加用户备忘录dto
     * @return {@link BaseResult}<{@link String}>
     */
    @Operation(summary = "新增用户备忘录")
    @PostMapping ("/add")
    @Transactional
    public BaseResult<String> addUserMemo(AddUserMemoDTO addUserMemoDTO){
        // 从token获取当前用户id
        String userId = UserContext.getUserId();
        UserMemo userMemo = UserMemo.builder()
                .userId(userId)
                .title(addUserMemoDTO.getTitle())
                .content(addUserMemoDTO.getContent())
                .build();
        Boolean isSuccess = userMemoService.save(userMemo);
        return isSuccess? ResultUtil.success("新增备忘录成功"):ResultUtil.error(ErrorCode.SYSTEM_ERROR);
    }


    @Operation(summary = "获取用户备忘录")
    @GetMapping ("/get")
    @Transactional
    public BaseResult<PageResponse<UserMemoVO>> getUserMemo(@RequestParam(defaultValue = "1") int pageIndex,
                                                            @RequestParam(defaultValue = "10") int pageSize){
        // 从token获取当前用户id
        String userId = UserContext.getUserId();
        PageResponse<UserMemoVO> userMemoPageVO = userMemoService.getPageById(pageIndex,pageSize,userId);
        return ResultUtil.success(userMemoPageVO);
    }

    /**
     * 更新用户备忘录
     *
     * @param updateUserMemoDTO 更新用户备忘录dto
     * @return {@link BaseResult}<{@link String}>
     */
    @Operation(summary = "修改用户备忘录")
    @PutMapping("/update")
    @Transactional
    public BaseResult<String> updateUserMemo(UpdateUserMemoDTO updateUserMemoDTO){
        UserMemo userMemo = userMemoService.getById(updateUserMemoDTO.getId());
        String userId = UserContext.getUserId();
        ThrowUtils.throwIf(!userId.equals(userMemo.getUserId()),ErrorCode.PRTMISSION_ERROR);
        userMemo.setTitle(updateUserMemoDTO.getTitle());
        userMemo.setContent(updateUserMemoDTO.getContent());
        Boolean isSuccess = userMemoService.updateById(userMemo);
        return isSuccess? ResultUtil.success("修改备忘录成功"):ResultUtil.error(ErrorCode.SYSTEM_ERROR);
    }

    /**
     * 删除用户备忘录
     *
     * @param userMemoId 用户备忘录id
     * @return {@link BaseResult}<{@link String}>
     */
    @Operation(summary = "删除用户备忘录")
    @DeleteMapping ("/delete/{id}")
    @Transactional
    public BaseResult<String> deleteUserMemo(@PathVariable("id") Integer userMemoId){
        UserMemo userMemo = userMemoService.getById(userMemoId);
        String userId = UserContext.getUserId();
        ThrowUtils.throwIf(!userId.equals(userMemo.getUserId()),ErrorCode.PRTMISSION_ERROR);
        Boolean isSuccess = userMemoService.removeById(userMemoId);
        return isSuccess? ResultUtil.success("删除备忘录成功"):ResultUtil.error(ErrorCode.SYSTEM_ERROR);
    }




}
