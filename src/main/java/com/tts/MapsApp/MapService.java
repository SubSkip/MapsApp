package com.tts.MapsApp;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MapService {
	@Value("${api_key}")
	private String apiKey;

	// Geocode City/State to Lat/Lng
	public void addCoordinates(Location location) {
		String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + location.getCity() + ","
				+ location.getState() + "&key=" + apiKey;
		RestTemplate restTemplate = new RestTemplate();
		GeocodingResponse response = restTemplate.getForObject(url, GeocodingResponse.class);
		Location coordinates = response.getResults().get(0).getGeometry().getLocation();
		location.setLat(coordinates.getLat());
		location.setLng(coordinates.getLng());
	}

	// ReverseGeocode Lat/Lng to City/State
	public void setLocationFromLatLng(Location location) {
		String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + location.getLat() + ","
				+ location.getLng() + "&key=" + apiKey;
		RestTemplate restTemplate = new RestTemplate();
		ReverseGeocodingResponse response = restTemplate.getForObject(url, ReverseGeocodingResponse.class);
		if (response.getResults().size() == 0) {
			location.setCity("Nowhere");
			location.setState("Noplace");
		} else {
			List<AddressComponents> components = response.getResults().get(0).getAddress_components();
			location.setCity(response.getResults().get(0).getFormatted_address());
			// location.setCity(components.get(0).getLong_name());
			location.setState(components.get(0).getLong_name());
		}
	}

}
