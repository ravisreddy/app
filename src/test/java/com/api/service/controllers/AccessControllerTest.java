package com.api.service.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.api.service.config.CORSFilter;
import com.api.service.functions.FunctionApp;
import com.api.service.model.Standings;


@WebMvcTest(AccessController.class)
public class AccessControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	private AccessController accessController;

	@MockBean
	private FunctionApp app;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = standaloneSetup(accessController).addFilters(new CORSFilter()).build();
	}
	
	@Test
	public void givenCountryTeamLeaugeIdsFetchStandings() throws Exception {
		List<Standings> ls = new ArrayList<Standings>();
		when(app.filter(any(), any(), any())).thenReturn(ls);
		this.mockMvc.perform(get("/standings/1/1/1").header("Origin", "*"))
        .andExpect(status().isOk());
	}
	
	
}
