package com.suave.vod;

import com.suave.vod.service.VodService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Suave
 * @date 2021/1/27 2:33 下午
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class T1 {

    @Autowired
    private VodService vodService;

    @Test
    public void testUploadAliVideo() {
    }
}
