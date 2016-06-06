package com.mobilapi.controller.general;

import com.mobilapi.domain.category.Category;
import com.mobilapi.domain.product.Product;
import com.mobilapi.domain.shop.Restaurant;
import com.mobilapi.service.CategoryService;
import com.mobilapi.service.RestaurantService;
import com.mobilapi.service.dto.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController extends BaseController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping(value = "/category/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity createCategory(@RequestBody CategoryDto categoryDto) {

        Category category = categoryService.createCategory(categoryDto);

        if (category.getId().equals(null)) {
            return new ResponseEntity(HttpStatus.FAILED_DEPENDENCY);
        } else {
            return new ResponseEntity(category, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/category/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity deleteCategory(@PathVariable("id") String id) {

        categoryService.deleteCategory(Long.valueOf(id));
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/category/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity updateCategory(@RequestBody Category category) {

        if (category.getId() == null) {
            return new ResponseEntity(HttpStatus.FAILED_DEPENDENCY);
        } else {
            categoryService.updateCategory(category);
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/category/all/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getCategory(@PathVariable("id") String id) {

        Restaurant restaurant = restaurantService.getRestaurantById(Long.valueOf(id));

        return new ResponseEntity(restaurant.getCategories(), HttpStatus.OK);
    }


    @RequestMapping(value = "/category/products/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getProductByCategory(@PathVariable("id") String id) {

        List<Product> product = categoryService.getAllProductByCategory(Long.valueOf(id));

        return new ResponseEntity(product, HttpStatus.OK);
    }

}
