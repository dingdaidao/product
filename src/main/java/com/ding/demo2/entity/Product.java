package com.ding.demo2.entity;

import java.util.Date;

public class Product {
    private int id;
    private String shop_name;
    private String stock_name;
    private String commodity_sku;
    private String commodity_name;
    private String size;
    private String color;
    private int stock;
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getStock_name() {
        return stock_name;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public String getCommodity_sku() {
        return commodity_sku;
    }

    public void setCommodity_sku(String commodity_sku) {
        this.commodity_sku = commodity_sku;
    }

    public String getCommodity_name() {
        return commodity_name;
    }

    public void setCommodity_name(String commodity_name) {
        this.commodity_name = commodity_name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", stock_name='" + stock_name + '\'' +
                ", commodity_sku='" + commodity_sku + '\'' +
                ", commodity_name='" + commodity_name + '\'' +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", stock=" + stock +
                ", date='" + date + '\'' +
                '}';
    }
}
