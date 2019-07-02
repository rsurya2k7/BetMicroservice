package com.example.microservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.microservice.model.Bet;
import com.example.microservice.service.BetService;
import com.example.microservice.service.reports.BetReportService;
import com.example.microservice.validator.BetValidator;
import com.example.microservice.validator.ErrorDetails;

@RestController
@RequestMapping("/rest/")
public class BetResourceController {

	@Autowired
	private BetService betService;
	
	@Autowired
	private BetReportService betReportService;

	@Autowired
	private BetValidator betServiceValidator;
	
	public BetResourceController(BetService betService) {
		super();
		this.betService = betService;
	}

//	@PostMapping(path = "/bet", produces = "application/json", consumes = "application/json")
//	public List<Bet> addBets(@RequestBody final Bet bet) {
//		
//		
//		betService.save(bet);
//		return betService.list();
//	}
	
	@PostMapping(path = "/bet", produces = "application/json", consumes = "application/json")
	public ResponseEntity<List> addBets(@RequestBody final Bet bet) {
		ErrorDetails successResponseDetails = new ErrorDetails("Successfully inserted");
		
		List<ErrorDetails> msgList = new ArrayList<ErrorDetails>();
		
		msgList.add(successResponseDetails);
		
		List<ErrorDetails> validationMsg = betServiceValidator.isRequestValid(bet);
		
		if(validationMsg.isEmpty()) {
			betService.save(bet);
			return new ResponseEntity<List>(msgList, HttpStatus.OK);
		} else {
			 return ResponseEntity
			            .status(HttpStatus.BAD_REQUEST)                 
			            .body(validationMsg);
		}
	}
	
	@GetMapping("/betlist")
	public Iterable<Bet> list() {
		return betService.list();
	}
	
	@GetMapping("/report/ByBetType")
	public Map<String, String> reportByBetType() {
		return betReportService.getTotalInvestmentByBetType();
	}
	
	
	@GetMapping("/report/ByCustomer")
	public Map<String, String> reportByCustomerId() {
		return betReportService.getTotalInvestmentByCustomerId();
	}
	
	@GetMapping("/report/totalBetsByBetType")
	public Map<String, String> reportForTotalBetsByBetType() {
		return betReportService.getTotalBetsByBetType();
	}
	
	@GetMapping("/report/totalBetsPerHour")
	public Map<String, String> reportForTotalBetsPerHour() {
		return betReportService.getTotalBetsPerHour();
	}
	
}
