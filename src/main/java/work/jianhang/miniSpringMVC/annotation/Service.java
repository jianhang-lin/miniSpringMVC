package work.jianhang.miniSpringMVC.annotation;

import java.lang.annotation.*;

/**
 * 业务层注解
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {
    /**
     * VALUE属性
     * @return String
     */
    String value();
}
