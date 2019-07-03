package com.bean;

import java.util.Date;

public class User {
    private Long id;

    private Long expect_id;

    private Long geek_id;

    private String name;

    private Integer city;

    private Integer position;

    private Integer degree;

    private Integer low_salary;

    private Integer high_salary;

    private Integer work_date8;

    private Integer work_year;

    private String rev_wy;

    private Integer apply_status;

    private Integer fresh_graduate;

    private String skills;

    private Byte hunter_visible;

    private Integer district_code;

    private String business_code;

    private Double longitude;

    private Double latitude;

    private Byte status;

    private Date add_time;

    private Date update_time;

    private String geek_unified;

    private String school_id;

    private String job_hopping;

    private String age;

    private String birthday;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExpect_id() {
        return expect_id;
    }

    public void setExpect_id(Long expect_id) {
        this.expect_id = expect_id;
    }

    public Long getGeek_id() {
        return geek_id;
    }

    public void setGeek_id(Long geek_id) {
        this.geek_id = geek_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getDegree() {
        return degree;
    }

    public void setDegree(Integer degree) {
        this.degree = degree;
    }

    public Integer getLow_salary() {
        return low_salary;
    }

    public void setLow_salary(Integer low_salary) {
        this.low_salary = low_salary;
    }

    public Integer getHigh_salary() {
        return high_salary;
    }

    public void setHigh_salary(Integer high_salary) {
        this.high_salary = high_salary;
    }

    public Integer getWork_date8() {
        return work_date8;
    }

    public void setWork_date8(Integer work_date8) {
        this.work_date8 = work_date8;
    }

    public Integer getWork_year() {
        return work_year;
    }

    public void setWork_year(Integer work_year) {
        this.work_year = work_year;
    }

    public String getRev_wy() {
        return rev_wy;
    }

    public void setRev_wy(String rev_wy) {
        this.rev_wy = rev_wy == null ? null : rev_wy.trim();
    }

    public Integer getApply_status() {
        return apply_status;
    }

    public void setApply_status(Integer apply_status) {
        this.apply_status = apply_status;
    }

    public Integer getFresh_graduate() {
        return fresh_graduate;
    }

    public void setFresh_graduate(Integer fresh_graduate) {
        this.fresh_graduate = fresh_graduate;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills == null ? null : skills.trim();
    }

    public Byte getHunter_visible() {
        return hunter_visible;
    }

    public void setHunter_visible(Byte hunter_visible) {
        this.hunter_visible = hunter_visible;
    }

    public Integer getDistrict_code() {
        return district_code;
    }

    public void setDistrict_code(Integer district_code) {
        this.district_code = district_code;
    }

    public String getBusiness_code() {
        return business_code;
    }

    public void setBusiness_code(String business_code) {
        this.business_code = business_code == null ? null : business_code.trim();
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Date add_time) {
        this.add_time = add_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getGeek_unified() {
        return geek_unified;
    }

    public void setGeek_unified(String geek_unified) {
        this.geek_unified = geek_unified == null ? null : geek_unified.trim();
    }

    public String getSchool_id() {
        return school_id;
    }

    public void setSchool_id(String school_id) {
        this.school_id = school_id == null ? null : school_id.trim();
    }

    public String getJob_hopping() {
        return job_hopping;
    }

    public void setJob_hopping(String job_hopping) {
        this.job_hopping = job_hopping == null ? null : job_hopping.trim();
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age == null ? null : age.trim();
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }
}