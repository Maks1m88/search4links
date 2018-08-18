package ru.same;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import ru.same.services.DataProcessingService;

@SpringBootApplication
//@EnableAutoConfiguration
@ComponentScan
public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(Main.class, args);
        DataProcessingService process = ctx.getBean("dataProcessingService", DataProcessingService.class);
//        process.invoke();
        process.generateData();
        System.out.println(ctx);
        Environment environment = ctx.getEnvironment();
        ctx.close();
    }
}
