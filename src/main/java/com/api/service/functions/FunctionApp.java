package com.api.service.functions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.api.service.model.CountryModel;
import com.api.service.model.DropDownModel;
import com.api.service.model.Standings;
import com.api.service.model.TeamModel;




@Service
public class FunctionApp {

	@Autowired
	RestTemplate restTemplate;
	
	@Value("${api_endpoint}")
	private String apiEndpoint;
	
	@Value("${api_key}")
	private String apiKey;
	
	@Value("${standings_action}")
	private String standingsAction;
	
	@Value("${countries_action}")
	private String countriesAction;
	
	@Value("${team_action}")
	private String teamAction;
	
	public List<Standings> filter(String countryId, String teamId, String leagueId) {
		String uri = apiEndpoint+standingsAction+"&league_id="+leagueId+"&APIkey="+apiKey; 
		Standings[] response = restTemplate.getForObject(uri, Standings[].class);
		
		List<Standings> listStandings = Arrays.asList(response);
		List<Standings> filteredStandings = new ArrayList<>();
		for(Standings data: listStandings) {
			
			if(data.getTeam_id().equalsIgnoreCase(teamId)) {
				Standings newData = new Standings();
				BeanUtils.copyProperties(data, newData);
				newData.setCountry_id(countryId);
				filteredStandings.add(newData);
			}
		}
	
		return filteredStandings;
	}

	public List<DropDownModel> getCountries() {
		String uri = apiEndpoint+countriesAction+"&APIkey="+apiKey;
		CountryModel[] response = restTemplate.getForObject(uri, CountryModel[].class);
		
		List<CountryModel> listCountries = Arrays.asList(response);
		List<DropDownModel> listDDModels= new ArrayList<>();
		
		for(CountryModel data: listCountries) {
			DropDownModel dd = new DropDownModel();
			dd.setId(data.getCountry_id());
			dd.setName(data.getCountry_name());
			listDDModels.add(dd);
		}
		
		return listDDModels;
	}

	public List<DropDownModel> getTeams(String leagueId) {
		String uri = apiEndpoint+teamAction+"&league_id="+leagueId+"&APIkey="+apiKey;
		TeamModel[] response = restTemplate.getForObject(uri, TeamModel[].class);
		
		List<TeamModel> listTeams = Arrays.asList(response);
		List<DropDownModel> listDDModels= new ArrayList<>();
		
		for(TeamModel data: listTeams) {
			DropDownModel dd = new DropDownModel();
			dd.setId(data.getTeam_key());
			dd.setName(data.getTeam_name());
			listDDModels.add(dd);
		}
		
		return listDDModels;
	}

}
