package com.example.cont;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class CounterViewController {

	@Autowired
	CounterService counterSevice;
	
	@ModelAttribute("counters")
	public List<Counter> getCounters() {
		return counterSevice.getCounters();
	}
	
	@GetMapping("/")
	public String getCounterHomeView() {
		return "ContadorHome";
	}
	
}
