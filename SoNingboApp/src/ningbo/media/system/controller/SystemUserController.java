package ningbo.media.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class SystemUserController {

	@RequestMapping(value="/{name}",method = RequestMethod.GET)
	public String printUser(@PathVariable("name")String name,ModelMap model) {
		model.put("message", "Devon.Ning,Welcome");
		model.addAttribute("interest",name) ;
		return "index";
	}
}
