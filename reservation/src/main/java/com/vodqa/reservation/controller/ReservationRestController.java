package com.vodqa.reservation.controller;

import com.vodqa.reservation.dto.ReservationUpdateRequest;
import com.vodqa.reservation.entities.Reservation;
import com.vodqa.reservation.repos.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReservationRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationRestController.class);

	@Autowired
	ReservationRepository reservationRepository;

	@RequestMapping("/reservations/{id}")
	public Reservation findReservation(@PathVariable("id") Long id) {
		LOGGER.info("Inside findReservation() for id: " + id);
		return reservationRepository.findOne(id);

	}

	@RequestMapping(value = "/reservations", method = RequestMethod.POST)
	public Reservation updateReservation(@RequestBody ReservationUpdateRequest request) {
		LOGGER.info("Inside updateReservation() for " + request);
		Reservation reservation = reservationRepository.findOne(request.getId());
		reservation.setNumberOfBags(request.getNumberOfBags());
		reservation.setCheckedIn(request.getCheckedIn());
		LOGGER.info("Saving Reservation");
		return reservationRepository.save(reservation);

	}

}
