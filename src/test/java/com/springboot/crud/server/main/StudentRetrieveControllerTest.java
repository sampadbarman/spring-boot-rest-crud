package com.springboot.crud.server.main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
public class StudentRetrieveControllerTest extends AbstractTest {

	protected MockMvc mvc;

	@Before
	public void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void getStudentsTest() throws Exception {
		String uri = "/register/student";
		Student student = new Student();
		student.setName("Elle");
		student.setAge(20);
		student.setRegistrationNumber("301");
		String inputJson = super.mapToJson(student);
		MvcResult mvcResult1 = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		
		String posturi = "/student/allstudent";
		MvcResult mvcResult2 = mvc.perform(MockMvcRequestBuilders.get(posturi).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult2.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult2.getResponse().getContentAsString();
		Student[] studentList = super.mapFromJson(content, Student[].class);
		assertTrue(studentList.length > 0);
	}

}
