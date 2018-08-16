package ru.same.logic;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(Main.class, args);
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        for(Object p: System.getProperties().values()){
            System.out.println(p);
        }
//            System.get
    }
}
