package com.anand.geneticalgorithm.domain;

/**
 * 
 * @author A Anand
 *
 */
public class TimeSlot {
	
	private String timeSlotId;
	private String timeSlotTime;

	public TimeSlot() {
		super();
	}

	public TimeSlot(String timeSlotId, String timeSlotTime) {
		super();
		this.timeSlotId = timeSlotId;
		this.timeSlotTime = timeSlotTime;
	}

	public String getTimeSlotId() {
		return timeSlotId;
	}

	public void setTimeSlotId(String timeSlotId) {
		this.timeSlotId = timeSlotId;
	}

	public String getTimeSlotTime() {
		return timeSlotTime;
	}

	public void setTimeSlotTime(String timeSlotTime) {
		this.timeSlotTime = timeSlotTime;
	}

	@Override
	public String toString() {
		return "TimeSlot [timeSlotId=" + timeSlotId + ", timeSlotTime=" + timeSlotTime + "]";
	}

}
