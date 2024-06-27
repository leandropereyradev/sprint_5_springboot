package cat.itacademy.barcelonactiva.pereyra.gastonleandro.s05.t01.n02.S05T01N02PereyraGastonLeandro.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class SwaggerConfig {

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .info(new Info()
                        .title("Api REST Flowers")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Leandro Pereyra")
                                .url("https://github.com/leandropereyradev"))
                        .description("En esta tarea realizaremos un CRUD (Create, Read, Update, Delete) que pueda ser llamado como API Rest y, también, como aplicación web."));
    }
}
