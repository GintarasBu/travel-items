package com.homework.travel;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TravelItemControllerIT {

	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;


	@BeforeEach
	void printApplicationContext() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		//Arrays.stream(webApplicationContext.getBeanDefinitionNames())
		//		.map(name -> webApplicationContext.getBean(name).getClass().getName()).sorted()
		//		.forEach(System.out::println);
	}

	@Test
	public void testRetrieveTravelItems() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/travel-items/summer/20")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.intemsCount").value("2"));
	}

}
