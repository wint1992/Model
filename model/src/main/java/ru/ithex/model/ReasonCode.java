package ru.ithex.model;

import static ru.ithex.model.TransformData.transformTimestamp;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class ReasonCode {

	public ReasonCode() {
		super();
	}

	protected Integer reasonCodeID;
	protected Date firedTimestamp;
	protected String decisionReasonCode;
	protected boolean fake;

	public Integer getReasonCodeID() {
		return reasonCodeID;
	}

	public void setReasonCodeID(Integer value) {
		this.reasonCodeID = value;
	}

	public String getDecisionReasonCode() {
		return decisionReasonCode;
	}

	public void setDecisionReasonCode(String value) {
		this.decisionReasonCode = value;
	}

	public Date getFiredTimestamp() {
		return firedTimestamp;
	}

	public void setFiredTimestamp(Date value) {
		this.firedTimestamp = value;
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