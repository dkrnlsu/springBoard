package myboard.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * User: ojh
 * Date: 13. 6. 17
 * Time: 오후 6:28
 * To change this template use File | Settings | File Templates.
 */
public class BoardInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

        // 로그인 체크하여 비로그인시 로그인창으로 이동
        if(session.getAttribute("isLogin") == null) {
            response.sendRedirect("/login/loginForm");
            return false;
        }

        return true;
    }
}
