package webmvc.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import webmvc.service.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;



//让Filter由spring管理，同时在web.xml中声明DelegatingFilterProxy，这样既保证了Filter对于Servlet的作用，
// 又能让filter接受spring的依赖注入管理。
@Component
public class AuthFilter implements Filter {
    @Autowired
    UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        // 获取Authorization头:
        String authHeader = req.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Basic ")) {
            // 从Header中提取email和password:
//            String email = prefixFrom(authHeader);
//            String password = suffixFrom(authHeader);
//            // 登录:
//            User user = userService.signin(email, password);
//            // 放入Session:
//            req.getSession().setAttribute(UserController.KEY_USER, user);
        }
        // 继续处理请求:
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
