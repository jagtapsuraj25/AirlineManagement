package com.airline.locationservice.controller;

import com.airline.payload.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public ApiResponse HomeController(){

        ApiResponse apiResponse=new ApiResponse();
         apiResponse.setMessage("Hello everyone im location service of microservice");
        return apiResponse;
    }
}
