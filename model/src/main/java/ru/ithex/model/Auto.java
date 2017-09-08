package ru.ithex.model;

import static ru.ithex.model.TransformData.bigDecimalToBoolean;
import static ru.ithex.model.TransformData.bigDecimalToInteger;
import static ru.ithex.model.TransformData.transformDate;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Auto {

	public Auto() {
		super();
	}

	protected Integer autoID;
	protected String make;
	protected String model;
	protected Integer year;
	protected Boolean isCredit;
	protected BigDecimal cost;
	protected String carNumber;
	protected String autoRegSerialNumber;
	protected String vin;
	protected boolean fake;

	public Integer getAutoID() {
		return autoID;
	}

	public void setAutoID(Integer value) {
		this.autoID = value;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String value) {
		this.make = value;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String value) {
		this.model = value;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer value) {
		this.year = value;
	}

	public void setYear(BigDecimal value) {
		this.year = bigDecimalToInteger(value);
	}

	public void setIsCredit(Boolean value) {
		this.isCredit = value;
	}

	public void setIsCredit(BigDecimal value) {
		this.isCredit = bigDecimalToBoolean(value);
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal value) {
		this.cost = value;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String value) {
		this.carNumber = value;
	}

	public String getAutoRegSerialNumber() {
		return autoRegSerialNumber;
	}

	public void setAutoRegSerialNumber(String value) {
		this.autoRegSerialNumber = value;
	}

	public String getVIN() {
		return vin;
	}

	public void setVIN(String value) {
		this.vin = value;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public Boolean getIsCredit() {
		return isCredit;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("{");
		try {
			boolean hasFirstProperty = false;
			Field[] fields = this.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length - 1; i++) {
				if (fields[i].get(this) != null) {
					if (fields[i].getType().equals(String.class)) {
						if (hasFirstProperty)
							sb.append(",");
						sb.append("\"").append(fields[i].getName()).append("\"").append(": \"")
								.append(fields[i].get(this).toString()).append("\"");
						hasFirstProperty = true;
					} else if (fields[i].getType().equals(Date.class)) {
						if (hasFirstProperty)
							sb.append(",");
						sb.append("\"").append(fields[i].getName()).append("\"").append(": \"")
								.append(transformDate((Date) fields[i].get(this)).toString()).append("\"");
						hasFirstProperty = true;
					} else if (fields[i].getType().equals(Set.class) || fields[i].getType().equals(List.class)) {
						if (((Collection) fields[i].get(this)).size() > 0) {
							if (hasFirstProperty)
								sb.append(",");
							sb.append("\"").append(fields[i].getName()).append("\"").append(": ")
									.append(fields[i].get(this).toString());
							hasFirstProperty = true;
						}
					} else {
						if (hasFirstProperty)
							sb.append(",");
						sb.append("\"").append(fields[i].getName()).append("\"").append(": ")
								.append(fields[i].get(this).toString());
						hasFirstProperty = true;
					}
				}
			}
		} catch (Exception e) {
			sb.append("null");
		}
		return sb.append("}").toString();
	}
}