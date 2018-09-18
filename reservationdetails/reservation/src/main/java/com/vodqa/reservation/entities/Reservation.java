package com.vodqa.reservation.entities;

import javax.persistence.*;

@Entity
public class Reservation{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private  long id;
	private Boolean checkedIn;
	private int numberOfBags;

	@OneToOne
	private Passenger passenger;

	public long getFlight_id() {
		return flight_id;
	}

	public void setFlight_id(long flight_id) {
		this.flight_id = flight_id;
	}

	private long flight_id;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public Boolean getCheckedIn() {
		return checkedIn;
	}

	public void setCheckedIn(Boolean checkedIn) {
		this.checkedIn = checkedIn;
	}

	public int getNumberOfBags() {
		return numberOfBags;
	}

	public void setNumberOfBags(int numberOfBags) {
		this.numberOfBags = numberOfBags;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}



}
