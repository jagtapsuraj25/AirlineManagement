package com.airline.locationservice.service;

import com.airline.payload.request.CityRequest;
import com.airline.payload.response.CityResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CityService {

    public CityResponse createCity(CityRequest cityRequest) throws Exception;
    public CityResponse getCitById(Long id) throws Exception;

    public CityResponse updateCity(Long id,CityRequest cityRequest) throws Exception;
    public void deleteCity(Long id);

    public Page<CityResponse> getAllCities(Pageable pageable);

    public Page<CityResponse> searchCities(String keyword, Pageable pageable);
    public Page<CityResponse> getCitiesByCountryCode(String countryCode,Pageable pageable);

    public boolean cityExists(String cityCode);


}


