package com.mehrdad.todolist;

import com.fasterxml.jackson.databind.Module;
import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;

@SpringBootApplication(
		nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class
)
@ComponentScan(
		basePackages = {"org.openapitools", "com.mehrdad.todolist" , "org.openapitools.configuration"},
		nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class
)
public class TodoListApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoListApplication.class, args);
	}
	@Bean(name = "org.openapitools.OpenApiGeneratorApplication.jsonNullableModule")
	public Module jsonNullableModule() {
		return new JsonNullableModule();
	}

}
