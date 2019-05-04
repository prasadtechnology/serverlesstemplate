package com.serverless.car;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.serverless.ApiGatewayResponse;
import com.serverless.model.Car;
import com.serverless.util.Util;

public class CreateCarHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

	private final Logger log = LogManager.getLogger(this.getClass());
	private final Gson gson = new Gson();

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {

		try {
			Car car = gson.fromJson((String) input.get("body"), Car.class);
			DynamoDBMapper dbMapper = Car.dynamoDBMapper;
			log.info(" car is saving ...");
			dbMapper.save(car);
			return Util.getGatewayResponse(200, gson.toJson(car), "saved suuccessfully ...");
		}catch(Exception e) {
			log.error(" got an error : ",e);
			return Util.getGatewayResponse(500, "{\"message\":\"something went wrong\"}", e.getMessage());
		}
	}

}
