package com.vodqa.reservation.controller;

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
}