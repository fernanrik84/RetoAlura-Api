package com.alura.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;

@SpringBootApplication
@ComponentScan(basePackages = "com.alura.*")
@EntityScan("com.alura.*")
@EnableJpaRepositories(basePackages = "com.alura.*")

//@ComponentScan({"com.alura.controller","com.alura.modelo","com.alura.repository","com.alura.services"})
public class ForoInicialApplication{

	public static void main(String[] args) {
		SpringApplication.run(ForoInicialApplication.class, args);
	}
	
}
