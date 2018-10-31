package com.axonactive.training.tourament.player;

import lombok.Getter;

public enum Gender {
	MALE(1),
	FEMALE(0),
	UNKNOWN(-1);
	
	private @Getter int value;
	
	private Gender(int value) {
		this.value = value;
	}
}
