package com.tts.MapsApp;

import java.util.List;

import lombok.Data;

@Data
public class ReverseGeocoding {
    private List<AddressComponents> address_components;
    private String formatted_address;
}
