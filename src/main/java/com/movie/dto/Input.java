package com.movie.dto;

import javax.xml.bind.annotation.XmlAttribute;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Input {

	@XmlAttribute(name = "query length")
	private Integer length;

	@XmlAttribute(name = "query")
	private String query;

}
