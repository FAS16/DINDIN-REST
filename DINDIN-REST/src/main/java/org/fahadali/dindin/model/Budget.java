package org.fahadali.dindin.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/*
 * Problem med at JSON og ENUM ikke arbejder så godt sammen er løst, der manglede blot getter og setter i User klassen
 */

@XmlRootElement
public enum Budget /* implements Serializable */ {

	LOW, MEDIUM, HIGH;

	private Budget() {

	}
	
	//Transient - Jsonignore

	// @JsonValue
	public String getValue() {
		return this.name();
	}

	//Test
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
