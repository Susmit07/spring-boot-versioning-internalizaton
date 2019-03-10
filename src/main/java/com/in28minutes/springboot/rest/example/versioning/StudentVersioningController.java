package com.in28minutes.springboot.rest.example.versioning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentVersioningController {

	@Autowired
	MessageSource messageSource;

	// Version by URI
	@GetMapping("v1/student")
	public StudentV1 studentV1() {
		return new StudentV1("Bob Charlie");
	}

	@GetMapping("v2/student")
	public StudentV2 studentV2() {
		return new StudentV2(new Name("Bob", "Charlie"));
	}

	// Version by Request Parameter
	// Request parameter version
	// localhost:8080/student/param?version=2
	@GetMapping(value = "/student/param", params = "version=1")
	public StudentV1 paramV1() {
		return new StudentV1("Bob Charlie");
	}

	@GetMapping(value = "/student/param", params = "version=2")
	public StudentV2 paramV2() {
		return new StudentV2(new Name("Bob", "Charlie"));
	}

	// Version by Header Parameter
	// Send in Headers key = X-API-VERSION & value = 1
	@GetMapping(value = "	", headers = "X-API-VERSION=1")
	public StudentV1 headerV1() {
		return new StudentV1("Bob Charlie");
	}

	@GetMapping(value = "/student/header", headers = "X-API-VERSION=2")
	public StudentV2 headerV2() {
		return new StudentV2(new Name("Bob", "Charlie"));
	}

	// Send in accept headers
	// Version by MIME type
	@GetMapping(value = "/student/produces", produces = "application/vnd.company.app-v1+json")
	public StudentV1 producesV1() {
		return new StudentV1("Bob Charlie");
	}

	@GetMapping(value = "/student/produces", produces = "application/vnd.company.app-v2+json")
	public StudentV2 producesV2() {
		return new StudentV2(new Name("Bob", "Charlie"));
	}

	// Internalization of rest-api
	@GetMapping(value = "/get-greeting")
	public String greeting() {
		return messageSource.getMessage("good.morning", null, LocaleContextHolder.getLocale());
	}

	@GetMapping(value = "/get-greeting-name")
	public String greetingname() {
		String[] params= new String[] {"Hello","Susmit"};
		return messageSource.getMessage("good.morning.name", params, LocaleContextHolder.getLocale());
	}
}