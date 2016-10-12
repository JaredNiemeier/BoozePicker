package edu.bsu.cs222.boozepicker;

import java.math.BigDecimal;

public class Special {

	public static final class Builder {
		private String nameOfAlcohol;
		private String priceOfAlcohol;
		private String typeOfAlcohol;

		public void setNameOfAlcohol(String name) {
			nameOfAlcohol = name;
		}

		public void setPriceOfAlcohol(String price) {
			priceOfAlcohol = price;
		}

		public void setTypeOfAlcohol(String type) {
			typeOfAlcohol = type;
		}

		public Special build() {
			return new Special(this);
		}
	}

	private String nameOfAlcohol = "";
	private BigDecimal priceOfAlcohol;
	private String typeOfAlcohol = "";

	public Special(Builder builder) {
		nameOfAlcohol = builder.nameOfAlcohol;
		priceOfAlcohol = new BigDecimal(builder.priceOfAlcohol).setScale(2);
		typeOfAlcohol = builder.typeOfAlcohol;
	}

	public String getNameOfAlcohol() {
		return nameOfAlcohol;
	}

	public BigDecimal getPriceOfAlcohol() {
		return priceOfAlcohol;
	}

	public String getTypeOfAlcohol() {
		return typeOfAlcohol;
	}
}