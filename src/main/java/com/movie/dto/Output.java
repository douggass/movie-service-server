package com.movie.dto;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@XmlRootElement
public class Output {

	@XmlAttribute(name = "payload length")
	public int length;

	@XmlElement(name = "payload")
	public String payload;

}
