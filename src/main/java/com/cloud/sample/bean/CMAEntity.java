package com.cloud.sample.bean;

import java.util.List;

public class CMAEntity {
	
	private String clientId;
	private String clientSecret;
	private String obEndPoint;
	private String apiEndPoint;
	private String redirectURL;
	private String obFinanceId;
	private String cmaBank;
	
	private List<String> users;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getObEndPoint() {
		return obEndPoint;
	}

	public void setObEndPoint(String obEndPoint) {
		this.obEndPoint = obEndPoint;
	}

	public String getApiEndPoint() {
		return apiEndPoint;
	}

	public void setApiEndPoint(String apiEndPoint) {
		this.apiEndPoint = apiEndPoint;
	}

	public String getRedirectURL() {
		return redirectURL;
	}

	public void setRedirectURL(String redirectURL) {
		this.redirectURL = redirectURL;
	}

	public String getObFinanceId() {
		return obFinanceId;
	}

	public void setObFinanceId(String obFinanceId) {
		this.obFinanceId = obFinanceId;
	}

	public List<String> getUsers() {
		return users;
	}

	public void setUsers(List<String> users) {
		this.users = users;
	}

	public String getCmaBank() {
		return cmaBank;
	}

	public void setCmaBank(String cmaBank) {
		this.cmaBank = cmaBank;
	}
	
//	 client.setClientId("J8l4MZQoiovsUm4e13_oKxUAevnozEmvHcW7QKOBoB4=");
//	 client.setClientSecret("PNdny_MCWocmYyfAOJQzx_PjfBiarrJVpaxTiMrrtGw=");
//	 client.setOB_ENDPOINT("https://ob.rbs.useinfinite.io/open-banking/v3.1/aisp");
//	 client.setAPI_ENDPOINT("https://api.rbs.useinfinite.io");
//	 client.setRedirectURL("https://37a62d74-a93b-49a8-90a0-3359cbf0dca9.example.org/redirect");
//	 client.setAuthorizationUsername("123456789012@37a62d74-a93b-49a8-90a0-3359cbf0dca9.example.org");
//	 client.setOB_FINANCIALID("0015800000jfwB4AAI");
	
	
}
