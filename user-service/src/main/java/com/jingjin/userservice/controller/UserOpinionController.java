package com.jingjin.userservice.controller;

import com.jingjin.common.exception.ThrowUtils;
import com.jingjin.common.result.BaseResult;
import com.jingjin.common.result.ErrorCode;
import com.jingjin.common.result.PageResponse;
import com.jingjin.common.result.ResultUtil;
import com.jingjin.common.utils.UserContext;
import com.jingjin.model.userInteraction.dto.AddUserOpinionDTO;
import com.jingjin.model.userInteraction.dto.OpinionResponseDTO;
import com.jingjin.model.userInteraction.dto.UpdateUserOpinionDTO;
import com.jingjin.model.userInteraction.po.UserMemo;
import com.jingjin.model.userInteraction.po.UserOpinion;
import com.jingjin.model.userInteraction.vo.BackUserOpinionVO;
import com.jingjin.model.userInteraction.vo.UserOpinionVO;
import com.jingjin.userservice.service.UserOpinionService;
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
@RequestMapping("/interaction/opinion")
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
        // 从token获取当前用户id
        String userId = UserContext.getUserId();
        UserOpinion userOpinion = UserOpinion.builder()
                .userId(userId)
                .title(addUserOpinionDTO.getTitle())
                .content(addUserOpinionDTO.getContent())
                .build();
        Boolean isSuccess = userOpinionService.save(userOpinion);
        return isSuccess? ResultUtil.success("新增用户意见成功"):ResultUtil.error(ErrorCode.SYSTEM_ERROR);
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
        UserOpinion userOpinion = userOpinionService.getById(updateUserOpinionDTO.getId());
        String userId = UserContext.getUserId();
        ThrowUtils.throwIf(!userId.equals(userOpinion.getUserId()),ErrorCode.PRTMISSION_ERROR);
        userOpinion.setTitle(updateUserOpinionDTO.getTitle());
        userOpinion.setContent(updateUserOpinionDTO.getContent());
        Boolean isSuccess = userOpinionService.updateById(userOpinion);
        return isSuccess? ResultUtil.success("修改意见成功"):ResultUtil.error(ErrorCode.SYSTEM_ERROR);
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
        UserOpinion userOpinion = userOpinionService.getById(userOpinionId);
        String userId = UserContext.getUserId();
        ThrowUtils.throwIf(!userId.equals(userOpinion.getUserId()),ErrorCode.PRTMISSION_ERROR);
        Boolean isSuccess = userOpinionService.removeById(userOpinionId);
        return isSuccess? ResultUtil.success("删除意见成功"):ResultUtil.error(ErrorCode.SYSTEM_ERROR);

    }

    /**
     * 用户获取已提交意见
     *
     * @return {@link BaseResult}<{@link PageResponse}<{@link UserOpinionVO}>>
     */
    @Operation(summary = "用户获取已提交意见")
    @GetMapping ("/get")
    @Transactional
    public BaseResult<PageResponse<UserOpinionVO>> getUserOpinion(@RequestParam(defaultValue = "1") int pageIndex,
                                                                  @RequestParam(defaultValue = "10") int pageSize){
        // 从token获取当前用户id
        String userId = UserContext.getUserId();
        PageResponse<UserOpinionVO> userOpinionPageVO = userOpinionService.getPageById(userId,pageIndex,pageSize);
        return ResultUtil.success(userOpinionPageVO);
    }

    /**
     * 后台获取用户意见
     *
     * @param pageIndex 页面索引
     * @param pageSize  页面大小
     * @return {@link BaseResult}<{@link PageResponse}<{@link BackUserOpinionVO}>>
     */
    @Operation(summary = "管理员获取用户意见")
    @GetMapping ("/back/get")
    @Transactional
    public BaseResult<PageResponse<BackUserOpinionVO>> getBackUserOpinion(@RequestParam(defaultValue = "1") int pageIndex,
                                                                          @RequestParam(defaultValue = "10") int pageSize){
        PageResponse<BackUserOpinionVO> backUserOpinionVOPageVO = userOpinionService.getPageBack(pageIndex,pageSize);
        return ResultUtil.success(backUserOpinionVOPageVO);
    }


    /**
     * 回应用户意见
     *
     * @param opinionResponseDTO 意见回应dto
     * @return {@link BaseResult}<{@link String}>
     */
    @Operation(summary = "回应用户意见")
    @PutMapping ("/response")
    @Transactional
    public BaseResult<String> respondUserOpinion(@RequestBody OpinionResponseDTO opinionResponseDTO){
        String userId = UserContext.getUserId();
        UserOpinion userOpinion = UserOpinion.builder()
                .id(opinionResponseDTO.getId())
                .responseId(userId)
                .responseContent(opinionResponseDTO.getResponseContent())
                .build();
        Boolean isSuccess = userOpinionService.updateById(userOpinion);
        return isSuccess? ResultUtil.success("回应用户意见成功"):ResultUtil.error(ErrorCode.SYSTEM_ERROR);
    }




}
