package com.app.codytutorials.dto;


import java.io.Serializable;

public class DTO implements Serializable {
    public String title;

    public DTO(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
