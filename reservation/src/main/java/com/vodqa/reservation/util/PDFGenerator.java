package com.vodqa.reservation.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.vodqa.reservation.controller.ReservationController;
import com.vodqa.reservation.entities.Reservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@Component
public class PDFGenerator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PDFGenerator.class);

	@Autowired
	public ReservationController reservationController;

	public void generateItinerary(Reservation reservation, String filePath) {
		LOGGER.info("generateItinerary()");
		Document document = new Document();

		try {
			PdfWriter.getInstance(document, new FileOutputStream(filePath));

			document.open();

			document.add(generateTable(reservation));

			document.close();

		} catch (FileNotFoundException | DocumentException e) {
			LOGGER.error("Exception in generateItinerary " +e);
		}

	}

	private PdfPTable generateTable(Reservation reservation) {
		PdfPTable table = new PdfPTable(2);

		PdfPCell cell;

		cell = new PdfPCell(new Phrase("Flight Itinerary"));
		cell.setColspan(2);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Flight Details"));
		cell.setColspan(2);
		table.addCell(cell);

		table.addCell("Airlines ");
		table.addCell(reservationController.showCompleteReservation(reservation.getFlight_id()).getBody().getOperatingAirlines());

		table.addCell("Departure City");
		table.addCell(reservationController.showCompleteReservation(reservation.getFlight_id()).getBody().getDepartureCity());

		table.addCell("Arrival City");
		table.addCell(reservationController.showCompleteReservation(reservation.getFlight_id()).getBody().getArrivalCity());

		table.addCell("Flight Number");
		table.addCell(reservationController.showCompleteReservation(reservation.getFlight_id()).getBody().getFlightNumber());

		table.addCell("Departure Date");
		table.addCell(reservationController.showCompleteReservation(reservation.getFlight_id()).getBody().getDepartureCity());

		table.addCell("Departure Time");
		table.addCell(reservationController.showCompleteReservation(reservation.getFlight_id()).getBody().getEstimatedDepartureTime().toString());

		cell = new PdfPCell(new Phrase("Passenger Details"));
		cell.setColspan(2);
		table.addCell(cell);

		table.addCell("First Name");
		table.addCell(reservation.getPassenger().getFirstName());

		table.addCell("Last Name");
		table.addCell(reservation.getPassenger().getLastName());

		table.addCell("Email");
		table.addCell(reservation.getPassenger().getEmail());

		table.addCell("Phone");
		table.addCell(reservation.getPassenger().getPhone());

		return table;
	}

}
