package com.sjf.study.aop;

import com.sjf.study.annotation.TargetDataSource;
import com.sjf.study.bean.DBContextHolder;
import com.sjf.study.constant.DBTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author klin
 * 设置路由key
 * <p>
 * 默认情况下，所有的查询都走从库，插入/修改/删除走主库。我们通过方法名来区分操作类型（CRUD）
 * 拓展：增加手动指定数据源
 */
@Slf4j
@Aspect
@Component
public class DataSourceAop {

    @Pointcut("!@annotation(com.sjf.study.annotation.Master) " +
            "&& (execution(* com.sjf.study.service..*.select*(..)) " +
            "|| execution(* com.sjf.study.service..*.get*(..)))")
    public void readPointcut() {

    }

    @Before("readPointcut()")
    public void read() {
        DBContextHolder.slave();
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

    @Before("writePointcut()")
    public void write() {
        DBContextHolder.master();
    }


    /**
     * 指定数据源
     */
    @Pointcut("@annotation(com.sjf.study.annotation.TargetDataSource)")
    public void chooseDataSource() {

    }

    @Around("chooseDataSource()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        TargetDataSource ds = method.getAnnotation(TargetDataSource.class);
        if (null == ds) {
            DBContextHolder.set(DBTypeEnum.MASTER);
        } else {
            DBContextHolder.set(ds.name());
        }
        try {
            return point.proceed();
        } finally {
            DBContextHolder.clearDataSouce();
        }
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
