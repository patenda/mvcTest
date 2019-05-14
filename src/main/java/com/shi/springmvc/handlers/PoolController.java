package com.shi.springmvc.handlers;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PoolController {
	
	@Resource
	private CountService cntService;

    @RequestMapping("/poolCount")    
    public String newCount(HttpServletRequest request) {
    	String s = request.getParameter("s");
    	System.out.println(s);        
        this.cntService.getCount(s);  
        return "success";
    }
    
}
