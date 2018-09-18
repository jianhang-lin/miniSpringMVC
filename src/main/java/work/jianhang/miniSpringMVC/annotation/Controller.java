package work.jianhang.miniSpringMVC.annotation;

import java.lang.annotation.*;

/**
 * 控制器注解
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {
    /**
     * 作用于该类上的注解有一个VALUE属性，说白了就是Controller的名称
     * @return String
     */
    String value();
}
