package aj;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cloudinary.utils.ObjectUtils;
import com.google.common.collect.Lists;

import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.mail.service.EmailService;
import it.ozimov.springboot.mail.service.exception.CannotSendEmailException;




@Controller
@RequestMapping(path="/greet")
public class GreetController {

	@Autowired
	private GreetRepository greetingRepository;
	
	@Autowired
    CloudinaryConfig cloudc;
	
	 @Autowired
	 public EmailService emailService;
	 
	 @Autowired
	 public ShoppingCart shoppingCart;
	
	@GetMapping(path="/add")
	public @ResponseBody String addNewGreet(@RequestParam String content)
	{
		Greeting g= new Greeting();
		g.setContent(content);
		greetingRepository.save(g);
		return "Saved";
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Greeting> getAllUsers()
	{
		return greetingRepository.findAll();
	}
	
	@GetMapping(path="/email")
	public String emailrecipient() throws UnsupportedEncodingException, CannotSendEmailException
	{
	
			//sendEmailWithTemplating("meensat3@gmail.com");
			sendEmailWithoutTemplating(); 
			return "hello";
		
	}
	
	@GetMapping("/upload")
    public String uploadForm(){
        return "upload";
    }
    @PostMapping("/upload")
    public String singleImageUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes, Model model){
        if (file.isEmpty()){
            model.addAttribute("message","Please select a file to upload");
            return "upload";
        }
        try {
            Map uploadResult =  cloudc.upload(file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
            System.out.println("Right here");
            model.addAttribute("message", "You successfully uploaded '" + file.getOriginalFilename() + "'");
            model.addAttribute("imageurl", uploadResult.get("url"));
            
            /*String filename = uploadResult.get("public_id").toString() + "." + uploadResult.get("format").toString();
            model.addAttribute("sizedimageurl", cloudc.createUrl(filename,100,150, "fit"));
            shoppingCart.addThings(filename);
            model.addAttribute("filesuploaded", shoppingCart.getThings());*/
            
            System.out.println(shoppingCart.getThings());
            
            
        } catch (IOException e){
            e.printStackTrace();
            model.addAttribute("message", "Sorry I can't upload that!");
        }
        return "upload";
    }
    
   
	
	
	public void sendEmailWithTemplating(String recipient) throws UnsupportedEncodingException, CannotSendEmailException{
	   final Email email = DefaultEmail.builder()
	        .from(new InternetAddress("samazon.infosys@gmail.com", "Meensat Tahzun"))
	        .to(Lists.newArrayList(new InternetAddress("samazon.infosys@gmail.com", "Lubaba")))
	        .subject("Laelius de amicitia")
	        .body("Firmamentum autem stabilitatis constantiaeque eius, quam in amicitia quaerimus, fides est.")
	        .encoding("UTF-8").build();
	   final Map<String, Object> modelObject = new HashMap<>();
	   modelObject.put("recipient",recipient);
	   emailService.send(email, "emailtemp", modelObject);
	}
	
	public void sendEmailWithoutTemplating() throws UnsupportedEncodingException{

		  final Email email = DefaultEmail.builder()

		        .from(new InternetAddress("samazon.infosys@gmail.com", "Marco Tullio Cicerone "))

		        .to(Lists.newArrayList(new InternetAddress("samazon.infosys@gmail.com", "Pomponius Attĭcus")))

		        .subject("Laelius de amicitia")

		        .body("Firmamentum autem stabilitatis constantiaeque eius, quam in amicitia quaerimus, fides est.")

		        .encoding("UTF-8").build();

		  emailService.send(email);

		}
	
	
}
