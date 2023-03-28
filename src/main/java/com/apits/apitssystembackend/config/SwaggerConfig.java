package com.apits.apitssystembackend.config;

import java.io.IOException;

import com.apits.apitssystembackend.utils.ReadJsonFIleToJsonObject;
import org.json.JSONException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.responses.ApiResponse;

@OpenAPIDefinition
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI baseOpenAPI() throws JSONException, IOException {
        ReadJsonFIleToJsonObject readJsonFIleToJsonObject = new ReadJsonFIleToJsonObject();
        ApiResponse badRequestAPI = new ApiResponse()
                .content(new Content().addMediaType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
                                new Example().value(
                                        readJsonFIleToJsonObject.read().get("badRequestResponse").toString()))))
                .description("Bad Request");
        ApiResponse internalServerErrorAPI = new ApiResponse()
                .content(new Content().addMediaType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
                                new Example().value(
                                        readJsonFIleToJsonObject.read().get("internalServerErrorResponse")
                                                .toString()))))
                .description("Internal Server");
        Components components = new Components();
        components.addResponses("badRequestAPI", badRequestAPI);
        components.addResponses("internalServerAPI", internalServerErrorAPI);

        return new OpenAPI().components(components)
                .info(new Info()
                        .title("Spring Doc")
                        .version("1.0.0")
                        .description("Spring doc"));
    }
}
