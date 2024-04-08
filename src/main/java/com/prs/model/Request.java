package com.prs.model;

import java.time.*;


import jakarta.persistence.*;

@Entity
public class Request {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "UserId")
	private User user;
	private String description;
	private String justification;
	private LocalDate dateneeded;
	private String deliverymode;
	private String status;
	private float total;
	private LocalDateTime submitteddate;
	private String reasonforrejection;
	public Request() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Request(int id, User user, String description, String justification, LocalDate dateneeded,
			String deliverymode, String status, float total, LocalDateTime submitteddate, String reasonforrejection) {
		super();
		this.id = id;
		this.user = user;
		this.description = description;
		this.justification = justification;
		this.dateneeded = dateneeded;
		this.deliverymode = deliverymode;
		this.status = status;
		this.total = total;
		this.submitteddate = submitteddate;
		this.reasonforrejection = reasonforrejection;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getJustification() {
		return justification;
	}
	public void setJustification(String justification) {
		this.justification = justification;
	}
	public LocalDate getDateneeded() {
		return dateneeded;
	}
	public void setDateneeded(LocalDate dateneeded) {
		this.dateneeded = dateneeded;
	}
	public String getDeliverymode() {
		return deliverymode;
	}
	public void setDeliverymode(String deliverymode) {
		this.deliverymode = deliverymode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public LocalDateTime getSubmitteddate() {
		return submitteddate;
	}
	public void setSubmitteddate(LocalDateTime submitteddate) {
		this.submitteddate = submitteddate;
	}
	public String getReasonforrejection() {
		return reasonforrejection;
	}
	public void setReasonforrejection(String reasonforrejection) {
		this.reasonforrejection = reasonforrejection;
	}
	@Override
	public String toString() {
		return "Request [id=" + id + ", user=" + user + ", description=" + description + ", justification="
				+ justification + ", dateneeded=" + dateneeded + ", deliverymode=" + deliverymode + ", status=" + status
				+ ", total=" + total + ", submitteddate=" + submitteddate + ", reasonforrejection=" + reasonforrejection
				+ "]";
	}


	
}