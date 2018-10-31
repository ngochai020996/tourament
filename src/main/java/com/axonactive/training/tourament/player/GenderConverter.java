/**
 * 
 */
package com.axonactive.training.tourament.player;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author htnguyen
 *
 */
@Converter
public class GenderConverter implements AttributeConverter<Gender, Integer> {

	@Override
	public Integer convertToDatabaseColumn(Gender gender) {
		return gender.getValue();
	}

	@Override
	public Gender convertToEntityAttribute(Integer dbData) {
		switch(dbData) {
		case -1:
			return Gender.UNKNOWN;
		case 0:
			return Gender.FEMALE;
		case 1:
			return Gender.MALE;
		default:
			throw new IllegalStateException("Possible values: -1, 0, 1.");
			
		}
	}
	
}
