package com.usts.xuexiangyu;

import com.usts.xuexiangyu.dao.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.usts.xuexiangyu.pojo.Users;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = XuexiangyuApplication.class)
public class XuexiangyuApplicationTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void TestDB() {
        Users u = new Users();
        u.setuName("薛翔宇");
        u.setuPwd("123456");
        u.setuRole(1);
        this.userRepository.save(u);

    }

}
