package com.example.microservice.service.reports;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface BetReportService {
	
public Map<String, String> getTotalInvestmentByBetType();
	
	public Map<String, String> getTotalInvestmentByCustomerId();
	
	public Map<String, String> getTotalBetsByBetType();
	
	public Map<String, String> getTotalBetsPerHour();
	
}
