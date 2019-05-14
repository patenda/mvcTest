package test.com.shi.springmvc.handlers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
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

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath*:/springmvc.xml")
public class PoolControllerTest {

	@Autowired
	public WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void test() {
		try {

			String s = "{\"info\":[{\"name\":\"poolA\",\"value\":\"5\"},{\"name\":\"poolB\",\"value\":\"500\"},{\"name\":\"poolC\",\"value\":\"1000\"}]}";
			//String s = "ssss";
			MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/poolCount").contentType(MediaType.APPLICATION_FORM_URLENCODED).param("s", s))
					.andExpect(MockMvcResultMatchers.status().is(200)).andDo(MockMvcResultHandlers.print()).andReturn();
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
