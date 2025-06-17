package mf.semi.joias.backend.loguin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "mf.semi.joias.backend.loguin.secutity")

public class LoguinApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoguinApplication.class, args);
	}

}
