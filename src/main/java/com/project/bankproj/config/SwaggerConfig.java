package com.project.bankproj.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
        info = @Info(
                title = "BankProject",
                description = "There is a prototype of the BackEnd Bank's Core Services data. <br />" +
                        "Data consist of clients, accounts, products, accounts, transactions and managers.",
                version = "1.0.0",
                contact = @Contact(
                        name = "Lavrov Aleksey",
                        email = "kentaskis.l@gmail.com"
                )
        )
)
@SecurityScheme(
        name = "JWT",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SwaggerConfig {
}