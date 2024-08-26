package com.jingjin.thirdpartywebsiteservice.controller;

import com.jingjin.common.result.BaseResult;
import com.jingjin.common.result.ErrorCode;
import com.jingjin.common.result.ResultUtil;
import com.jingjin.common.utils.UserContext;
import com.jingjin.model.thirdPartyWebsite.dto.AddFavoritesDTO;
import com.jingjin.model.thirdPartyWebsite.dto.DeleteFavoritesDTO;
import com.jingjin.model.thirdPartyWebsite.po.UserFavorites;
import com.jingjin.model.thirdPartyWebsite.vo.ThirdPartyWebsitePageVO;
import com.jingjin.model.thirdPartyWebsite.vo.ThirdPartyWebsiteSimpleVO;
import com.jingjin.thirdpartywebsiteservice.service.ThirdPartyWebsiteService;
import com.jingjin.thirdpartywebsiteservice.service.UserFavoritesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户第三方网站收藏关系控制器
 *
 * @author fxab
 * @date 2024/08/13
 */
@RefreshScope // 自动配置更新
@RestController
@RequestMapping("/favorites")
@Slf4j
@Tag(name = "第三方网站收藏关系接口文档", description = "第三方网站收藏关系模块接口文档")
public class UserFavoritesController {

    @Resource
    private UserFavoritesService userFavoritesService;

    @Resource
    private ThirdPartyWebsiteService thirdPartyWebsiteService;

    /**
     * 获取用户收藏第三方网站信息列表
     *
     * @return {@link BaseResult}<{@link ThirdPartyWebsitePageVO}>
     */
    @Operation(summary = "获取第三方网站收藏列表")
    @GetMapping("/get/list")
    @Transactional
    public BaseResult<List<ThirdPartyWebsiteSimpleVO>> getAdminWebsiteDetail(){
        // 从token获取用户id
        String userId = UserContext.getUserId();
        List<Integer> ids = userFavoritesService.getWebsiteIdsByUserId(userId);
        List<ThirdPartyWebsiteSimpleVO> websiteSimpleVOList = thirdPartyWebsiteService.getByIds(ids);
        return ResultUtil.success(websiteSimpleVOList);
    }

    /**
     * 添加第三方网站收藏
     *
     * @param addFavoritesDTO 添加收藏夹dto
     * @return {@link BaseResult}<{@link String}>
     */
    @Operation(summary = "新增第三方网站收藏关系")
    @PostMapping("/add")
    public BaseResult<String> addFavorites(@RequestBody AddFavoritesDTO addFavoritesDTO){
        // 从token获取用户id
        String userId = UserContext.getUserId();
        Integer webSiteId = addFavoritesDTO.getThirdPartyWebsiteId();
        UserFavorites userFavorites = UserFavorites.builder()
                .userId(userId)
                .adminWebsiteId(webSiteId)
                .build();
        Boolean isSuccess = userFavoritesService.save(userFavorites);
        return isSuccess?ResultUtil.success("新增第三方网站收藏关系成功"):ResultUtil.error(ErrorCode.SYSTEM_ERROR);
    }

    /**
     * 删除第三方网站收藏
     *
     * @param deleteFavoritesDTO 删除收藏夹dto
     * @return {@link BaseResult}<{@link String}>
     */
    @Operation(summary = "删除第三方网站收藏关系")
    @PostMapping("/delete")
    public BaseResult<String> deleteFavorites(@RequestBody DeleteFavoritesDTO deleteFavoritesDTO){
        Boolean isSuccess = userFavoritesService.removeByWebsiteId(deleteFavoritesDTO);
        return isSuccess?ResultUtil.success("删除第三方网站收藏关系成功"):ResultUtil.error(ErrorCode.SYSTEM_ERROR);
    }
}
