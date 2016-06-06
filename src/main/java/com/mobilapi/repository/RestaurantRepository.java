package com.mobilapi.repository;


import com.mobilapi.domain.shop.Restaurant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant,Long> {

    List<Restaurant> findByCityAndDistrict(String city,String district);

    Restaurant findById(Long id);

}
