package takip.back;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import takip.back.mail.Mailer;
import takip.back.repository.UserRepository;
import takip.back.util.Util;

import javax.annotation.PostConstruct;

@SpringBootApplication
@RequiredArgsConstructor
public class BackApplication {

	final Util pd;
	final Mailer mailer;

	public static void main(String[] args) {
		var ctx = SpringApplication.run(BackApplication.class, args);


	}

	@PostConstruct
	public void pconstruct(){
//		pd.populateDatabase();
		System.out.println("database populated");
//		mailer.sendSimpleMessage();
//		System.out.println(ur.findAll());
	}

}
