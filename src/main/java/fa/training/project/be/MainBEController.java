package fa.training.project.be;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainBEController {

	@GetMapping("management")
	public String dashboard() {
		return "BE/dashboard";
	}
	
	@GetMapping("test")
	public String test() {
		return "test";
	}
	
}
