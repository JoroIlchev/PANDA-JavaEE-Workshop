package panda.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/faces/view/packages/delivered.xhtml","/faces/view/packages/pending.xhtml",
        "/faces/view/packages/shipped.xhtml" })
public class NotAdminUser implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        String role = (String) session.getAttribute("role");
        if (!role.equals("Admin")){
            response.sendRedirect("/faces/view/home.xhtml");
        }else {
            filterChain.doFilter(request, response);
        }
    }
}
