package com.jingjin.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ClassName: 网站访问接口白名单
 * Description:
 *
 * @Author zjm
 */
@ConfigurationProperties(prefix = "auth.path")
@Component
@Data
public class AuthProperties {
    // 在nacos的配置中心有对应的路径
    // 白名单
    private List<String> whitePaths;

}
