package com.hamburgueria.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hamburgueria.controller.UsuarioController;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Autowired
	UsuarioController usuarioController;

	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("com.hamburgueria.controller"))              
          .paths(PathSelectors.any()).build()
          .apiInfo(apiInfo());
    }
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Burguer Mount")
				.description("Documentação da API de acesso aos endpoints da Burguer Mount.").version("0.0.1")
				.build();
	}
}
