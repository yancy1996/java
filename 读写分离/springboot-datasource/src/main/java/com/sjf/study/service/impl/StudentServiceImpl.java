package com.sjf.study.service.impl;

import com.sjf.study.annotation.Master;
import com.sjf.study.dao.StudentDao;
import com.sjf.study.entity.Student;
import com.sjf.study.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentDao studentDao;


    @Override
    @Transactional
    public int insert(Student student) {
        return studentDao.insert(student);
    }

    @Master
    @Override
    public int save(Student student) {
        return studentDao.insert(student);
    }

    @Override
    public List<Student> selectAll() {
        return studentDao.selectAll();
    }

    @Master
    @Override
    public String getToken(String appId) {
        //  有些读操作必须读主数据库
        //  比如，获取微信access_token，因为高峰时期主从同步可能延迟
        //  这种情况下就必须强制从主数据读
        return null;
    }
}
