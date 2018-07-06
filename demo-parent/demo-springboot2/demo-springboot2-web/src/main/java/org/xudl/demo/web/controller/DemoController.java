package org.xudl.demo.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.xudl.demo.web.form.DemoResponse;

@RestController
@RequestMapping("/demo")
public class DemoController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping("/hello/${username}")
	public DemoResponse hello(String username) {
		logger.info("--hello--");
		return new DemoResponse();
	}

	@RequestMapping("/page")
	public ModelAndView toPage() {
		logger.info("--toPage--");
		/**
		 * 静态页面默认需要放入static目录中，才能被正常访问
		 */
		return new ModelAndView("/demo.html");
	}

	@RequestMapping("/exception")
	public DemoResponse exception() {
		logger.info("--exception--");
		int a = 1;
		if (a == 1) {
			throw new RuntimeException("throw exception");
		}

		return new DemoResponse();
	}
}
