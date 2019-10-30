package com.anand.geneticalgorithm.domain;

/**
 * 
 * @author A Anand
 *
 */
public class Room {

	private String roomNumber;
	private int roomSeatingCapacity;

	public Room() {
		super();
	}

	public Room(String roomNumber, int roomSeatingCapacity) {
		super();
		this.roomNumber = roomNumber;
		this.roomSeatingCapacity = roomSeatingCapacity;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public int getRoomSeatingCapacity() {
		return roomSeatingCapacity;
	}

	public void setRoomSeatingCapacity(int roomSeatingCapacity) {
		this.roomSeatingCapacity = roomSeatingCapacity;
	}

	@Override
	public String toString() {
		return "Room [roomNumber=" + roomNumber + ", roomSeatingCapacity=" + roomSeatingCapacity + "]";
	}

}
