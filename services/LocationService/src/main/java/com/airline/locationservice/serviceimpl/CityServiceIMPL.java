package com.airline.locationservice.serviceimpl;

import com.airline.locationservice.mapper.CityMapper;
import com.airline.locationservice.model.City;
import com.airline.locationservice.repositry.CityRepositry;
import com.airline.locationservice.service.CityService;
import com.airline.payload.request.CityRequest;
import com.airline.payload.response.CityResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityServiceIMPL implements CityService {

    private final CityRepositry cityRepositry;


    // create city
    @Override
    public CityResponse createCity(CityRequest cityRequest)throws Exception {

        if (cityRepositry.existsByCityCode(cityRequest.getCityCode())){
            throw new Exception("city with the give code already exist");
        }
        City city= CityMapper.getCity(cityRequest);
        City result= cityRepositry.save(city);

        return CityMapper.toResponse(result);
    }

    // find the city
    @Override
    public CityResponse getCitById(Long id) throws Exception {
        City city= cityRepositry.findById(id).orElseThrow(
                ()->new Exception("City not exit with given id")
        );
        return CityMapper.toResponse(city);
    }

    // update city

    @Override
    public CityResponse updateCity(Long id, CityRequest cityRequest) throws Exception {

        // find a city by id from database(CityRepositry) if its not present throw the Exception
        City city=cityRepositry.findById(id).orElseThrow(()->new Exception());

        // if city is present with given cityCode in database throw exception Else not present then it will be continued.
        if (cityRepositry.existsByCityCode(cityRequest.getCityCode())){
            throw new Exception();
        }

        // we are update city and save into database(CityRepositry)
        City saveCity = cityRepositry.save( CityMapper.updateEntity(city,cityRequest));
        // it's convert saveCity into cityResponse,and it will be return(CityMapper)
        return CityMapper.toResponse(saveCity);
    }

    // Delete City
    @Override
    public void deleteCity(Long id) {

        // find city by city id
   City city = cityRepositry.findById(id).orElseThrow(
           ()-> new RuntimeException("City is not present with given id"));
   // delete city
   cityRepositry.delete(city);
    }


    //get all cities
    @Override
    public Page<CityResponse> getAllCities(Pageable pageable) {

    // we are getting all records of city from database in  pageable format and in convert city to city response with the help of map method
        return cityRepositry.findAll(pageable).map(CityMapper::toResponse);
    }


    // search specific city from mappper to database
    @Override
    public Page<CityResponse> searchCities(String keyword, Pageable pageable) {
        // search into the repositry by keyword of keyword and pageable map to find citymapper to toresponse
        return cityRepositry.searchByKeyword(keyword, pageable).map(CityMapper::toResponse);
    }


    // get city by country code
    @Override
    public Page<CityResponse> getCitiesByCountryCode(String countryCode, Pageable pageable) {
        return cityRepositry.findByCountryCodeIgnoreCase(countryCode,pageable).map(CityMapper::toResponse);
    }

    // exits city are present or not
    @Override
    public boolean cityExists(String cityCode) {
        return cityRepositry.existsByCityCode(cityCode);
    }


}
