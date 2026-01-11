package za.co.sabs.planningtool.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("SABS Planning Tool API")
                        .version("1.0.0")
                        .description("Spring Boot 4 Planning Tool REST API documentation")
                        .license(
                                new License()
                                        .name("SABS Planning Tool Private License")
                                        .url("https://www.sabs.co.za")
                        )
                        .contact(
                                new Contact()
                                        .name("SABS ICT")
                                        .email("development@sabs.co.za")
                                        .url("https://www.sabs.co.za")

                        )
                )
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .name("bearerAuth")
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                );
    }
}
