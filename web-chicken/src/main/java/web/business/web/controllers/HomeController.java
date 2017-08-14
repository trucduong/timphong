package web.business.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import core.common.exception.CommonException;
import core.web.navigation.WebResult;
import web.business.config.Views;

@Controller
public class HomeController {

	@RequestMapping(value = Views.HOME_PAGE_URL, method = RequestMethod.GET)
	public WebResult gotoHome() throws CommonException {
	    WebResult mv = WebResult.show(Views.HOME_PAGE);

		return mv;
	}
}
