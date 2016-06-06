package com.mobilapi.controller.dto;


import com.mobilapi.domain.product.Product;

import java.util.List;

public class OrderDto {

    private List<Product> products;

    private Long price;

    private Long location_id;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Long getLocation_id() {
        return location_id;
    }

    public void setLocation_id(Long location_id) {
        this.location_id = location_id;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
