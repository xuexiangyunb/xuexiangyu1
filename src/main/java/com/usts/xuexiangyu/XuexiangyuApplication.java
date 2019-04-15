package com.usts.xuexiangyu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.usts.xuexiangyu")

public class XuexiangyuApplication {

    public static void main(String[] args) {
        SpringApplication.run(XuexiangyuApplication.class, args);
    }

}
