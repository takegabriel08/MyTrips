package ro.scoalainformala.hello;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication//(exclude = { SecurityAutoConfiguration.class })
public class HelloSpringApplication {


    public static void main(String[] args) {
//        BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
//        System.out.println("Hashed password is:" + pwdEncoder.encode("1234"));
        SpringApplication.run(HelloSpringApplication.class, args);
    }

    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }
//
}
