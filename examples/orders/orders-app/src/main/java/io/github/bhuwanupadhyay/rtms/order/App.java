package io.github.bhuwanupadhyay.rtms.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.schema.registry.client.EnableSchemaRegistryClient;

@SpringBootApplication
@EnableSchemaRegistryClient
public class App {

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }
}
