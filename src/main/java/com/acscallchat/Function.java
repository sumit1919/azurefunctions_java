package com.acscallchat;

import com.acscallchat.model.RequestObject;
import com.acscallchat.model.ResponseObject;
import com.acscallchat.service.CallChatService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import java.util.Optional;

/**
 * Azure Functions with HTTP Trigger.
 */
public class Function {
	/**
	 * This function listens at endpoint "/api/HttpExample". Two ways to invoke it
	 * using "curl" command in bash: 1. curl -d "HTTP Body" {your
	 * host}/api/HttpExample 2. curl "{your host}/api/HttpExample?name=HTTP%20Query"
	 */
	@FunctionName("participantDetails")
	public HttpResponseMessage run(@HttpTrigger(name = "req", methods = { HttpMethod.GET,
			HttpMethod.POST }, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
			final ExecutionContext context) {
		context.getLogger().info("Java HTTP trigger processed a request.");
		

		// Parse query parameter
		
		final String query = request.getQueryParameters().get("name");
		final String body = request.getBody().orElse(query);
		final Gson gson = new GsonBuilder().setPrettyPrinting().create();

		if (body == null) {
			return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
					.body("Please pass a json with right parameters in request body").build();
		} else {
			CallChatService callChatService =  new CallChatService();
			try {
				RequestObject reqObject = gson.fromJson(body, RequestObject.class);
				
				context.getLogger().info(reqObject.toString());
				
				ResponseObject resObject =  callChatService.processRequest(reqObject);
				String response = gson.toJson(resObject); 
				return request.createResponseBuilder(HttpStatus.OK).body(response).build();

			} catch (Exception e) {
				return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body(e.getMessage()).build();
			}

		}

	}
}
