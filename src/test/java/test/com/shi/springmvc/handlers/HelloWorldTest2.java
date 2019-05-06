package test.com.shi.springmvc.handlers;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.shi.springmvc.handlers.HelloWorld;
import com.shi.springmvc.handlers.HelloWorldImpl;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration( locations = "classpath*:/springmvc.xml")
public class HelloWorldTest2{
	
    @Autowired
    public WebApplicationContext wac;
	  
	private MockMvc mockMvc;

	@Before
	  public void setUp() {
		//HelloWorld helloController = new HelloWorld();
	    mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		//this.mockMvc = webAppContextSetup(this.wac).build();  
	  }
	  
	//@MockBean
	//private HelloWorldImpl helloWorldImpl;
	
	@Test
	public void testHello() {
		try {
		      MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/helloworld"))
		                .andExpect(MockMvcResultMatchers.status().is(200))
		                .andDo(MockMvcResultHandlers.print())
		                .andReturn();
		      int status = mvcResult.getResponse().getStatus();
		      System.out.println("请求状态码：" + status);
		      String result = mvcResult.getResponse().getContentAsString();
		      System.out.println("接口返回结果：" + result);
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
	}

}
