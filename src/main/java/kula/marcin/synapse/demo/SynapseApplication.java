package kula.marcin.synapse.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.context.annotation.SessionScope;

@SpringBootApplication
@SessionScope
public class SynapseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SynapseApplication.class, args);
	}

}
