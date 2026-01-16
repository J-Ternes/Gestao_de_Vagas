package com.jonathan.Gestao_Vagas;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info( //Basta apertar Ctrl + space para aparecer as opções e preencherg
				title = "Gestão de Vagas",
				description = "API responsável pela gestão de vagas",
				version = "1"
		)
)
public class GestaoVagasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoVagasApplication.class, args);
	}

}
