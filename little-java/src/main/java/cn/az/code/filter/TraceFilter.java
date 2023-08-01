package cn.az.code.filter;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

/**
 * @author az
 * @since 09/01/20
 */
@Order(1)
@WebFilter(urlPatterns = "/*")
public class TraceFilter implements Filter {

    private static final String TRACE_ID = "traceId";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String string = request.getParameter(TRACE_ID);
        if (!StringUtils.hasLength(string)) {
            string = UUID.randomUUID().toString();
        }

        // 在MDC中添加traceId
        MDC.put(TRACE_ID, string);

        chain.doFilter(request, response);
    }
}
