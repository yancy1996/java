package com.sjf.study.annotation;

import java.lang.annotation.*;

/**
 * 有一般情况就有特殊情况，特殊情况是某些情况下我们需要强制读主库，针对这种情况，定义一个主键，用该注解标注的就读主库
 * @author klin
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Master {

}
