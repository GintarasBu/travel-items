package com.homework.travel;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.homework.travel.data.Item;

import com.homework.travel.enumeration.SeasonType;
import com.homework.travel.model.TravelItem;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TravelItemControllerIT {

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private MockMvc mockMvc;
	

	@BeforeEach
	void printApplicationContext() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	@Order(1) 
	public void testRetrieveTravelItems() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/travel-items/summer/20")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.intemsCount").value("1"));
	}
	
	@Test
	@Order(2) 
	public void testCrud() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/travel-items/summer/20")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.intemsCount").value("1"));
				
		Item summerItem = new Item("Test item", BigDecimal.valueOf(1), 1L, 20L, SeasonType.SUMMER);
		
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/travel-items")
				 .content(asJson(summerItem))
				 .contentType(MediaType.APPLICATION_JSON)
				 .accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().is(201));
				
		MvcResult result = resultActions.andReturn();
		String contentAsString = result.getResponse().getContentAsString();
		Long summerItemId = objectMapper.readValue(contentAsString, Long.class);			
				
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/travel-items/summer/20")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.intemsCount").value("2"));
		
		
		TravelItem updatedSummerItem = new TravelItem();
		updatedSummerItem.setId(summerItemId);
		updatedSummerItem.setTitle("Test item updated");
		updatedSummerItem.setWeight(BigDecimal.valueOf(1));
		updatedSummerItem.setQuantity(2L);
		updatedSummerItem.setDistance(20L);
		updatedSummerItem.setSeason(SeasonType.SUMMER);
		
		
		ResultActions updateResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/travel-items")
				 .content(asJson(updatedSummerItem))
				 .contentType(MediaType.APPLICATION_JSON)
				 .accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().is(200));
		

		TravelItem updatedTravelItem = objectMapper.readValue(updateResult.andReturn().getResponse().getContentAsString(), TravelItem.class);
		Assert.isTrue(updatedTravelItem.getId() != null, "Id null");
		Assert.isTrue(updatedTravelItem.getId().equals(summerItemId), "Ids mismatches");
		
		
		
		updatedSummerItem.setId(null);
		updatedSummerItem.setTitle("new test item");
		
		ResultActions createUpdateResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/travel-items")
				 .content(asJson(updatedSummerItem))
				 .contentType(MediaType.APPLICATION_JSON)
				 .accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().is(201));
		
		TravelItem createUpdatedTravelItem = objectMapper.readValue(createUpdateResult.andReturn().getResponse().getContentAsString(), TravelItem.class);
		Assert.isTrue(createUpdatedTravelItem.getId() != null, "Id null");
		Assert.isTrue(!createUpdatedTravelItem.getId().equals(summerItemId), "Ids mismatches");
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/travel-items/summer/20")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.intemsCount").value("3"));
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/travel-items/" + createUpdatedTravelItem.getId())
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/travel-items/summer/20")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.intemsCount").value("2"));
		
	}
	
	
	private String asJson(Object obj) {
	    try {
	        String jsonContent = objectMapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}  
	

}
