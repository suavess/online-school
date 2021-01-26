package com.suave.edu;

import com.google.gson.Gson;
import com.suave.edu.service.SubjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Suave
 * @date 2021/1/26 9:42 上午
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class T1 {

    @Autowired
    private SubjectService subjectService;

    @Test
    public void test1() {
        System.out.println(new Gson().toJson(subjectService.getAllSubjectList(null)));
    }
}
