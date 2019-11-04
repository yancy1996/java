package com.sjf.study.aop;

import com.sjf.study.bean.DBContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 设置路由key
 * <p>
 * 默认情况下，所有的查询都走从库，插入/修改/删除走主库。我们通过方法名来区分操作类型（CRUD）
 */
@Aspect
@Component
public class DataSourceAop {

    @Pointcut("!@annotation(com.sjf.study.annotation.Master) " +
            "&& (execution(* com.sjf.study.service..*.select*(..)) " +
            "|| execution(* com.sjf.study.service..*.get*(..)))")
    public void readPointcut() {

    }

    @Pointcut("@annotation(com.sjf.study.annotation.Master) " +
            "|| execution(* com.sjf.study.service..*.insert*(..)) " +
            "|| execution(* com.sjf.study.service..*.add*(..)) " +
            "|| execution(* com.sjf.study.service..*.update*(..)) " +
            "|| execution(* com.sjf.study.service..*.edit*(..)) " +
            "|| execution(* com.sjf.study.service..*.delete*(..)) " +
            "|| execution(* com.sjf.study.service..*.remove*(..))")
    public void writePointcut() {

    }

    @Before("readPointcut()")
    public void read() {
        DBContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        DBContextHolder.master();
    }


    /**
     * 另一种写法：if...else...  判断哪些需要读从数据库，其余的走主数据库
     */
//    @Before("execution(* com.sjf.study.service.impl.*.*(..))")
//    public void before(JoinPoint jp) {
//        String methodName = jp.getSignature().getName();
//
//        if (StringUtils.startsWithAny(methodName, "get", "select", "find")) {
//            DBContextHolder.slave();
//        }else {
//            DBContextHolder.master();
//        }
//    }

}
