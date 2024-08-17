package com.jingjin.common.interceptor;

import cn.hutool.core.util.StrUtil;
import com.jingjin.common.utils.UserContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * ClassName: UserIdInterceptor
 * Description: 微服务拦截器，用来在传递用户信息给每个微服务
 *
 * @Author zjm
 */
public class UserIdInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1.获取请求头中的用户信息
        String userId = request.getHeader("userId");
        // 2.判断是否为空
        if (StrUtil.isNotBlank(userId)) {
            // 不为空，保存到ThreadLocal
            UserContext.setUserId(userId);
        }
//        // 1.获取请求头中的用户信息
//        String userPermission = request.getHeader("userPermission");
//        // 2.判断是否为空
//        if (StrUtil.isNotBlank(userPermission)) {
//            // 不为空，保存到ThreadLocal
//            UserContext.setUser(Long.valueOf(userId));
//        }
        // 3.放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 移除用户
        UserContext.removeUser();
    }
}
