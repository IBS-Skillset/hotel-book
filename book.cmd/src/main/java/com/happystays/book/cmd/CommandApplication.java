package com.happystays.book.cmd;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.happystays.book.cmd.error.ErrorMappings;
import com.happystays.cqrs.core.infrastucture.CommandDispatcher;
import com.happystays.book.cmd.api.commands.BookCommand;
import com.happystays.book.cmd.api.commands.CommandHandler;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.PostConstruct;
import java.io.IOException;

@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
@AllArgsConstructor
public class CommandApplication {

	private final CommandDispatcher commandDispatcher;

	private final CommandHandler commandHandler;

	public static void main(String[] args) {
		SpringApplication.run(CommandApplication.class, args);
	}

	@PostConstruct
	public void registerHandlers() {
		commandDispatcher.registerHandler(BookCommand.class,commandHandler::handle);
	}

	@Bean
	public ErrorMappings errorMap() throws IOException {
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		mapper.findAndRegisterModules();
		return mapper.readValue(new ClassPathResource("/error.yaml")
						.getInputStream(),
				ErrorMappings.class);

	}

}
