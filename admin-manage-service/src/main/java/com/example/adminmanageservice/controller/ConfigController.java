package com.example.adminmanageservice.controller;

import com.example.adminmanageservice.service.ConfigService;
import com.jingjin.common.result.BaseResult;
import com.jingjin.model.adminManage.dto.UpdateConfigDTO;
import com.jingjin.model.adminManage.vo.ConfigPageVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


/**
 * 网站配置控制器
 *
 * @author fxab
 * @date 2024/08/07
 */
@RefreshScope // 自动配置更新
@RestController
@RequestMapping("/")
@Slf4j
@Tag(name = "网站配置接口文档", description = "网站配置模块接口文档")
public class ConfigController {

    /**
     * 网站配置服务
     */
    @Resource
    private ConfigService ConfigService;

    /**
     * 获取网站配置详细信息列表
     *
     * @return {@link BaseResult}<{@link ConfigPageVO}>
     */
    @Operation(summary = "获取网站配置列表")
    @GetMapping("/get/list")
    @Transactional
    public BaseResult<ConfigPageVO> getConfig(){
        //todo 获取网站配置列表
        return null;
    }



    /**
     * 更新网站配置
     *
     * @param updateConfigDTO 更新管理员网站dto
     * @return {@link BaseResult}<{@link String}>
     */
    @Operation(summary = "修改网站配置")
    @PostMapping("/update")
    public BaseResult<String> updateConfig(UpdateConfigDTO updateConfigDTO){
        //todo 从token获取管理员id
        //todo 修改网站配置
        return null;
    }



}
