package com.mobilapi.domain.shop;


import com.mobilapi.domain.category.Category;
import com.mobilapi.domain.product.Price;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
public class Restaurant {

    @Id
    @Column(name = "restaurant_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String district;

    @Column(nullable = false)
    private String url;
    
    @Column(nullable = false)
    private String description;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "restaurant")
    private Score score;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "restaurant_category", joinColumns = {@JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id")}
            , inverseJoinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id")})
    private List<Category> categories;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "restaurant_payments", joinColumns =
            {@JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id")}
            , inverseJoinColumns = {@JoinColumn(name = "payment_id", referencedColumnName = "id")})
    private Set<Payment> payments;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "restaurant_promotion", joinColumns = {@JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id")}
            , inverseJoinColumns = {@JoinColumn(name = "promotion_id", referencedColumnName = "id")})
    private Set<Promotion> promotions;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "restaurant_service_locations", joinColumns = {@JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id")}
            , inverseJoinColumns = {@JoinColumn(name = "service_location_id", referencedColumnName = "id")})
    private Set<ServiceLocations> serviceLocationses;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "restaurant_openhour", joinColumns = {@JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id")}
            , inverseJoinColumns = {@JoinColumn(name = "openHour_id", referencedColumnName = "id")})
    private Set<OpenHours> openHourses;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }

    public Set<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(Set<Promotion> promotions) {
        this.promotions = promotions;
    }

    public Set<ServiceLocations> getServiceLocationses() {
        return serviceLocationses;
    }

    public void setServiceLocationses(Set<ServiceLocations> serviceLocationses) {
        this.serviceLocationses = serviceLocationses;
    }

    public Set<OpenHours> getOpenHourses() {
        return openHourses;
    }

    public void setOpenHourses(Set<OpenHours> openHourses) {
        this.openHourses = openHourses;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    

    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Restaurant)) return false;

        Restaurant that = (Restaurant) o;

        return getId().equals(that.getId());

    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
