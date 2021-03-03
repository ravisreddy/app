package com.api.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.service.functions.FunctionApp;
import com.api.service.model.Standings;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Api(value = "Standings", tags = { "League Standings" })
@RequestMapping(path = "/standings")
public class AccessController {

	@Autowired
	FunctionApp functionApp;

	@ApiOperation(value = "To get the Client ids", response = Object.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Retrieved standings"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = ""), @ApiResponse(code = 404, message = "The resource"),
			@ApiResponse(code = 500, message = "Internal Server Error.Please after some time.") })

	@GetMapping(value = "/{countryId}/{teamId}/{leagueId}", produces = "application/json")
	public ResponseEntity<List<Standings>> getClientIds(@PathVariable("countryId") final String countryId,
			@PathVariable("teamId") final String teamId, @PathVariable("leagueId") final String leagueId) {
		//log.info("Passing Inputs");
		return ResponseEntity.ok(functionApp.filter(countryId, teamId, leagueId));
	}

}
