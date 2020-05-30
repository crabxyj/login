package cn.edu.zucc.login.common.result;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author crabxyj
 * @date 2020/5/30 14:24
 * 使用于类上
 * 运行时保留
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ResultWrap {
    boolean isWrap() default true;
}
