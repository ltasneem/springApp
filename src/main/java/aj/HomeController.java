package aj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {
	
	@Autowired
	public ShoppingCart shoppingCart;
	
	@RequestMapping("/")
	public String index(Model model)
	{
		model.addAttribute("message","Hello!");
		return "hello";
	}
	
	@RequestMapping("/login")
	public String login()
	{
		String filename = "hello";
		shoppingCart.addThings(filename);
		return "login";
	}
	
	

}
