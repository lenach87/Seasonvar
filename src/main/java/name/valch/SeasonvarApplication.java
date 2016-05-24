package name.valch;

import name.valch.entity.User;
import name.valch.service.UserService;
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
	public CommandLineRunner demo(UserService userService) {
		return (args) -> {

			if (userService.findByLogin("admin") == null) {
				User admin = new User("admin", "admin", "admin", "elena@valch.name");
				userService.save(admin);
			}
		};
	}
}

//	@Bean
//	public CommandLineRunner demo(MailManager mailManager, SerialService serialService, SerialWithDatesService serialWithDatesService) {
//		return (args) -> {
//			SeasonvarParser sp = new SeasonvarParser(serialService, serialWithDatesService);
//			sp.parse();
//		};
//
//	}