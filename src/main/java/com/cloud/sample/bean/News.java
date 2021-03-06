package com.cloud.sample.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class News {
	private String title;
	private String description;
	private String date;
	private String id;
	private String type;
	private String company;

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
	
	
	   public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String toString() {
		     return new ToStringBuilder(this).
		    		 append("id", id).
		       append("title", title).
		       append("description", description).
		       append("date", date).
		       append("type",type).
		       toString();
		   }
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
}
