package com.leucine.util;

public class Software {
    private int id;
    private String name;

    public Software(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Software() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
        return name;
    }


}

