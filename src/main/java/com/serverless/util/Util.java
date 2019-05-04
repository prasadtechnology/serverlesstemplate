package com.serverless.util;

import java.util.Collections;

import com.serverless.ApiGatewayResponse;

public class Util {
	
	public static ApiGatewayResponse getGatewayResponse(int statusCode,String data,String message) {
		return ApiGatewayResponse.builder()
  				.setStatusCode(statusCode)
  				.setObjectBody(data)
  				.setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless"))
  				.build();
	}

}
