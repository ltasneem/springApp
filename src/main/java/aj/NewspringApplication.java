package aj;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

import com.google.common.collect.Lists;

import it.ozimov.springboot.mail.configuration.EnableEmailTools;
import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.mail.service.EmailService;
import it.ozimov.springboot.mail.service.exception.CannotSendEmailException;

@SpringBootApplication
@EnableEmailTools
public class NewspringApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewspringApplication.class, args);
	}
	
	/*@Autowired
	public EmailService emailService;
	
	@GetMapping(path="/email")
	public void sendEmailWithTemplating(String recipient) throws UnsupportedEncodingException, CannotSendEmailException{
	   final Email email = DefaultEmail.builder()
	        .from(new InternetAddress("meensat3@gmail.com", "Meensat Tahzun"))
	        .to(Lists.newArrayList(new InternetAddress("lubaba070@gmail.com", "Lubaba")))
	        .subject("Laelius de amicitia")
	        .body("Firmamentum autem stabilitatis constantiaeque eius, quam in amicitia quaerimus, fides est.")
	        .encoding("UTF-8").build();
	   final Map<String, Object> modelObject = new HashMap<>();
	   modelObject.put("recipient",recipient);
	   emailService.send(email, "emailtemp", modelObject);
	}*/
}
