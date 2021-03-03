package com.api.service.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Standings implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String country_id;
	private String country_name;
	private String league_id;
	private String league_name;
	private String team_id;
	private String team_name;
	private String overall_league_position;
	
	
	
	
}