package fa.training.project.fe;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainFEController {
	
	@GetMapping("home")
	public String regitserUser(Model model) {
		model.addAttribute("eventName", "World Cup 2022");
		return "FE/index";
	}
	
	@GetMapping("hehe")
	public String testHTML(Model model) {
		model.addAttribute("eventName", "World Cup 2022");
		return "FE/home";
	}
	
	@GetMapping("chats")
	public String chatPage() {
		return "FE/chatpage";
	}
	
}
