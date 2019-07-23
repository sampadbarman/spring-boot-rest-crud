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
public class StudentDeleteControllerTest extends AbstractTest {

	protected MockMvc mvc;

	@Before
	public void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void deleteStudentTest() throws Exception {
		String url = "/register/student";
		Student student = new Student();
		student.setName("Elle");
		student.setAge(20);
		student.setRegistrationNumber("301");
		String inputJson = super.mapToJson(student);
		MvcResult mvcResult1 = mvc.perform(
				MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		
		String uri = "/delete/student/" + student.getRegistrationNumber();
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Student is deleted successsfully");
	}
	
	@Test
	public void deleteStudentFailedTest() throws Exception {
		String url = "/register/student";
		Student student = new Student();
		student.setName("Elle");
		student.setAge(20);
		student.setRegistrationNumber("301");
		String inputJson = super.mapToJson(student);
		MvcResult mvcResult1 = mvc.perform(
				MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		
		String uri = "/delete/student/" + "403";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Delete un-successful");
	}


}
