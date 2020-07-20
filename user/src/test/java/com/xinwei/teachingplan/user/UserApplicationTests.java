package com.xinwei.teachingplan.user;

import com.xinwei.teachingplan.test_word.WordAction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserApplicationTests {
    @Autowired
    WordAction wordAction;

    @Test
    void contextLoads() {
        wordAction.createWord();
    }

}
