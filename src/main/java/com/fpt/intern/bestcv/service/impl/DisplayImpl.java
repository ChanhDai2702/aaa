package com.fpt.intern.bestcv.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.intern.bestcv.entity.Display;
import com.fpt.intern.bestcv.reposistory.DisplayRepository;
import com.fpt.intern.bestcv.service.DisplayService;


@Service
public class DisplayImpl implements DisplayService{

	@Autowired
	private DisplayRepository displayRepository;
	
	@Override
	public void addDisplay(Display display) {
		display.setIsActive(false);
		displayRepository.save(display);
	}

	@Override
	public List<Display> listDisplay() {
		return (List<Display>) displayRepository.findAll();
	}

	@Override
	public void deleteDisplay(int id) {
		displayRepository.deleteById(id);
	}

	@Override
	public Display updateDisplay(Display display) {
		Display displayCurrent = displayRepository.findById(display.getId()).get();
		if(display.getIsActive() != null) {
			displayCurrent.setIsActive(display.getIsActive());
		}
		if(display.getBackgroundColor().trim().isEmpty() == false) {
			displayCurrent.setBackgroundColor(display.getBackgroundColor());
		}
		if(display.getDisplayName().trim().isEmpty() == false) {
			displayCurrent.setDisplayName(display.getDisplayName());
		}
		if(display.getFont().trim().isEmpty() == false) {
			displayCurrent.setFont(display.getFont());
		}
		if(display.getFontColor().trim().isEmpty() == false) {
			displayCurrent.setFontColor(display.getFontColor());
		}
		displayCurrent.setFontSize(display.getFontSize());
		if(display.getImageBanner() != null && display.getImageBanner().trim().isEmpty() == false) {
			displayCurrent.setImageBanner(display.getImageBanner());
		}
		if(display.getImageReview() != null && display.getImageReview().trim().isEmpty() == false) {
			displayCurrent.setImageReview(display.getImageReview());
		}
		if(display.getNavColor() != null && display.getNavColor().trim().isEmpty() == false) {
			displayCurrent.setNavColor(display.getNavColor());
		}
		displayRepository.save(displayCurrent);
		return displayCurrent;
	}

	@Override
	public Display findDisplayById(int id) {
		Optional<Display> findDisOptional = displayRepository.findById(id);
		return findDisOptional.isPresent() == true ? findDisOptional.get() : null;
	}

	@Override
	public void ChangeDisplay(int id) {
		changeFalseActiveDisplay();
		displayRepository.activeDisplayById(id);
	}

	@Override
	public Display findDisplayWasChoose() {
		List<Display> listDisplays = displayRepository.findDisplayIsActive();
		return listDisplays.size() >= 1 ? listDisplays.get(0) : null;
	}

	@Override
	public void changeFalseActiveDisplay() {
		displayRepository.setActiveToFalse();
	}

}
