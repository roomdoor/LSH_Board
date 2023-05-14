package com.lsh.board.util.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI openAPI() {

		Info info = new Info()
			.title("LSH_Board API 문서") // 타이틀
			.description("LSH_Board API 문서입니다.");

//		// Security 스키마 설정
//		SecurityScheme bearerAuth = new SecurityScheme()
//			.type(SecurityScheme.Type.HTTP)
//			.scheme("bearer")
//			.bearerFormat("JWT")
//			.in(SecurityScheme.In.HEADER)
//			.name(HttpHeaders.AUTHORIZATION);
//
//		// Security 요청 설정
//		SecurityRequirement addSecurityItem = new SecurityRequirement();
//		addSecurityItem.addList("JWT");
//
//		ModelConverters.getInstance()
//			.addConverter(new WebFluxSupportConverter(new ObjectMapperProvider(
//				new SpringDocConfigProperties())));
		return new OpenAPI()
			// Security 인증 컴포넌트 설정
//			.components(new Components().addSecuritySchemes("JWT", bearerAuth))
			// API 마다 Security 인증 컴포넌트 설정
//			.addSecurityItem(addSecurityItem)
			.info(info);
	}
}
