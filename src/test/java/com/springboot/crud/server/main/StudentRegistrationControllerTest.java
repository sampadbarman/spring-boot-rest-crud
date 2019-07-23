package com.springboot.crud.server.main;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.springboot.crud.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentRegistrationControllerTest extends AbstractTest {

	protected MockMvc mvc;

	@Before
	public void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void registerStudentTest() throws Exception {
		String uri = "/register/student";
		Student student = new Student();
		student.setName("Elle");
		student.setAge(20);
		student.setRegistrationNumber("301");
		String inputJson = super.mapToJson(student);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "{\"name\":\"Elle\",\"age\":20,\"registrationNumber\":\"301\",\"registrationStatus\":\"Successful\"}");
		String response = mvcResult.getResponse().getContentType();
		assertEquals(response, "application/json;charset=UTF-8");
		
	}
}
