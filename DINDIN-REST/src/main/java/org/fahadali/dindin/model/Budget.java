package org.fahadali.dindin.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public enum Budget {
	
	LOW(1), MEDIUM(2), HIGH(3);

	private final int budgetCategory;

	private Budget(int budgetCategory) {
			this.budgetCategory = budgetCategory;
		}

	public int getBudgetCategory() {
		return budgetCategory;
	}

}
