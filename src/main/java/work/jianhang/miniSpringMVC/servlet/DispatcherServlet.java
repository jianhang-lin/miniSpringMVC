package work.jianhang.miniSpringMVC.servlet;

import work.jianhang.miniSpringMVC.annotation.Controller;
import work.jianhang.miniSpringMVC.annotation.Repository;
import work.jianhang.miniSpringMVC.annotation.Service;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "dispatcherServlet", urlPatterns = "/*", loadOnStartup = 1, initParams = {@WebInitParam(name = "base-package", value = "work.jianhang.miniSpringMVC")})
public class DispatcherServlet extends HttpServlet {

    private static final long serialVersionUID = -7305339996300445842L;

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

    /**
     * init初始化处理
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        basePackage = config.getInitParameter("base-package");

        try {
            //1.扫描基包得到全部的带包路径权限定名
            scanBasePackage(basePackage);
            //2.把带有@Controller/@Service/@Repository的类实例化放入MAP中，KEY为注解上的名称
            instance(packageNames);
            //3.Spring IOC注入
            springIOC();
            //4.完成URL地址与方法的映射关系
            handlerUrlMethodMap();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 注意：基包是X.Y.Z的形式，而URL是X/Y/Z的形式，需要转换
     * @param basePackage
     */
    private void scanBasePackage(String basePackage) {
        //注意为了得到基包下面的URL路径需要对basePackage作转换：将.替换为/
        URL url = this.getClass().getClassLoader().getResource(basePackage.replaceAll("\\.","/"));
        File basePackageFile = new File(url.getPath());
        System.out.println("scan:" + basePackageFile);
        File[] childFiles = basePackageFile.listFiles();
        for (File file : childFiles) {
            if (file.isDirectory()){//目录继续递归扫描
                scanBasePackage(basePackage + "." + file.getName());
            } else if (file.isFile()) {
                //去掉class
                packageNames.add(basePackage + "." + file.getName().split("\\.")[0]);
            }
        }
    }

    /**
     * 被注解标注的类的实例化
     * @param packageNames
     */
    private void instance(List<String> packageNames) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (packageNames.size() < 1) {
            return;
        }
        for (String string : packageNames) {
            Class c = Class.forName(string);
            if (c.isAnnotationPresent(Controller.class)) {
                Controller controller = (Controller) c.getAnnotation(Controller.class);
                String controllerName = controller.value();
                instanceMap.put(controllerName, c.newInstance());
                nameMap.put(string, controllerName);
                System.out.println("Controller:" + string + ",value:" + controller.value());
            } else if (c.isAnnotationPresent(Service.class)){
                Service service = (Service) c.getAnnotation(Service.class);
                String serviceName = service.value();
                instanceMap.put(serviceName, c.newInstance());
                nameMap.put(string, serviceName);
                System.out.println("Service:" + string + ",value:" + service.value());
            } else if (c.isAnnotationPresent(Repository.class)) {
                Repository repository = (Repository) c.getAnnotation(Repository.class);
                String repositoryName = repository.value();
                instanceMap.put(repositoryName, c.newInstance());
                nameMap.put(string, repositoryName);
                System.out.println("Repository:" + string + ",value:" + repository.value());
            }
        }
    }
}
