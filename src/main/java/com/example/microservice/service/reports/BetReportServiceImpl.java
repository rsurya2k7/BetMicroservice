package com.example.microservice.service.reports;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.microservice.repository.BetRepository;
import com.example.microservice.service.BetService;

@Service
public class BetReportServiceImpl implements BetReportService {


	@Autowired
	private BetService betService;
	
	@Autowired
	private BetRepository betRepository;

	@Override
	public Map<String, String> getTotalInvestmentByBetType() {
		
		LinkedHashMap<String, String> results = new LinkedHashMap<String, String>();
		results.put("Bet Type", "Total Investment");

		
		betService.list().stream()
		  .collect(Collectors.groupingBy(bet -> bet.betType, Collectors.summingDouble(bet->bet.investmentAmt)))
		  .forEach((betType,sumTotalInvestment)-> results.put(betType, String.valueOf(sumTotalInvestment)));
		
//		.forEach((betType,sumTotalInvestment)->System.out.println(betType+"\t"+sumTotalInvestment));
		
		return results;

	}

	@Override
	public Map<String, String> getTotalInvestmentByCustomerId() {
		LinkedHashMap<String, String> results = new LinkedHashMap<String, String>();
		results.put("Customer Id", "Total Investment");

		
		betService.list().stream()
		  .collect(Collectors.groupingBy(bet -> bet.customerId, Collectors.summingDouble(bet->bet.investmentAmt)))
		  .forEach((customerId,sumTotalInvestment)-> results.put(String.valueOf(customerId), String.valueOf(sumTotalInvestment)));
		
//		.forEach((customerId,sumTotalInvestment)->System.out.println(customerId+"\t"+sumTotalInvestment));
		
		return results;
	}

	@Override
	public Map<String, String> getTotalBetsByBetType() {
		LinkedHashMap<String, String> results = new LinkedHashMap<String, String>();
		results.put("Bet Type", "Count");

		betService.list().stream()
		  .collect(Collectors.groupingBy(bet -> bet.betType,  Collectors.counting()))
		  .forEach((betType,count)-> results.put(betType, String.valueOf(count)));

		return results;
	}


	public Map<String, String> getTotalBetsPerHour() {
		return betRepository.getTotalBetsPerHour();
	}

}
