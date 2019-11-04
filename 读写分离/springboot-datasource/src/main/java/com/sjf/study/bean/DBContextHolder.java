package com.sjf.study.bean;

import com.sjf.study.constant.DBTypeEnum;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author klin
 * 通过ThreadLocal将数据源设置到每个线程上下文中
 */
public class DBContextHolder {

    private static final ThreadLocal<DBTypeEnum> contextHolder = new ThreadLocal<>();

    private static final AtomicInteger counter = new AtomicInteger(-1);

    public static void set(DBTypeEnum dbType) {
        System.out.println("set datasource is "+dbType.name());
        contextHolder.set(dbType);
    }

    public static DBTypeEnum get() {
        return contextHolder.get();
    }

    public static void master() {
        set(DBTypeEnum.MASTER);

    }

    public static void slave() {
        //  轮询 切换策略根据实际情况修改
        int index = counter.getAndIncrement() % 2;
        if (counter.get() > 9999) {
            counter.set(-1);
        }
        if (index == 0) {
            set(DBTypeEnum.SLAVE1);
        } else {
            set(DBTypeEnum.SLAVE2);
        }
    }
    public static void clearDataSouce(){
        contextHolder.remove();
    }
}
