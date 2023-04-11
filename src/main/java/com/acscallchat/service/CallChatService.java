package com.acscallchat.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.acscallchat.model.Participant;
import com.acscallchat.model.RequestObject;
import com.acscallchat.model.ResponseObject;

public class CallChatService {

	public ResponseObject processRequest(RequestObject reqObject) throws SQLException {
		String url = "jdbc:sqlserver://acschatcall.database.windows.net:1433;database=acschatcall;user=acschatcall@acschatcall;password=Citi@1234;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
		Connection connect = DriverManager.getConnection(url);
		ResponseObject resObject = new ResponseObject();
		if (reqObject != null) {
			String originatorId = reqObject.getOriginatorId();
			if (originatorId != null) {
				Participant originator = getUserType(connect, originatorId);
				System.out.println("originator ::: "+ originator.toString());

				if (originator != null) {
					resObject.setOriginator(originator);
					if (originator.getParticipantType().equalsIgnoreCase("banker")) {
						resObject.setParticipantList(getResponseForBanker(connect, reqObject));
					} else if (originator.getParticipantType().equalsIgnoreCase("customer")) {
						resObject.setParticipantList(getResponseForCustomer(connect, reqObject));
					}
				} else{
					resObject.setMessage("No user found");
				}

			}
		} else {
			resObject.setMessage("Request parameters are empty");
		}

		return resObject;
	}

	private Participant getUserType(Connection connect, String originatorId) throws SQLException {
		String sql = "select * from dbo.Persons where userid=?";
		PreparedStatement statement = connect.prepareStatement(sql);
		statement.setString(1, originatorId);

		ResultSet rs = statement.executeQuery();
		List<Participant> participantList = new ArrayList<Participant>();
		while (rs.next()) {
			Participant prtcpnt = new Participant();
			prtcpnt.setAcsId(rs.getString("acsid"));
			prtcpnt.setParticipantId(rs.getString("userid"));
			prtcpnt.setParticipantName(rs.getString("username"));
			prtcpnt.setParticipantType(rs.getString("usertype"));
			participantList.add(prtcpnt);
		}
		return participantList.get(0);
	}

	private List<Participant> getResponseForBanker(Connection connect, RequestObject reqObject) throws SQLException {
		List<Participant> participantList = new ArrayList<Participant>();
		if(reqObject.getParticipantName() != null) {
			String sql = "select * from dbo.Threadinfo where bankerUserId=? and customerName=?";
			PreparedStatement statement = connect.prepareStatement(sql);
			statement.setString(1, reqObject.getOriginatorId());
			statement.setString(2, reqObject.getParticipantName());
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				Participant prtcpnt = new Participant();
				prtcpnt.setAcsId(rs.getString("customerAcsId"));
				prtcpnt.setParticipantId(rs.getString("customerUserId"));
				prtcpnt.setParticipantName(rs.getString("customerName"));
				prtcpnt.setParticipantType("customer");
				prtcpnt.setThreadId(rs.getString("threadId"));
				participantList.add(prtcpnt);
			}
		}else {
			String sql = "select * from dbo.Threadinfo where bankerUserId=?";
			PreparedStatement statement = connect.prepareStatement(sql);
			statement.setString(1, reqObject.getOriginatorId());
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				Participant prtcpnt = new Participant();
				prtcpnt.setAcsId(rs.getString("customerAcsId"));
				prtcpnt.setParticipantId(rs.getString("customerUserId"));
				prtcpnt.setParticipantName(rs.getString("customerName"));
				prtcpnt.setParticipantType("customer");
				prtcpnt.setThreadId(rs.getString("threadId"));
				participantList.add(prtcpnt);
			}
		}
		
		return participantList;
	}

	private List<Participant> getResponseForCustomer(Connection connect, RequestObject reqObject) throws SQLException {
		List<Participant> participantList = new ArrayList<Participant>();
		if(reqObject.getParticipantName() != null) {
			String sql = "select * from dbo.Threadinfo where customerUserId=? and bankerName=?";
			PreparedStatement statement = connect.prepareStatement(sql);
			statement.setString(1, reqObject.getOriginatorId());
			statement.setString(2, reqObject.getParticipantName());
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				Participant prtcpnt = new Participant();
				prtcpnt.setAcsId(rs.getString("bankerAcsId"));
				prtcpnt.setParticipantId(rs.getString("bankerUserId"));
				prtcpnt.setParticipantName(rs.getString("bankerName"));
				prtcpnt.setParticipantType("banker");
				prtcpnt.setThreadId(rs.getString("threadId"));
				participantList.add(prtcpnt);
			}
		}else {
			String sql = "select * from dbo.Threadinfo where customerUserId=?";
			PreparedStatement statement = connect.prepareStatement(sql);
			statement.setString(1, reqObject.getOriginatorId());
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				Participant prtcpnt = new Participant();
				prtcpnt.setAcsId(rs.getString("bankeracsid"));
				prtcpnt.setParticipantId(rs.getString("bankerUserId"));
				prtcpnt.setParticipantName(rs.getString("bankerName"));
				prtcpnt.setParticipantType("banker");
				prtcpnt.setThreadId(rs.getString("threadId"));
				participantList.add(prtcpnt);
			}
		}
		return participantList;
	}

}
