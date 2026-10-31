package com.airline.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityRequest {

    @NotBlank(message = "City name is required")
    @Size(max = 100)
    private String name;

    @NotBlank(message = "City code is required")
    @Size(max = 10)
    private String cityCode;

    @NotBlank(message = "CountryCode is required")
    @Size(max = 100)
    private String countryCode;

    @NotBlank(message = "CountryName is required")
    @Size(max = 100)
    private String countryName;

    @Size(max = 10)
    private String regionCode;

    @Size(max = 10)
    private String timeZoneOffset;
 }
