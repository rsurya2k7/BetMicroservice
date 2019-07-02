package com.example.microservice;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.microservice.model.Bet;
import com.example.microservice.validator.BetValidator;
import com.example.microservice.validator.ErrorDetails;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { com.example.microservice.validator.BetValidator.class})
public class BetServiceTest {

	@Autowired
	BetValidator betValidator;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	/*@Test
	public void testInsertBet() throws Exception {
		Bet bet = createValidBetObject();
		Bet insertedBet = betService.save(bet);
		boolean betObjectValid = insertedBet.equals(bet);
		assertEquals(true, betObjectValid);
	}*/

	/*
	 * @Test public void testInsertBet() throws Exception { Bet bet =
	 * createValidBetObject(); Bet insertedBet = betService.save(bet); boolean
	 * betObjectValid = insertedBet.equals(bet); assertEquals(true, betObjectValid);
	 * }
	 */

	@Test
	public void testValidateRequest_Null() throws Exception {
		Bet bet = null;
		List<ErrorDetails> validationDetails = betValidator.isRequestValid(bet);
		assertEquals("Input request (Bet) is empty", validationDetails.get(0).getMessage());
	}

	@Test
	public void testValidateRequest_BetTypeInvalid() throws Exception {
		Bet bet = createValidBetObject();
		bet.setBetType("WINss");
		List<ErrorDetails> validationDetails = betValidator.isRequestValid(bet);
		assertEquals("Bet Type can be one of the following:WIN / PLACE / TRIFECTA / DOUBLE / QUADDIE",
				validationDetails.get(0).getMessage());
	}

	@Test
	public void testValidateRequest_InvestmentAmtGreaterthanLimit() throws Exception {
		Bet bet = createValidBetObject();
		bet.setInvestmentAmt(300000);
		List<ErrorDetails> validationDetails = betValidator.isRequestValid(bet);
		assertEquals("Investment amount should be less than $20000", validationDetails.get(0).getMessage());
	}

	@Test
	public void testValidateRequest_OldDate() throws Exception {
		Bet bet = createValidBetObject();
		bet.setDateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("2010-10-10 10:10"));
		List<ErrorDetails> validationDetails = betValidator.isRequestValid(bet);
		assertEquals("Date Time must not be in the past current time", validationDetails.get(0).getMessage());
	}

	public Bet createValidBetObject() throws ParseException {
		Bet bet = new Bet();
		bet.setBetType("WIN");
		bet.setCustomerId(123);
		bet.setInvestmentAmt(10000.00);
		bet.setPropNumber(223344);
		bet.setDateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("2020-10-10 10:10"));
		return bet;
	}

}
