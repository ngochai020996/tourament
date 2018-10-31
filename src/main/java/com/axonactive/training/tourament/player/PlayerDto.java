/**
 * 
 */
package com.axonactive.training.tourament.player;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author htnguyen
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@AllArgsConstructor
@NoArgsConstructor
public @Data class PlayerDto implements Serializable {
	
	@NotNull(message = "{player.name.notNull.message}")
	private String name;
	
	@NotNull
	private String socialInsurranceNumber;
	
	@NotNull
	private int number;
	
	private String dateOfBirth;
	
	@NotNull
	private Gender gender;
	
	@NotNull
	private Integer teamId;
	
}
