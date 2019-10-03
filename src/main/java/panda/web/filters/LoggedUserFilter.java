package panda.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/faces/view/register.xhtml", "/faces/view/login.xhtml", "/faces/view/index.xhtml", "/"})
public class LoggedUserFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        System.out.println();
        if (session.getAttribute("username") != null) {

            response.sendRedirect("/faces/view/home.xhtml");
        } else {

            filterChain.doFilter(request, response);
        }

    }
}
