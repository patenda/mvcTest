package com.shi.springmvc.handlers;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {
	
	@Resource
	private Service service;

    @RequestMapping("/helloworld2")    
    public String hello2() {
        System.out.println(this.service.getHelloRtn());        
        return "success";
    }
    
    @RequestMapping("/helloworld")    
    public String hello() {
        System.out.println("success");        
        return "success";
    }
}
