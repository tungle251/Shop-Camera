package com.spring.domain;

public class State {
	private String status;
	private String messeges;

	public State(String status, String messeges) {
		super();
		this.status = status;
		this.messeges = messeges;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMesseges() {
		return messeges;
	}

	public void setMesseges(String messeges) {
		this.messeges = messeges;
	}

}	
