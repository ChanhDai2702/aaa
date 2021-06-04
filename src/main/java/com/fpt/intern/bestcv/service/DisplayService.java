package com.fpt.intern.bestcv.service;

import java.util.List;

import com.fpt.intern.bestcv.entity.Display;


public interface DisplayService {
	void addDisplay(Display display);
	
	List<Display> listDisplay();
	
	void deleteDisplay(int id);
	
	Display updateDisplay(Display display);
	
	Display findDisplayById(int id);
	
	void ChangeDisplay(int id);
	
	Display findDisplayWasChoose();
	
	void changeFalseActiveDisplay();
}
