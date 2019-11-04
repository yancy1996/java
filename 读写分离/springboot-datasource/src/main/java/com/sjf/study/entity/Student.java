package com.sjf.study.entity;

import lombok.Data;

@Data
public class Student {
    /**
     * 学生id
     */
    private Long id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 年龄
     */
    private Integer age;
}
