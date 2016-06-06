package com.mobilapi.domain.category;

import com.mobilapi.domain.product.Product;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String guid;

    @Column(nullable = false)
    private String thumb;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private Boolean featured;

    @Column(nullable = false)
    private String icon;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
    @JoinTable(name = "product_category", joinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id")}
            , inverseJoinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")})
    private List<Product> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;

        Category category = (Category) o;

        if (!getId().equals(category.getId())) return false;
        return getGuid().equals(category.getGuid());

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getGuid().hashCode();
        return result;
    }
}
