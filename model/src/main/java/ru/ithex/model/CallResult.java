package ru.ithex.model;

import static ru.ithex.model.TransformData.bigDecimalToInteger;
import static ru.ithex.model.TransformData.transformTimestamp;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CallResult {

	public CallResult() {
		super();
	}

	protected Integer callResultID;
	protected Integer callType;
	protected Integer isActive;
	protected Date callDateTime;
	protected Integer callDecision;

	protected Set<ReasonCode> reasonCodes;
	protected Set<NextStep> nextSteps;
	protected boolean fake;

	public Set<ReasonCode> getReasonCodes() {
		if (reasonCodes == null) {
			reasonCodes = new HashSet<ReasonCode>();
		}
		return this.reasonCodes;
	}

	public Set<NextStep> getNextSteps() {
		if (nextSteps == null) {
			nextSteps = new HashSet<NextStep>();
		}
		return this.nextSteps;
	}

	public Integer getCallResultID() {
		return callResultID;
	}

	public void setCallResultID(Integer value) {
		this.callResultID = value;
	}

	public Integer getCallType() {
		return callType;
	}

	public void setCallType(Integer value) {
		this.callType = value;
	}

	public void setCallType(BigDecimal value) {
		this.callType = bigDecimalToInteger(value);
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer value) {
		this.isActive = value;
	}

	public void setIsActive(BigDecimal value) {
		this.isActive = bigDecimalToInteger(value);
	}

	public Integer getCallDecision() {
		return callDecision;
	}

	public void setCallDecision(Integer value) {
		this.callDecision = value;
	}

	public void setCallDecision(BigDecimal value) {
		this.callDecision = bigDecimalToInteger(value);
	}

	public Date getCallDateTime() {
		return callDateTime;
	}

	public void setCallDateTime(Date value) {
		this.callDateTime = value;
	}

	public void setReasonCodes(Set<ReasonCode> reasonCodes) {
		this.reasonCodes = reasonCodes;
	}

	public void setNextSteps(Set<NextStep> nextSteps) {
		this.nextSteps = nextSteps;
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