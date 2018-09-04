package com.vodqa.flightdetails.controllers;

import com.vodqa.flightdetails.dto.FlightDetailRequest;
import com.vodqa.flightdetails.entities.Flight;
import com.vodqa.flightdetails.repos.FlightRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class FlightController {

	@Autowired
	FlightRepository flightRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(FlightController.class);

	@RequestMapping(value="/findFlights", method = RequestMethod.POST)
	public List<Flight> findFlights(@RequestBody FlightDetailRequest flightDetailRequest) throws ParseException {
		String depDate = flightDetailRequest.getDepartureDate();

		DateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
		Date date = (Date) parser.parse(depDate);

		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(formatter.format(date));
		System.out.println("Inside findFlights() From:" + flightDetailRequest.getFrom() + " TO:" + flightDetailRequest.getTo() + "Departure Date: " + date);
		List<Flight> flights = flightRepository.findFlights(flightDetailRequest.getFrom(), flightDetailRequest.getTo(),date);
		LOGGER.info("Flight Found are:" + flights);
		return flights;

	}

	@GetMapping(value="/findAllFlights")
	public List<Flight> findFlights() {
		List<Flight> flights = flightRepository.findAll();
		LOGGER.info("All Flights  are:" + flights);
		return flights;

	}


	@GetMapping(value="/findFlight/{flightId}")
	public Flight findFlightsByFlightid(@PathVariable("flightId") Long flightId) {
     Flight flight= flightRepository.findOne(flightId);
     return flight;
	}

	@GetMapping("/health")
	public String healthCheck()
	{
		return "ok";
	}


}
