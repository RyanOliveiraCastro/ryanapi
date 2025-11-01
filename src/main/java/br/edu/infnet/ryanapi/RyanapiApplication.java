package br.edu.infnet.ryanapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RyanapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RyanapiApplication.class, args);
	}

}
