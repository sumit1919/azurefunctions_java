package com.acscallchat.model;

public class Participant {
	private String participantName; 
	private String participantId;
	private String acsId;
	private String threadId;
	private String participantType;
	
	
	public String getParticipantName() {
		return participantName;
	}
	public void setParticipantName(String participantName) {
		this.participantName = participantName;
	}
	public String getParticipantId() {
		return participantId;
	}
	public void setParticipantId(String participantId) {
		this.participantId = participantId;
	}
	public String getThreadId() {
		return threadId;
	}
	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}
	
	public String getAcsId() {
		return acsId;
	}
	public void setAcsId(String acsId) {
		this.acsId = acsId;
	}
	public String getParticipantType() {
		return participantType;
	}
	public void setParticipantType(String participantType) {
		this.participantType = participantType;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Participant [participantName=");
		builder.append(participantName);
		builder.append(", participantId=");
		builder.append(participantId);
		builder.append(", acsId=");
		builder.append(acsId);
		builder.append(", threadId=");
		builder.append(threadId);
		builder.append(", participantType=");
		builder.append(participantType);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
