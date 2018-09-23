package com.example.springwebpackage;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	// 주소 입력하면 처음으로 나오는 url mapping
	@RequestMapping("/")
	public String home(Locale locale, Model model) {
		// 로그창에 보여질 문구.
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		// 모델(서블릿의 request 객체를 대체한 것)
		model.addAttribute("serverTime", formattedDate);

		// home로 포워딩
		return "home";
	}
}
