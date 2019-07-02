package com.example.microservice.validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.microservice.model.Bet;

@Component
public class BetValidator {
	
	ErrorDetails responseDetails = new ErrorDetails();
	
	double investmentAmtLimit = 20000;
	
	String[] betTypeList = {"WIN","PLACE","TRIFECTA","DOUBLE", "QUADDIE"};
	
	public List<ErrorDetails> isRequestValid(Bet bet) {
		
		List<ErrorDetails> validationMsg = new ArrayList<ErrorDetails>();
		//Check the Bet is empty or not
		if(bet==null) {
			validationMsg.add(new ErrorDetails("Input request (Bet) is empty"));
		} else {
			//Maximum amount should not be greater than 20000
			if(bet.getInvestmentAmt() > investmentAmtLimit)
				validationMsg.add(new ErrorDetails("Investment amount should be less than $20000"));
			
			//Validating for the bet type.
			boolean validBetType = Arrays.stream(betTypeList).anyMatch(bet.getBetType()::equals);
			if(!validBetType)
				validationMsg.add(new ErrorDetails("Bet Type can be one of the following:WIN / PLACE / TRIFECTA / DOUBLE / QUADDIE"));
				
			//Date validation
			if(Calendar.getInstance().getTime().after(bet.getDateTime())) {
//				validationMsg.add(new ErrorDetails("Date Time must not be in the past current time" + Calendar.getInstance().getTime()));
				validationMsg.add(new ErrorDetails("Date Time must not be in the past current time"));
			}

		}
		
		return validationMsg;
	}
	
//	public String isValid(Bet bet) {
//		return getValidationMsgListInStr(isRequestValid(bet));
//	}
//	
//	private String getValidationMsgListInStr(List<ErrorDetails> validationMsg) {
//		StringBuilder validationMsgStr = new StringBuilder();
//		
//		if(validationMsg!=null && validationMsg.size() > 0) {
//			for(ErrorDetails validMsg : validationMsg)
//			validationMsgStr.append(validMsg.getErrorMessage() + "/n");
//		}
//		
//		return validationMsgStr.toString();
//	}

}
