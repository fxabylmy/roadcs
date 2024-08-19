package com.jingjin.filter;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import com.jingjin.common.result.BaseResult;
import com.jingjin.common.result.ErrorCode;
import com.jingjin.config.AuthProperties;
import com.jingjin.jwtutil.config.AuthJwtProperties;
import com.jingjin.jwtutil.jwtUtil.JwtTokenUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;


import static com.jingjin.common.result.ErrorCode.*;

@Configuration
@Slf4j
public class AuthFilter {

    private final AuthProperties authProperties;

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();


    @Resource
    private AuthJwtProperties authJwtProperties;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    public AuthFilter(AuthProperties authProperties) {
        this.authProperties = authProperties;
    }

    @Bean
    @Order(-101)
    public GlobalFilter jwtAuthGlobalFilter(){
        return (exchange, chain) -> {
            // 登陆判断
            ServerHttpRequest serverHttpRequest = exchange.getRequest();
            ServerHttpResponse serverHttpResponse = exchange.getResponse();


            if (isExclude(serverHttpRequest.getPath().toString())) {
                // 白名单，直接放行
                return chain.filter(exchange);
            }


            // 从 HTTP 请求头中获取 JWT 令牌
            String token = getToken(serverHttpRequest);
            if (StringUtils.isEmpty(token)) {
                return unauthorizedResponse(exchange, serverHttpResponse,TOKEN_MISSION);
            }
            Boolean expired = jwtTokenUtil.isTokenExpired(token);

            //令牌是否过期或无效
            if (expired){
                return unauthorizedResponse(exchange, serverHttpResponse,TOKEN_INVALID);
            }

            // 从token中拿到权限
            List<String> permissions = jwtTokenUtil.getUserPermissionsFromToken(token);

            // 进行权限的过滤
            // 获取当前请求的路径
            String requestPath = exchange.getRequest().getURI().getPath();

            // 判断用户是否有权限访问该路径,没有的话直接报错返回
            if (!(isAuthorized(permissions, requestPath))) {
                return unauthorizedResponse(exchange, serverHttpResponse,PRTMISSION_ERROR);
            }

            // 拿到用户id
            String userId = jwtTokenUtil.getUserIdFromToken(token);
            //将用户id添加到请求头，传递到下游的微服务
            return chain.filter(exchange.mutate().request(builder -> builder.header("userId", userId)).build());
        };
    }

    /**
     * 根据权限列表查询当前访问是否在对应权限的路径集合
     * @param permissions 权限路由列表
     * @param requestPath 当前访问路径
     * @return 是否存在
     */
    private boolean isAuthorized(List<String> permissions, String requestPath) {
        // 检查当前请求路径是否与 token 中的权限路径匹配
        return permissions.stream().anyMatch(permission -> antPathMatcher.match(permission, requestPath));
    }

    /**
     * 判断请求路径是否和白名单匹配
     * @param antPath
     * @return
     */
    private boolean isExclude(String antPath) {
        for (String pathPattern : authProperties.getWhitePaths()) {
            if(antPathMatcher.match(pathPattern, antPath)){
                return true;
            }
        }
        return false;
    }

    /**
     * 获取令牌
     *
     * @param request 请求
     * @return {@link String}
     */
    private String getToken(ServerHttpRequest request) {
        String token = request.getHeaders().getFirst(authJwtProperties.getHeader());
        return token;
    }

    /**
     * 错误处理
     *
     * @param exchange           交易所
     * @param serverHttpResponse 服务器http响应
     * @param errorCode          错误代码
     * @return {@link Mono}<{@link Void}>
     */
    private Mono<Void> unauthorizedResponse(ServerWebExchange exchange, ServerHttpResponse serverHttpResponse, ErrorCode errorCode) {
        log.warn("token异常处理,请求路径:{}", exchange.getRequest().getPath());
        serverHttpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
        serverHttpResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        BaseResult responseResult = new BaseResult<>(errorCode);
        DataBuffer dataBuffer = serverHttpResponse.bufferFactory()
                .wrap(JSON.toJSONStringWithDateFormat(responseResult, JSON.DEFFAULT_DATE_FORMAT)
                        .getBytes(StandardCharsets.UTF_8));
        return serverHttpResponse.writeWith(Flux.just(dataBuffer));
    }

}
