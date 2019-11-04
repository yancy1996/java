package com.sjf.study;

import com.sjf.study.entity.Student;
import com.sjf.study.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootDatasourceApplicationTests {

    @Autowired
    StudentService studentService;


    @Test
    public void testWrite() {
        Student student = new Student();
        student.setName("wangling");
        student.setAge(15);
        student.setSex(1);
        studentService.insert(student);
    }

    @Test
    public void testRead() {
        System.out.println(studentService.selectAll());

    }

    @Test
    public void testSave() {
        Student student = new Student();
        student.setName("wangling");
        student.setAge(15);
        student.setSex(1);
        studentService.save(student);
    }


    @Test
    public void testTargetDataSource() {
        System.out.println(studentService.fetchList());
    }

    @Test
    public void testReadFromMaster() {
        studentService.getToken("1234");
    }


}
