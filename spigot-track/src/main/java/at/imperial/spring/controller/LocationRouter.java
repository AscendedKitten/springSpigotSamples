package at.imperial.spring.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class LocationRouter {
    @Bean
    public RouterFunction<ServerResponse> route(LocationHandler locationHandler) {
        return RouterFunctions.route(RequestPredicates.GET("/players/{name}"), locationHandler::getLocationByName);
    }
}