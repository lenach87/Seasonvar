package name.valch;

import name.valch.mail.MailManager;
import name.valch.parser.SeasonvarParser;
import name.valch.service.SerialService;
import name.valch.service.SerialWithDatesService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
@EnableScheduling
public class SeasonvarApplication extends SpringBootServletInitializer {


	public static void main(String[] args) {
		SpringApplication.run(SeasonvarApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(MailManager mailManager, SerialService serialService, SerialWithDatesService serialWithDatesService) {
		return (args) -> {
			SeasonvarParser sp = new SeasonvarParser(serialService, serialWithDatesService);
			sp.parse();
		};

	}
}
