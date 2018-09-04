package com.vodqa.reservation.services;

import com.vodqa.reservation.dto.ReservationRequest;
import com.vodqa.reservation.entities.Flight;
import com.vodqa.reservation.entities.Passenger;
import com.vodqa.reservation.entities.Reservation;
import com.vodqa.reservation.repos.PassengerRepository;
import com.vodqa.reservation.repos.ReservationRepository;
import com.vodqa.reservation.util.EmailUtil;
import com.vodqa.reservation.util.PDFGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Value("${com.vodqa.reservation.dirpath}")
	private String ITINERARY_DIR;


	@Value("${flight.details.url}")
	private String flightDetailsUrl;


	@Autowired
	PassengerRepository passengerRepository;

	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
	PDFGenerator pdfGenerator;

	@Autowired
	EmailUtil emailUtil;

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

//		String filePath = ITINERARY_DIR + savedReservation.getId()
//				+ ".pdf";
//		LOGGER.info("Generating  the itinerary");
//		pdfGenerator.generateItinerary(savedReservation, filePath);
//		LOGGER.info("Emailing the Itinerary");
//		emailUtil.sendItinerary(passenger.getEmail(), filePath);

		return savedReservation;
	}

}
