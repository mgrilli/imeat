package br.com.tirolezo.imeatapi.configs.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
        .info(new Info()
                .title("IMeat API")
                .description("API Rest da aplicação do IMeat, com autenticação e as funcionalidades de cadastro de usuários, consulta, exclusão e adição de produtos")
                .contact(new Contact()
                        .name("Equipe Tirolezo")
                        .email("contato@tirolezo.com")));
    }


}
