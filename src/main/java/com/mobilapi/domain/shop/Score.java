package com.mobilapi.domain.shop;


import javax.persistence.*;

@Entity
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer velocity;

    private Integer presentation;

    private Integer taste;

    @OneToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public Integer getVelocity() {
        return velocity;
    }

    public void setVelocity(Integer velocity) {
        this.velocity = velocity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPresentation() {
        return presentation;
    }

    public void setPresentation(Integer presentation) {
        this.presentation = presentation;
    }

    public Integer getTaste() {
        return taste;
    }

    public void setTaste(Integer taste) {
        this.taste = taste;
    }
}
