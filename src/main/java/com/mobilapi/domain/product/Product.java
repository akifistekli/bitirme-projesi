package com.mobilapi.domain.product;


import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Product {

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
    private String body;

    @Column(nullable = false)
    private Boolean featured;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Picture> pictures;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Tags> tags;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "product_standardoptions", joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")}
            , inverseJoinColumns = {@JoinColumn(name = "standardOptions_id", referencedColumnName = "id")})
    private List<StandardOptions> standardOptionses;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "product_extraoptions", joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")}
            , inverseJoinColumns = {@JoinColumn(name = "extraOptions_id", referencedColumnName = "id")})
    private List<ExtraOptions> extraOptionses;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "product_price", joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")}
            , inverseJoinColumns = {@JoinColumn(name = "price_id", referencedColumnName = "id")})
    private List<Price> prices;

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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

    public List<StandardOptions> getStandardOptionses() {
        return standardOptionses;
    }

    public void setStandardOptionses(List<StandardOptions> standardOptionses) {
        this.standardOptionses = standardOptionses;
    }

    public List<ExtraOptions> getExtraOptionses() {
        return extraOptionses;
    }

    public void setExtraOptionses(List<ExtraOptions> extraOptionses) {
        this.extraOptionses = extraOptionses;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        if (!getId().equals(product.getId())) return false;
        return getGuid().equals(product.getGuid());

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getGuid().hashCode();
        return result;
    }

	public Boolean getFeatured() {
		return featured;
	}

	public void setFeatured(Boolean featured) {
		this.featured = featured;
	}
}
