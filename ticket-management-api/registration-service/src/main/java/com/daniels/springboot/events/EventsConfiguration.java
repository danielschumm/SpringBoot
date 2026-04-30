package com.daniels.springboot.events;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.reactive.function.client.ClientRequest;

@Configuration
public class EventsConfiguration {

    @Bean
    public WebClient webClient(@Value("${tickets.events.url}") String baseUrl) {
        return WebClient.builder()
                .baseUrl(baseUrl)
                .filter((request, next) -> {
                    System.out.println("Outgoing request: " + request.method() + " " + request.url());
                    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                            .getRequestAttributes();
                    if (attributes != null) {
                        String authHeader = attributes.getRequest().getHeader("Authorization");
                        if (authHeader != null) {
                            request = ClientRequest.from(request)
                                    .header("Authorization", authHeader)
                                    .build();
                        }
                    }
                    return next.exchange(request);
                })
                .build();
    }

    @Bean
    public EventsClient eventsClient(WebClient webClient) {
        return HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(webClient))
                .build()
                .createClient(EventsClient.class);
    }
}
