package io.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@ConfigurationPropertiesScan("io.demo.conf")
@EnableFeignClients
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

}
