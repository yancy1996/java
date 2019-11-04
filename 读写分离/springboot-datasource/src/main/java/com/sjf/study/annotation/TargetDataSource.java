package com.sjf.study.annotation;

import com.sjf.study.constant.DBTypeEnum;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * @author klin
 * @Description 拓展，可手动指定哪个数据源
 * @Date 2019/11/4 16:10
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Order(1)
public @interface TargetDataSource {
    /**
     * 拓展：
     * 这个可以定义多个数据源标识，可以手动指定数据源,在aop处理
     */
    DBTypeEnum name() default DBTypeEnum.MASTER;
}
