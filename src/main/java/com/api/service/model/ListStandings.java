package com.api.service.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListStandings implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Standings> standingsList;
}

