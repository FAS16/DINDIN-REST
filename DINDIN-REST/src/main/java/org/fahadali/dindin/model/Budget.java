package org.fahadali.dindin.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import com.fasterxml.jackson.annotation.JsonValue;

@XmlRootElement
public enum Budget implements Serializable {

	LOW, MEDIUM, HIGH;

	private Budget() {

	}
	
	//@JsonValue
	public String getValue() {
		return this.name();
	}
	
	public static void main(String[] args) {
		String l = "LOW";
		String m = "MEDIUM";
		String h = "HIGH";
		
		
		
		
		Budget b = Budget.valueOf(l);
		
	System.out.println(b.getValue());
		
		System.out.println(b);
		System.out.println(Budget.valueOf(m));
		System.out.println(Budget.valueOf(h));
	}

}
