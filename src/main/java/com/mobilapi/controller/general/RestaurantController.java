package com.mobilapi.controller.general;


import com.mobilapi.domain.category.Category;
import com.mobilapi.domain.shop.Promotion;
import com.mobilapi.domain.shop.Restaurant;
import com.mobilapi.service.RestaurantService;
import com.mobilapi.service.dto.RestaurantListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class RestaurantController extends BaseController {

    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping(method = RequestMethod.GET, value = "/restaurants/{city}/{district}")
    @ResponseBody
    public ResponseEntity showRestaurantByLocalize(@PathVariable("city") String city, @PathVariable("district") String district) {

        List<RestaurantListDto> restaurant = restaurantService.getRestaurantByLocalize(city, district);

        if (restaurant.size() == 0) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(restaurant, HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/promotions/{city}/{district}")
    @ResponseBody
    public ResponseEntity getPromotionsByLocalize(@PathVariable("city") String city, @PathVariable("district") String district) {

        List<Promotion> promotions = restaurantService.getPromotionByLocalize(city, district);

        if (promotions.size() == 0) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(promotions, HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/restaurant/{id}")
    @ResponseBody
    public ResponseEntity getRestaurantInfoById(@PathVariable("id") String id) {

        Restaurant restaurant = restaurantService.getRestaurantById(Long.valueOf(id));

        if (restaurant.equals(null)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(restaurant, HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/restaurant/{id}/category")
    @ResponseBody
    public ResponseEntity getRestaurantCategory(@PathVariable("id") String id) {

        List<Category> category = restaurantService.getRestaurantCategory(Long.valueOf(id));
        if (category.size() == 0) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(category, HttpStatus.OK);
        }
    }


    @RequestMapping(method = RequestMethod.GET, value = "/restaurants")
    @ResponseBody
    public ResponseEntity getAllRestaurant() {

        Iterable<Restaurant> restaurant = restaurantService.getAllRestaurant();

        return new ResponseEntity(restaurant, HttpStatus.OK);
    }

}
