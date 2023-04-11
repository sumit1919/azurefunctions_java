package com.acscallchat.model;

public class RequestObject {
	private String originatorId;
	private String participantName;
	
	
	public String getOriginatorId() {
		return originatorId;
	}
	public void setOriginatorId(String originatorId) {
		this.originatorId = originatorId;
	}
	public String getParticipantName() {
		return participantName;
	}
	public void setParticipantName(String participantName) {
		this.participantName = participantName;
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RequestObject [originatorId=");
		builder.append(originatorId);
		builder.append(", participantName=");
		builder.append(participantName);
		builder.append("]");
		return builder.toString();
	}
	
	
	

}
