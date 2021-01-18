package com.tts.MapsApp;

import lombok.Data;

@Data
public class Location {
	private String city;
	private String state;
	private String lat;
	private String lng;
	private String short_name;
}