package com.sjf.study.service;

import com.sjf.study.entity.Student;

import java.util.List;

public interface StudentService {

    int insert(Student student);

    int save(Student student);

    List<Student> selectAll();

    String getToken(String appId);

    List<Student> fetchList();
}
