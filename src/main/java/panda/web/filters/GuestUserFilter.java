package panda.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@WebFilter({"/faces/view/packages/*","/faces/view/create-package.xhtml", "/faces/view/home.xhtml",
"/faces/view/packages.xhtml", "/faces/view/receipts/receipts.xhtml"})
public class GuestUserFilter implements Filter {



    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        if (session.getAttribute("username") == null){
            response.sendRedirect("/faces/view/login.xhtml");
        }else {

            filterChain.doFilter(request, response);
        }
    }
}
