package work.jianhang.miniSpringMVC.servlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "dispatcherServlet", urlPatterns = "/*", loadOnStartup = 1, initParams = {@WebInitParam(name = "base-package", value = "work.jianhang.miniSpringMVC")})
public class DispatcherServlet extends HttpServlet {

    private static final long serialVersionUID = -5207837183145086720L;
}
