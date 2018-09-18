package work.jianhang.miniSpringMVC.annotation;

import java.lang.annotation.*;

/**
 * 字段注解
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Qualifier {

    /**
     * VALUE属性
     * @return String
     */
    String value();
}
