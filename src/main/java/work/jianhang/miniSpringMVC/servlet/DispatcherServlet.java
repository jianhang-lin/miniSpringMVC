package work.jianhang.miniSpringMVC.servlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "dispatcherServlet", urlPatterns = "/*", loadOnStartup = 1, initParams = {@WebInitParam(name = "base-package", value = "work.jianhang.miniSpringMVC")})
public class DispatcherServlet extends HttpServlet {

    private static final long serialVersionUID = -5207837183145086720L;

    //扫描基包
    private String basePackage = "";
    //基包下面所有的带包路径权限定类名
    private List<String> packageNames = new ArrayList<String>();
    //注解实例化 注解上的名称：实例化对象
    private Map<String, Object> instanceMap = new HashMap<String, Object>();
    //带包路径的权限定名称：注解上的名称
    private Map<String, String> nameMap = new HashMap<String, String>();
    //URL地址和方法的映射关系
    private Map<String, Method> urlMethodMap = new HashMap<String, Method>();
    //Method和权限定类名映射关系，主要是为了通过Method找到该方法的对象利用反射执行
    private Map<Method, String> methodPackageMap = new HashMap<Method, String>();

}
