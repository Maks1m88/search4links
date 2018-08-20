package ru.same;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.same.services.DataProcessingService;

@SpringBootApplication
//@EnableAutoConfiguration
@ComponentScan
public class Main {

    public static ConfigurableApplicationContext ctx;

    public static void main(String[] args) {
        ctx = SpringApplication.run(Main.class, args);
        DataProcessingService process = ctx.getBean("dataProcessingService", DataProcessingService.class);
        process.invoke();
//        process.generateData();
        ctx.close();
    }
}
