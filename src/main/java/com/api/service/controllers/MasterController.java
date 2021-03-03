package com.api.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.service.functions.FunctionApp;
import com.api.service.model.DropDownModel;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
public class MasterController {

	@Autowired
	FunctionApp functionApp;

	@GetMapping(value = "/get-countries", produces = "application/json")
	public ResponseEntity<List<DropDownModel>> getCountries() {
		//log.info("Passing Inputs");
		return ResponseEntity.ok(functionApp.getCountries());
	}

	@GetMapping(value = "/get-teams/{leagueId}", produces = "application/json")
	public ResponseEntity<List<DropDownModel>> getTeams(@PathVariable("leagueId") final String leagueId) {
		//log.info("Passing Inputs");
		return ResponseEntity.ok(functionApp.getTeams(leagueId));
	}
	
	@GetMapping("/welcome")
	 public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
	  model.addAttribute("name", name);
	  return "response";
	 }
}

