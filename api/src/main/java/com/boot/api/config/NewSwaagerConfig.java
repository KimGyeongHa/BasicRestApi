package com.boot.api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Restful Service API명세서",
                description = "Spring Boot로 개발한 Restful API명세서",
                version = "v.1.0.0")
)
@Configuration
public class NewSwaagerConfig {

    public GroupedOpenApi customOpenAPI(){
        String[] roles = {"/user/**","/admin/**"};

        return GroupedOpenApi
                .builder()
                .group("일반 사용자와 관리자를 위한 도메인")
                .pathsToMatch(roles)
                .build();
    }
}
