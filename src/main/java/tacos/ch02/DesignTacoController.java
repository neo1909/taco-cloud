package tacos.ch02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tacos.ch02.Ingredient.Type;

@Controller
@RequestMapping("/design")
public class DesignTacoController {
	
	private static final Logger log = LoggerFactory.getLogger(DesignTacoController.class);
	
	@GetMapping
	public String showDesignForm(Model model) {
		log.info("-------PAGE: /design --------");
		
		model = setModelDefault(model);
		model.addAttribute("design", new Taco());
		
		return "design";
	}
	
	@GetMapping("/rest/array")
	@ResponseBody
	public List<String> showJsonTypeArray() {
		String[] arrString = {"A", "B", "C", "D"};
		List<String> lstJson = Arrays.asList(arrString);
		
		return lstJson;
	}
	
	@GetMapping("/rest/object")
	@ResponseBody
	public Ingredient showJsonTypeObject() {
		
		return new Ingredient("FLTO", "Flour Tortilla", Type.WRAP);
	}

	@GetMapping(value="/rest/map")
	@ResponseBody
	public Map<String, Object> showJsonTypeMap(Model model) {
		Map<String, Object> map = new HashMap<>();
		map.put("0", 0);
		map.put("1", "Lương Ngọc Phú");
		
		
		List<Ingredient> ingredients = Arrays.asList(
				new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
				new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
				new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
				new Ingredient("CARN", "Carnitas", Type.PROTEIN),
				new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
				new Ingredient("LETC", "Lettuce", Type.VEGGIES),
				new Ingredient("CHED", "Cheddar", Type.CHEESE),
				new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
				new Ingredient("SLSA", "Salsa", Type.SAUCE),
				new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
			);
		
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
//			map.put(type.toString().toLowerCase(), filterByType(ingredients, type));
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}
		
		// return model.asMap(): LinkedHashMap 
		map = new HashMap<>(model.asMap());
		
		return map;
	}
	
	private Model setModelDefault(Model model) {
		List<Ingredient> ingredients = Arrays.asList(
				new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
				new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
				new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
				new Ingredient("CARN", "Carnitas", Type.PROTEIN),
				new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
				new Ingredient("LETC", "Lettuce", Type.VEGGIES),
				new Ingredient("CHED", "Cheddar", Type.CHEESE),
				new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
				new Ingredient("SLSA", "Salsa", Type.SAUCE),
				new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
			);
		
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}
		
		return model;
	}
	
	
	private List<Ingredient> filterByType( List<Ingredient> ingredients, Type type) {
		List<Ingredient> lstResultIngredients = new ArrayList<>();
		if (ingredients != null && type != null) {
			for (Ingredient ingredient : ingredients) {
				if (type.toString().equals(ingredient.getType().toString())) {
					lstResultIngredients.add(ingredient);
				}
			}
		}
		
		return lstResultIngredients;
	}
	
	@PostMapping
	public String processDesign( @Valid Taco design, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model = setModelDefault(model);
			return "design";
		}
		
		return "redirect:/orders/current";
	}
	
	@ModelAttribute
	public void getStr(Model model) {
		model.addAttribute("str123", "123");
		model.addAttribute("str123456", "123456");
	}
}
