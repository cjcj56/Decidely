package kisch.binyamin.decidely;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan(basePackages = {"kisch.binyamin.decidely.web"})
@SpringBootApplication
public class DecidelyApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(DecidelyApplication.class, args);
	}

}
