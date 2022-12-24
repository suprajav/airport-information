package com.app.airport.controller;

import com.app.airport.dto.ErrorDTO;
import com.app.airport.enums.CommonErrorTypeEnum;
import com.app.airport.models.Runways;
import com.app.airport.models.TopCountries;
import com.app.airport.service.AirportService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class AirportControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private AirportService airportService;


    @Test
    void testGetRunwaysByCountryCode() throws Exception{

        when(airportService.getRunways(any(String.class), any())).thenReturn(getSampleRunways());
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                        .get("/runways")
                        .param("countryCode", "AT")
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andDo(print())
                        .andReturn();
        List<Runways> runways = new ObjectMapper().readValue(result.getResponse().getContentAsString(), new TypeReference<List<Runways>>(){});
        assertTrue(runways.get(0).getId() == 123);
    }

    @Test
    void testGetRunwaysByCountryName() throws Exception{

        when(airportService.getRunways(any(), any(String.class))).thenReturn(getSampleRunways());
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                        .get("/runways")
                        .param("countryName", "Australia")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        List<Runways> runways = new ObjectMapper().readValue(result.getResponse().getContentAsString(), new TypeReference<List<Runways>>(){});
        assertTrue(runways.get(0).getId() == 123);
    }

    @Test
    void testGetRunwaysForValidationError() throws Exception{
        when(airportService.getRunways(any(), any(String.class))).thenReturn(getSampleRunways());
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                        .get("/runways")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn();
        ErrorDTO errorDTO = new ObjectMapper().readValue(result.getResponse().getContentAsString(), ErrorDTO.class);
        assertEquals("CountryCode or CountryName must be provided!", errorDTO.getErrorText());
        assertEquals(CommonErrorTypeEnum.BAD_REQUEST.getErrorCode(), errorDTO.getErrorCode());
    }

    @Test
    void testGetTopCountries() throws Exception{

        when(airportService.getTopCountries(1)).thenReturn(getTopCountires());
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                        .get("/countries")
                        .param("topAirports", "1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        List<Map<String,String>> topCountires = new ObjectMapper().readValue(result.getResponse().getContentAsString(), new TypeReference<List<Map<String,String>>>(){});
        assertTrue(topCountires.get(0).get("country").equals("US"));
        assertTrue(topCountires.get(0).get("airportCount").equals("23280"));
    }

    @Test
    void testGetTopCountriesForValidationError() throws Exception{

        when(airportService.getTopCountries(1)).thenReturn(getTopCountires());
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                        .get("/countries")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn();

        ErrorDTO errorDTO = new ObjectMapper().readValue(result.getResponse().getContentAsString(), ErrorDTO.class);
        assertEquals("Required request parameter 'topAirports' for method parameter type Integer is not present", errorDTO.getErrorText());
        assertEquals(CommonErrorTypeEnum.BAD_REQUEST.getErrorCode(), errorDTO.getErrorCode());
    }

    private List<Runways> getSampleRunways(){
        Runways runways = new Runways();
        runways.setId(123);
        List<Runways> runwayList = new ArrayList<>();
        runwayList.add(runways);
        return runwayList;
    }

    private List<TopCountries> getTopCountires(){
        TopCountries expectedTopCountries = new TopCountries() {
            @Override
            public String getCountry() {
                return "US";
            }
            @Override
            public int getAirportCount() {
                return 23280;
            }
        };
        List<TopCountries> topCountries = new ArrayList<>();
        topCountries.add(expectedTopCountries);
        return topCountries;
    }



}
