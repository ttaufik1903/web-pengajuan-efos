package com.rabbai.serviceprospek.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

public class Scoring {
	private String user_id;
	private List<ScoringLoan> scoring;

	public List<ScoringLoan> getScoring() {
		return scoring;
	}

	public void setScoring(List<ScoringLoan> scoring) {
		this.scoring = scoring;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
 
}
