package com.oracle.soccerleague.model;

public class League {
    private int lid;
    private int year;
    private String season;
    private String title;

    public League(){}

    public League(int lid, int year, String season, String title) {
        this.lid = lid;
        this.year = year;
        this.season = season;
        this.title = title;
    }
    
    public int getLid() {
		return lid;
	}
    
    public void setLid(int lid) {
		this.lid = lid;
	}

	public int getYear() {
        return year;
    }
    
    public String getSeason() {
        return season;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String toString() {
        return title;
    }
}
