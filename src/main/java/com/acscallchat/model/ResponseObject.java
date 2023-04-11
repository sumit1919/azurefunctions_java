package com.acscallchat.model;

import java.util.List;

public class ResponseObject {
	private Participant originator;
	private List<Participant> participantList;
	private Participant selectedParticipant;
	private String message;
	
	public List<Participant> getParticipantList() {
		return participantList;
	}
	public void setParticipantList(List<Participant> participantList) {
		this.participantList = participantList;
	}
	public Participant getSelectedParticipant() {
		return selectedParticipant;
	}
	public void setSelectedParticipant(Participant selectedParticipant) {
		this.selectedParticipant = selectedParticipant;
	}
	public Participant getOriginator() {
		return originator;
	}
	public void setOriginator(Participant originator) {
		this.originator = originator;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResponseObject [originator=");
		builder.append(originator);
		builder.append(", participantList=");
		builder.append(participantList);
		builder.append(", selectedParticipant=");
		builder.append(selectedParticipant);
		builder.append(", message=");
		builder.append(message);
		builder.append("]");
		return builder.toString();
	}
	
	
	

}
