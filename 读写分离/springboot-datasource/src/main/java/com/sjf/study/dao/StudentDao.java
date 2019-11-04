package com.sjf.study.dao;

import com.sjf.study.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description TODO
 * @Created by klin
 * @Date 2019/11/2 17:28
 */
@Repository
public interface StudentDao {

    int insert(Student student);

    List<Student> selectAll();
}
