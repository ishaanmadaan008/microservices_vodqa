package com.vodqa.reservation.controller;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vodqa.reservation.dto.ReservationRequest;
import com.vodqa.reservation.entities.Reservation;
import com.vodqa.reservation.repos.ReservationRepository;
import com.vodqa.reservation.services.ReservationService;
import com.vodqa.reservation.services.ReservationServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by divyar on 9/16/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class ReservationControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ReservationService reservationService;


    private ObjectMapper objectMapper;

    private Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder;
    private JacksonTester < ReservationRequest > jsonTester;

    @InjectMocks
    private ReservationController reservationController;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.jackson2ObjectMapperBuilder = new Jackson2ObjectMapperBuilder();
        this.mockMvc = standaloneSetup(reservationController).build();
        objectMapper = jackson2ObjectMapperBuilder.build();
        JacksonTester.initFields(this, objectMapper);
    }


    @Test
    public void completeReservation() throws Exception {

        Reservation reservationObject = new Reservation();
        ReservationRequest request = new ReservationRequest();
        given(reservationService.bookFlight(request)).willReturn(reservationObject);
        final String reservationDTOJson = jsonTester.write(request).getJson();

        mockMvc.perform(post("/completeReservation")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(reservationDTOJson)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isCreated());
    }

    @Test
    public void healthCheck() throws Exception {

        mockMvc.perform(get("/health"))
                .andExpect(status().isOk());
    }

=======
import com.vodqa.reservation.entities.Flight;
import com.vodqa.reservation.services.ReservationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

@AutoConfigureMockMvc
@SpringBootTest
@RunWith(SpringRunner.class)
public class ReservationControllerTest {

    @MockBean
    ReservationService reservationService;

    String flightDetailsUrl="https://flightdetail-test-url";


    @MockBean
    RestTemplate restTemplate;

    @MockBean
    Flight flight;

    @Autowired
    MockMvc mockMvc;

    ReservationController reservationController;


    @Before
    public  void setUp()
    {
       // BDDMockito.given(flightDetailsUrl).willReturn("https://flightdetail-test-url");
         reservationController= new ReservationController();
    }

    @Test
    public void showCompleteReservation() {
        long flightId = 100;
        Flight flight=reservationController.showCompleteReservation(100l);
    }

    @Test
    public void completeReservation() {

    }

    @Test
    public void healthCheck() {
    }
>>>>>>> 2cc3cb82e4699b0f39374ecd77bf6ef17bb2f063
}