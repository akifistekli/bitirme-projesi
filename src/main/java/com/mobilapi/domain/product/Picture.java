package com.mobilapi.domain.product;

import javax.persistence.*;
import java.util.List;

@Entity
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String url;

    @ManyToMany(mappedBy = "pictures")
    private List<Product> productSet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Product> getProductSet() {
        return productSet;
    }

    public void setProductSet(List<Product> productSet) {
        this.productSet = productSet;
    }
}
