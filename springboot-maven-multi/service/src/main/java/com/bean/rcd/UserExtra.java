package com.bean.rcd;

import java.util.Date;

public class UserExtra {
    private Long id;

    private Long user_id;

    private String description;

    private Integer location;

    private String resume_email;

    private Date update_time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public String getResume_email() {
        return resume_email;
    }

    public void setResume_email(String resume_email) {
        this.resume_email = resume_email;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}