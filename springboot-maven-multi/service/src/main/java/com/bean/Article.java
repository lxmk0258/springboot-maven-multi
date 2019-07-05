package com.bean;

import java.util.Date;
import java.util.List;

public class Article {
    private Integer id;

    private String article_name;

    private Double article_price;

    private Integer article_num;

    private Date add_time;

    private Date update_time;

    private List<Order> orderList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArticle_name() {
        return article_name;
    }

    public void setArticle_name(String article_name) {
        this.article_name = article_name == null ? null : article_name.trim();
    }

    public Double getArticle_price() {
        return article_price;
    }

    public void setArticle_price(Double article_price) {
        this.article_price = article_price;
    }

    public Integer getArticle_num() {
        return article_num;
    }

    public void setArticle_num(Integer article_num) {
        this.article_num = article_num;
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

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}