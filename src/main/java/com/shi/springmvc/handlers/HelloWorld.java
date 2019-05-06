package com.shi.springmvc.handlers;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorld {
	
	@Resource
	private HelloWorldImpl helloWorldImpl;

	 /**
     * 
     * @return
     */
    @RequestMapping("/helloworld")    
    public String hello() {
        System.out.println(this.helloWorldImpl.getHelloRtn());        
        return "success";
    }
}
