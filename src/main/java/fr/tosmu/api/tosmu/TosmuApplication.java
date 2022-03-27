package fr.tosmu.api.tosmu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "fr.tosmu.api.tosmu")
public class TosmuApplication {

	public static void main(String[] args) {
		SpringApplication.run(TosmuApplication.class, args);
	}

}
