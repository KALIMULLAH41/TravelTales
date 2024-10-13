package com.traveltales.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Videos 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	String videoName;
	@Column(length=2000)
	String videoLink;
	String videoType;
	String videoLang;
	String traveller;
	public Videos() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Videos(int id, String videoName, String videoLink, String videoType, String videoLang, String traveller) {
		super();
		this.id = id;
		this.videoName = videoName;
		this.videoLink = videoLink;
		this.videoType = videoType;
		this.videoLang = videoLang;
		this.traveller = traveller;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	public String getVideoLink() {
		return videoLink;
	}
	public void setVideoLink(String videoLink) {
		this.videoLink = videoLink;
	}
	public String getVideoType() {
		return videoType;
	}
	public void setVideoType(String videoType) {
		this.videoType = videoType;
	}
	public String getVideoLang() {
		return videoLang;
	}
	public void setVideoLang(String videoLang) {
		this.videoLang = videoLang;
	}
	public String getTraveller() {
		return traveller;
	}
	public void setTraveller(String traveller) {
		this.traveller = traveller;
	}
	@Override
	public String toString() {
		return "Videos [id=" + id + ", videoName=" + videoName + ", videoLink=" + videoLink + ", videoType=" + videoType
				+ ", videoLang=" + videoLang + ", traveller=" + traveller + "]";
	}
}
