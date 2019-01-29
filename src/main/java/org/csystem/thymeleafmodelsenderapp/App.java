package org.csystem.thymeleafmodelsenderapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public static void main(String[] args)
    {
        SpringApplication.run(App.class, args);
        int i1 = 2;
        int i2 = 3;

        System.out.println(i2 + "" + i1);
    }
}
