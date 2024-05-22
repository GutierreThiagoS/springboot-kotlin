package br.com.gutierre.productsdb.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    @Bean
    fun customOpenApi(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("RestFull API SpringBoot ProductsDB")
                    .version("v1")
                    .description("Apis para Autenticar Usuarios e ver Produtos, Categoria, Users e Configuração de Projetos")
                    .termsOfService("http://localhost:8081/")
                    .license(
                        License().name("Apache 2.0")
                            .url("http://localhost:8081")
                    )
            )
    }
}