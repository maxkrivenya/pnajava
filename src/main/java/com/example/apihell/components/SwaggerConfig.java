package com.example.apihell.components;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(title = "IIIS",
        description = "IIS but better",
        version = "pi",
        contact = @Contact(name = "Max Krivenya",
                email = "tideystvovalnavernaka@gmail.com")))
public class SwaggerConfig {
}