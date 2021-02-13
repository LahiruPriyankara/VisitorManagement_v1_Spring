package com.company.prjControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "authonticate")
public class EmployeeLoginController {
	@RequestMapping(value = "login")
	public ModelAndView getAvilbleVehicleData() {
		ModelAndView mv = new ModelAndView("main/login");
		try {
			System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXX");
			//log.debug("Enter | getAvilbleVehicleData");
			mv.addObject("vehicles", "");

		} catch (Exception e) {
			//log.debug("Exception : " + e);
		}
		return mv;
	}
}


