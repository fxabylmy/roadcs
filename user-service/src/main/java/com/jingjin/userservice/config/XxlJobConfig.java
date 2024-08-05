package com.jingjin.userservice.config;

//import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * xxl定时任务调度配置
 *
 * @author fxab
 * @date 2024/08/05
 */
@Configuration
@RefreshScope
public class XxlJobConfig {
    /**
     * 管理员地址
     */
    @Value("${xxl.job.admin.addresses}")
    private String adminAddresses;
    /**
     * 访问令牌
     */
    @Value("${xxl.job.accessToken}")
    private String accessToken;
    /**
     * appname
     */
    @Value("${xxl.job.executor.appname}")
    private String appname;
    /**
     * 地址
     */
    @Value("${xxl.job.executor.address}")
    private String address;
    /**
     * ip
     */
    @Value("${xxl.job.executor.ip}")
    private String ip;
    /**
     * 港口
     */
    @Value("${xxl.job.executor.port}")
    private int port;
    /**
     * 日志路径
     */
    @Value("${xxl.job.executor.logpath}")
    private String logPath;
    /**
     * 日志保留天数
     */
    @Value("${xxl.job.executor.logretentiondays}")
    private int logRetentionDays;

    /**
     * xxl作业执行者
     *
     * @return {@link XxlJobSpringExecutor}
     */
/*    @Bean
    public XxlJobSpringExecutor xxlJobExecutor() {
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(adminAddresses);
        xxlJobSpringExecutor.setAppname(appname);
        xxlJobSpringExecutor.setAddress(address);
        xxlJobSpringExecutor.setIp(ip);
        xxlJobSpringExecutor.setPort(port);
        xxlJobSpringExecutor.setAccessToken(accessToken);
        xxlJobSpringExecutor.setLogPath(logPath);
        xxlJobSpringExecutor.setLogRetentionDays(logRetentionDays);
        return xxlJobSpringExecutor;
    }*/
}