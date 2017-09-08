package ru.ithex.model;

import static ru.ithex.model.TransformData.bigDecimalToInteger;
import static ru.ithex.model.TransformData.transformTimestamp;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class RequestedInfo {

	public RequestedInfo() {
		super();
	}

	protected Date requestTimestamp;
	protected Integer stepCode;
	protected Date currentDate;
	protected Integer stepParameter;
	protected boolean fake;

	public Date getRequestTimestamp() {
		return requestTimestamp;
	}

	public void setRequestTimestamp(Date value) {
		this.requestTimestamp = value;
	}

	public Integer getStepCode() {
		return stepCode;
	}

	public void setStepCode(Integer value) {
		this.stepCode = value;
	}

	public void setStepCode(BigDecimal value) {
		this.stepCode = bigDecimalToInteger(value);
	}

	public Integer getStepParameter() {
		return stepParameter;
	}

	public void setStepParameter(Integer value) {
		this.stepParameter = value;
	}

	public void setStepParameter(BigDecimal value) {
		this.stepParameter = bigDecimalToInteger(value);
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date value) {
		this.currentDate = value;
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