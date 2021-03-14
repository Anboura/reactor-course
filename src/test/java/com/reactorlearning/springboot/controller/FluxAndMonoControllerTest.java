package com.reactorlearning.springboot.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
@WebFluxTest
public class FluxAndMonoControllerTest {
  @Autowired WebTestClient webTestClient;

  @Test
  public void flux_approach1() {
    final Flux<Integer> integerFlux =
        webTestClient
            .get()
            .uri("/flux")
            .accept(MediaType.APPLICATION_JSON_UTF8)
            .exchange()
            .expectStatus()
            .isOk()
            .returnResult(Integer.class)
            .getResponseBody();

    StepVerifier.create(integerFlux).expectSubscription().expectNext(1, 2, 3, 4).verifyComplete();
  }
}
