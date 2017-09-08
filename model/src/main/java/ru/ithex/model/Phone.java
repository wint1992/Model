package ru.ithex.model;

import static ru.ithex.model.TransformData.bigDecimalToInteger;
import static ru.ithex.model.TransformData.transformTimestamp;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Phone {

	public Phone() {
		super();
	}

	protected Integer phoneID;
	protected Integer phoneType;
	protected String phoneNumber;
	protected Integer isActual;
	protected Integer callsCount;
	protected Integer contactsCount;
	protected Date phoneContactDate;
	protected String operator;
	protected boolean fake;

	public Integer getPhoneID() {
		return phoneID;
	}

	public void setPhoneID(Integer value) {
		this.phoneID = value;
	}

	public Integer getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(Integer value) {
		this.phoneType = value;
	}

	public void setPhoneType(BigDecimal value) {
		this.phoneType = bigDecimalToInteger(value);
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String value) {
		this.phoneNumber = value;
	}

	public Integer getIsActual() {
		return isActual;
	}

	public void setIsActual(Integer value) {
		this.isActual = value;
	}

	public void setIsActual(BigDecimal value) {
		this.isActual = bigDecimalToInteger(value);
	}

	public Integer getCallsCount() {
		return callsCount;
	}

	public void setCallsCount(Integer value) {
		this.callsCount = value;
	}

	public void setCallsCount(BigDecimal value) {
		this.callsCount = bigDecimalToInteger(value);
	}

	public Integer getContactsCount() {
		return contactsCount;
	}

	public void setContactsCount(Integer value) {
		this.contactsCount = value;
	}

	public void setContactsCount(BigDecimal value) {
		this.contactsCount = bigDecimalToInteger(value);
	}

	public Date getPhoneContactDate() {
		return phoneContactDate;
	}

	public void setPhoneContactDate(Date value) {
		this.phoneContactDate = value;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String value) {
		this.operator = value;
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
								.append(transformTimestamp((Date) fields[i].get(this)).toString()).append("\"");
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