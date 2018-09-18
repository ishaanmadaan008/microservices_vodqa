package com.vodqa.reservation.services;

import com.vodqa.reservation.dto.ReservationRequest;
import com.vodqa.reservation.entities.Flight;
import com.vodqa.reservation.entities.Passenger;
import com.vodqa.reservation.entities.Reservation;
import com.vodqa.reservation.repos.PassengerRepository;
import com.vodqa.reservation.repos.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
public class ReservationServiceImpl implements ReservationService {


	@Value("${flight.details.url}")
	private String flightDetailsUrl;


	@Autowired
	PassengerRepository passengerRepository;

	@Autowired
	ReservationRepository reservationRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

	@Override
	@Transactional
	public Reservation bookFlight(ReservationRequest request) {

		LOGGER.info("Inside bookFlight()");
		// Make Payment

		Long flightId = request.getFlightId();
		LOGGER.info("Fetching  flight for flight id:" + flightId);
		RestTemplate restTemplate = new RestTemplate();
		Flight flight=restTemplate.getForObject(flightDetailsUrl+"/findFlight/"+flightId, Flight.class);

		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setPhone(request.getPassengerPhone());
		passenger.setEmail(request.getPassengerEmail());
		LOGGER.info("Saving the passenger:" + passenger);
		Passenger savedPassenger = passengerRepository.save(passenger);

		Reservation reservation = new Reservation();
		reservation.setFlight_id(flight.getId());
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);

		LOGGER.info("Saving the reservation:" + reservation);
		Reservation savedReservation = reservationRepository.save(reservation);


		return savedReservation;
	}

}
