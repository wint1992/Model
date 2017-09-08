package ru.ithex.model;

import static ru.ithex.model.TransformData.bigDecimalToBoolean;
import static ru.ithex.model.TransformData.transformDate;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Document {

	public Document() {
		super();
	}

	protected Integer docID;
	protected Integer docType;
	protected Date issueDate;
	protected String serial;
	protected String number;
	protected String subDivisionCode;
	protected String issuer;
	protected Boolean isOriginal;
	protected Integer amountPages;
	protected boolean fake;

	public Integer getDocID() {
		return docID;
	}

	public void setDocID(Integer value) {
		this.docID = value;
	}

	public Integer getDocType() {
		return docType;
	}

	public void setDocType(Integer value) {
		this.docType = value;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date value) {
		this.issueDate = value;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String value) {
		this.serial = value;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String value) {
		this.number = value;
	}

	public String getSubDivisionCode() {
		return subDivisionCode;
	}

	public void setSubDivisionCode(String value) {
		this.subDivisionCode = value;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String value) {
		this.issuer = value;
	}

	public Boolean isIsOriginal() {
		return isOriginal;
	}

	public void setIsOriginal(Boolean value) {
		this.isOriginal = value;
	}

	public void setIsOriginal(BigDecimal value) {
		this.isOriginal = bigDecimalToBoolean(value);
	}

	public Integer getAmountPages() {
		return amountPages;
	}

	public void setAmountPages(Integer value) {
		this.amountPages = value;
	}

	public Boolean getIsOriginal() {
		return isOriginal;
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