package tacos.ch02;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrderController {
	
	@GetMapping("/current")
	public String orderForm(Model model) {
		model.addAttribute("order", new Order());
		
		return "orderForm";			
	}
	
	@PostMapping
	public String processOrder(Order order, BindingResult result) {
		if (result.hasErrors()) {
			return "404";
		}
		System.out.println(order.toString());
		
		return "home";
	}
}
