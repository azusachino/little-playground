package cn.az.code.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * @author az
 */
@WebServlet(name = "my",
        urlPatterns = "/my",
        initParams = {
                @WebInitParam(name = "aa", value = "bb")
        })
public class MyServlet extends HttpServlet {

    private String value;

    @Override
    public void init(ServletConfig config) throws ServletException {
        value = config.getInitParameter("aa");
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Writer writer = resp.getWriter();
        writer.write(value);
    }

}
