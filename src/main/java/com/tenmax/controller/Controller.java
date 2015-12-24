package com.tenmax.controller;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public @ResponseBody String admin() {
		System.out.println("user admin login!");
		return "get admin";
	}
	
	@RequestMapping(value = "/provider", method = RequestMethod.GET)
	public @ResponseBody String provider() {
		System.out.println("user provider login!");
		return "get provider";
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public @ResponseBody String user() {
		System.out.println("user login!");
		return "get user";
	}
	
	//==================== method annotation ====================
	@DenyAll
	@RequestMapping(value = "/deny", method = RequestMethod.GET)
	public @ResponseBody String deny() {
		System.out.println("all user cant get this!");
		return "---";
	}
	
	@RolesAllowed({"ADMIN","PROVIDER"})
	@RequestMapping(value = "/adminAT", method = RequestMethod.GET)
	public @ResponseBody String adminAT() {
		System.out.println("user adminAT login !");
		return "user adminAT login";
	}
	
	@PermitAll
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public @ResponseBody String all() {
		System.out.println("everyone can login !");
		return "everyone can login";
	}
	
	
	
}
