package test.com.shi.springmvc.handlers;

import org.junit.Before;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.shi.springmvc.handlers.HelloWorld;

import org.junit.Test;

public class HelloWorldTest {
	private MockMvc mockMvc;

	@Before
	  public void setUp() {
		HelloWorld helloController = new HelloWorld();
	    mockMvc = MockMvcBuilders.standaloneSetup(helloController).build();
	  }
	  
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
