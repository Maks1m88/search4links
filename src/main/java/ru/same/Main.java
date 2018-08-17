package ru.same;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.same.logic.Process;

@SpringBootApplication
//@EnableAutoConfiguration
@ComponentScan
public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(Main.class, args);
        Process process = ctx.getBean("process", Process.class);
        process.process();
        System.out.println(ctx);
    }
}
