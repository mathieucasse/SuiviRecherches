package ch.matfly.suivirecherches;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableJpaAuditing
@EnableSwagger2

public class SuiviRecherchesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuiviRecherchesApplication.class, args);
	}

	@Bean
	public Docket swaggerConfig(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/rest/**"))
				.apis(RequestHandlerSelectors.basePackage("ch.matfly"))
				.build()
				.apiInfo(apiDetalis());
	}

	private ApiInfo apiDetalis(){
		return new ApiInfo(
				"SuiviRecherches API",
				"API to record research for ORP",
				"0.1.0",
				"Free to use",
				new springfox.documentation.service.Contact("Mathieu Casse","http://matfly.ch","mc@matfly.ch"),
				"API licence",
				"http://matfly.ch",
				Collections.emptyList());
	}
}
