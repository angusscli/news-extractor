package com.cloud.sample.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class News {
	private String title;
	private String description;
	private String date;
	private String id;
	private float score;
	private float magnitude;
	
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public float getMagnitude() {
		return magnitude;
	}
	public void setMagnitude(float magnitude) {
		this.magnitude = magnitude;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	   public String toString() {
		     return new ToStringBuilder(this).
		    		 append("id", id).
		       append("title", title).
		       append("description", description).
		       append("date", date).
		       append("score", score).
		       append("magnitude",magnitude).
		       toString();
		   }
}
