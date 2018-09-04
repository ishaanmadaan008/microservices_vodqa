package com.vodqa.reservation.controller;

import com.vodqa.reservation.dto.ReservationRequest;
import com.vodqa.reservation.entities.Flight;
import com.vodqa.reservation.entities.Reservation;
import com.vodqa.reservation.services.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class ReservationController {


	@Autowired
	ReservationService reservationService;

	@Value("${flight.details.url}")
	private String flightDetailsUrl;

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);

	@GetMapping("/showCompleteReservation/{flightId}")
	public Flight showCompleteReservation(@RequestParam("flightId") Long flightId) {
		LOGGER.info("showCompleteReservation() invoked with the Flight Id: " + flightId);
		RestTemplate restTemplate = new RestTemplate();
		Flight flight=restTemplate.getForObject(flightDetailsUrl+"/findFlight/"+flightId, Flight.class);
		LOGGER.info("Flight is:" + flight);
		return flight;

	}

	@RequestMapping(value = "/completeReservation", method = RequestMethod.POST)
	public Reservation completeReservation(@RequestBody ReservationRequest request) {
		LOGGER.info("completeReservation()  " + request);

		Reservation reservation = reservationService.bookFlight(request);
		return reservation;

	}

}
