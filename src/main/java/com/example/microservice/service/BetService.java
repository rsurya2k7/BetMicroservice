package com.example.microservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.microservice.model.Bet;
import com.example.microservice.repository.BetRepository;

@Service
public class BetService {
	@Autowired
	private BetRepository betRepository;

	public BetService(BetRepository betRepository) {
		super();
		this.betRepository = betRepository;
	}

	public List<Bet> list() {
		return betRepository.findAll();
	}

	public Bet save(Bet bet) {
		return betRepository.save(bet);
	}

	public Iterable<Bet> save(List<Bet> bets) {
		return betRepository.saveAll(bets);
	}
	
	public List<Bet> listByBetType() {
		return betRepository.findAll(new Sort(Sort.Direction.ASC, "betType") );
	}

	
}
