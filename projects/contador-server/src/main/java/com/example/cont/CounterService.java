package com.example.cont;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CounterService {

	List<Counter> counters = new ArrayList<Counter>();
	
	public CounterService() {
		super();
		
		counters.add(Counter.builder().id((long)1).namespace("A").value(4).build());
		counters.add(Counter.builder().id((long)2).namespace("A").value(8).build());
		counters.add(Counter.builder().id((long)3).namespace("B").value(0).build());
		counters.add(Counter.builder().id((long)4).namespace("C").value(0).build());
		counters.add(Counter.builder().id((long)5).namespace("B").value(0).build());
	}
	
	public List<Counter> getCounters() {
		return counters;
	}
	
	public List<Counter> incrementCounters(String namespace) {
		List<Counter> updatedCounters = new ArrayList<Counter>();
		
		for (Counter counter : counters) {
			if (counter.getNamespace().equals(namespace)) {
				counter.setValue(counter.getValue() + 1);
				updatedCounters.add(counter);
			}
		}
		
		return updatedCounters;
	}
	
	public List<Counter> decrementCounters(String namespace) {
		List<Counter> updatedCounters = new ArrayList<Counter>();
		
		for (Counter counter : counters) {
			if (counter.getNamespace().equals(namespace)) {
				counter.setValue(counter.getValue() - 1);
				updatedCounters.add(counter);
			}
		}
		
		return updatedCounters;
	}
	
	public List<Counter> resetCounters(String namespace) {
		List<Counter> updatedCounters = new ArrayList<Counter>();
		
		for (Counter counter : counters) {
			if (counter.getNamespace().equals(namespace)) {
				counter.setValue(0);
				updatedCounters.add(counter);
			}
		}
		
		return updatedCounters;
	}
	
}
