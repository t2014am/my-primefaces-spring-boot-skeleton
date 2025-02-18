package es.test.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles special redirects on specific request paths and errors.
 */
@Slf4j
@Controller
public class MyRedirectsController implements ErrorController {

	private static final String INDEX_REDIRECT = "redirect:/index.xhtml";
	private static final String ERROR_MAPPING = "/error";

	@RequestMapping(value = "/")
	public static String rootRedirect() {
		log.info("Redirecting from root to index page");
		return INDEX_REDIRECT;
	}

	@RequestMapping(value = ERROR_MAPPING)
	public static String error() {
		log.warn("Redirecting internal error");
		return INDEX_REDIRECT;
	}

//	@Override
	public String getErrorPath() {
		log.warn("getErrorPath called");
		return ERROR_MAPPING;
	}

}
