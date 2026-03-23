package com.dominick.cadastrausuario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CadastrausuarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(CadastrausuarioApplication.class, args);
	}

}
