package com.whiteboard.entity;

import java.util.List;

public class Project {
    private String name;
    private String description;
    private List<Stroke> content;
    private String user;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Stroke> getContent() {
        return content;
    }

    public void setContent(List<Stroke> content) {
        this.content = content;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
