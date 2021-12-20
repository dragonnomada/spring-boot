package com.example.cont;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/counter")
public class CounterApiController {

	@Autowired
	CounterService counterService;
	
	@PostMapping("/increment")
	public List<Counter> incrementCounters(@RequestParam String namespace) {
		return counterService.incrementCounters(namespace);
	}
	
	@PostMapping("/decrement")
	public List<Counter> decrementCounters(@RequestParam String namespace) {
		return counterService.decrementCounters(namespace);
	}
	
	@PostMapping("/reset")
	public List<Counter> resetCounters(@RequestParam String namespace) {
		return counterService.resetCounters(namespace);
	}
	
	
}
