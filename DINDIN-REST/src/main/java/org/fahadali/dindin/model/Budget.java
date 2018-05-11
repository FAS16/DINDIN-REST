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

	public String getValue() {
		return this.name();
	}

}
