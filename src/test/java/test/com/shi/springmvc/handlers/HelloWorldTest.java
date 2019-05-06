package test.com.shi.springmvc.handlers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.springframework.mock.web.MockHttpServletResponse;
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
//		                .andExpect(MockMvcResultMatchers.content().string("asdf"))
		                .andDo(MockMvcResultHandlers.print())
		                .andReturn();
		      MockHttpServletResponse response = mvcResult.getResponse();
		      int status = response.getStatus();
		      assertEquals(200, status);
		      String result = response.getContentAsString();
//		      assertEquals("asdf", result);
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
	}

}
