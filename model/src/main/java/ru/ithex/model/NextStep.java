package ru.ithex.model;

import static ru.ithex.model.TransformData.bigDecimalToInteger;
import static ru.ithex.model.TransformData.transformTimestamp;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class NextStep {

	public NextStep() {
		super();
	}

	protected Integer nextStepID;
	protected Integer stepCode;
	protected Integer stepParameter;
	protected Date stepPlaneDate;
	protected Integer stepPriority;
	protected boolean fake;

	public Integer getNextStepID() {
		return nextStepID;
	}

	public void setNextStepID(Integer value) {
		this.nextStepID = value;
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

	public Integer getStepPriority() {
		return stepPriority;
	}

	public void setStepPriority(Integer value) {
		this.stepPriority = value;
	}

	public void setStepPriority(BigDecimal value) {
		this.stepPriority = bigDecimalToInteger(value);
	}

	public Date getStepPlaneDate() {
		return stepPlaneDate;
	}

	public void setStepPlaneDate(Date value) {
		this.stepPlaneDate = value;
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