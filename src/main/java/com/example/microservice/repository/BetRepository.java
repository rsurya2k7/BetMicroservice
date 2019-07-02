package com.example.microservice.repository;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.microservice.model.Bet;

public interface BetRepository extends JpaRepository<Bet, Long> {

	@Query(value = "select new Map(hour(b.dateTime) as hour,count(b) as cnt) from Bet b group by FUNCTION('DATEDIFF','HOUR', b.dateTime,now())")
	public Map<String, String> getTotalBetsPerHour();
			

//@Query(value = "select new Map(day (v.dateTime) as day, hour(v.dateTime) as hour, count(v) as cnt) from Bet v group by day, hour")
//@Query(value = "select new Map(count(v) as cnt, dateTime) from Bet v group by DATEDIFF(HOUR, dateTime, now())")
//@Query(value = "select new Map(count(v) as cnt, dateTime) from Bet v group by dateTime")
//@Query(value = "select new Map(day (v.dateTime) as day, hour(v.dateTime) as hour, count(v) as cnt) from Bet v group by day, hour")
}
