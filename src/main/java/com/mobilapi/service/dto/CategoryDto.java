package com.mobilapi.service.dto;


import com.mobilapi.domain.category.Category;

import java.util.UUID;

public class CategoryDto {

    private String thumb;

    private String title;

    private String description;

    private Boolean featured;

    private String icon;

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getdescription() {
        return description;
    }

    public void setdescription(String description) {
        this.description = description;
    }

    public Boolean getFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Category createCategory() {

        Category category = new Category();
        category.setGuid(UUID.randomUUID().toString());
        category.setTitle(getTitle());
        category.setThumb(getThumb());
        category.setFeatured(getFeatured());
        category.setDescription(getdescription());
        category.setIcon(getIcon());

        return category;

    }
}
