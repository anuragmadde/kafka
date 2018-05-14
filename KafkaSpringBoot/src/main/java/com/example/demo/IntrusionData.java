package com.example.demo;

import java.security.Timestamp;

import org.springframework.stereotype.Component;

@Component
public class IntrusionData {
	
	private Address address;

	private String reference_id;
	
	private Location location;
	
	private String provider;
	
	private boolean type;
	
	private String video_url;
	
	private String image_url;
	
	private String status;
	
	private Timestamp alerted_time;

	public Location getLocation() {
		return location;
	}

	public void setLatitude(Location location) {
		this.location = location;
	}	
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getReference_id() {
		return reference_id;
	}

	public void setReference_id(String reference_id) {
		this.reference_id = reference_id;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public boolean isType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}

	public String getVideo_url() {
		return video_url;
	}

	public void setVideo_url(String video_url) {
		this.video_url = video_url;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getAlerted_time() {
		return alerted_time;
	}

	public void setAlerted_time(Timestamp alerted_time) {
		this.alerted_time = alerted_time;
	}
	
}
