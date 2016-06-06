package com.mobilapi.service;


import com.mobilapi.domain.category.Category;
import com.mobilapi.domain.shop.Promotion;
import com.mobilapi.domain.shop.Restaurant;
import com.mobilapi.repository.RestaurantRepository;
import com.mobilapi.service.dto.RestaurantListDto;
import com.mobilapi.service.util.CalculateCurrentScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<RestaurantListDto> getRestaurantByLocalize(String city, String district) {

        List<Restaurant> restaurants = getRestaurantByLocation(city, district);

        List<RestaurantListDto> restaurantListDtoList = null;

        for (Restaurant restaurant : restaurants) {
            RestaurantListDto restaurantListDto = new RestaurantListDto();
            restaurantListDto.setRestaurant_id(restaurant.getId());
            restaurantListDto.setName(restaurant.getName());
            restaurantListDto.setScore(CalculateCurrentScore.Calculate(restaurant.getScore()));
            restaurantListDtoList.add(restaurantListDto);
        }

        return restaurantListDtoList;
    }

    public List<Promotion> getPromotionByLocalize(String city, String district) {

        List<Restaurant> restaurants = getRestaurantByLocation(city, district);
        List<Promotion> promotions = null;
        for (Restaurant restaurant : restaurants) {

            promotions.add((Promotion) restaurant.getPromotions());
        }

        return promotions;
    }

    public List<Category> getRestaurantCategory(Long id) {

        return restaurantRepository.findById(id).getCategories();
    }

    public Restaurant getRestaurantById(Long id) {

        return restaurantRepository.findById(id);
    }

    public Iterable<Restaurant> getAllRestaurant() {

        return restaurantRepository.findAll();
    }

    private List<Restaurant> getRestaurantByLocation(String city, String district) {
        return restaurantRepository.findByCityAndDistrict(city, district);
    }


}
