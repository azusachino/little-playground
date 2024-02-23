package cn.az.code.servlet;

import jakarta.servlet.annotation.WebFilter;

/**
 * @author az
 */
@WebFilter(urlPatterns = { "/my" })
public class MyFilter {

    // @Override
    // protected void doFilterInternal(HttpServletRequest request, @NonNull
    // HttpServletResponse response, FilterChain filterChain) throws
    // ServletException, IOException {
    // ServletContext context = request.getServletContext();

    // context.log("/my servlet filter");
    // filterChain.doFilter(request, response);
    // }

}
