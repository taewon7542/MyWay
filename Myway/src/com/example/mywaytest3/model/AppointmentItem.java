package com.example.mywaytest3.model;

public class AppointmentItem {
	private int id;
	private String name;
	private int date;
	private int time;
	private int transport;
	private int from;
	private String fromName;
	private String fromAddress;
	private float fromX;
	private float fromY;
	
	private int to;
	private String toName;
	private String toAddress;
	private float toX;
	private float toY;	
	private int estimatedTime;

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public float getFromX() {
		return fromX;
	}

	public void setFromX(float fromX) {
		this.fromX = fromX;
	}

	public float getFromY() {
		return fromY;
	}

	public void setFromY(float fromY) {
		this.fromY = fromY;
	}

	public String getToName() {
		return toName;
	}

	public void setToName(String toName) {
		this.toName = toName;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public float getToX() {
		return toX;
	}

	public void setToX(float toX) {
		this.toX = toX;
	}

	public float getToY() {
		return toY;
	}

	public void setToY(float toY) {
		this.toY = toY;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getTransport() {
		return transport;
	}

	public void setTransport(int transport) {
		this.transport = transport;
	}

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getTo() {
		return to;
	}

	public void setTo(int to) {
		this.to = to;
	}

	public int getEstimatedTime() {
		return estimatedTime;
	}

	public void setEstimatedTime(int estimatedTime) {
		this.estimatedTime = estimatedTime;
	}

}
