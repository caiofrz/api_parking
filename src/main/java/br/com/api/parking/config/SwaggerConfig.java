package br.com.api.parking.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI openAPI() {

    Contact contato = new Contact();
    contato.setName("Caio Ferraz");
    contato.setEmail("caioferrazalmeida.27@gmail.com");
    contato.setUrl("https://github.com/caiofrz");

    return new OpenAPI()
            .info(new Info()
                    .title("Parking API")
                    .description("API para controle de estacionamento de condomínio/prédio")
                    .contact(contato)
            )
            .addServersItem(new Server().url("apiparking-production.up.railway.app"))
            .addServersItem(new Server().url("http://localhost:8080"));
  };
}
