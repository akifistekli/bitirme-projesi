package com.mobilapi.domain.shop;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OpenHours {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String day;

    private String openTime;

    private String endTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OpenHours)) return false;

        OpenHours openHours = (OpenHours) o;

        return getId().equals(openHours.getId());

    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
