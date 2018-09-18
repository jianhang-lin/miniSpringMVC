package work.jianhang.miniSpringMVC.annotation;

import java.lang.annotation.*;

/**
 * 地址映射注解
 */
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
    public String value();
}
