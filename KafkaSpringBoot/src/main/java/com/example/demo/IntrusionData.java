package com.example.demo;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

@Component
public class IntrusionData {
	
	private Address address;

	private String uuid;
	
	private Location location;
	
	private String provider;
	
	private String alert_type;
	
	private String intrusion_type;
	
	private String video_url;
	
	private String image_url;
	
	private String status;
	
	private Timestamp alerted;

	public Location getLocation() {
		return location;
	}

	public void setLatitude(Location location) {
		this.location = location;
	}	
	
	public Address getAddress() {
		return address;
	}

	public String getAlert_type() {
		return alert_type;
	}

	public void setAlert_type(String alert_type) {
		this.alert_type = alert_type;
	}

	public String getIntrusion_type() {
		return intrusion_type;
	}

	public void setIntrusion_type(String intrusion_type) {
		this.intrusion_type = intrusion_type;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
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

	public Timestamp getAlerted() {
		return alerted;
	}

	public void setAlerted(Timestamp alerted) {
		this.alerted = alerted;
	}
	
}
