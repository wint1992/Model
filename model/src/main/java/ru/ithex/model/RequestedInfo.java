package ru.ithex.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;

@Embeddable
@XmlRootElement(name = "RequestedInfo")
@XmlAccessorType(XmlAccessType.FIELD)
public class RequestedInfo implements Externalizable {
	private static final long serialVersionUID = 1l;
	transient protected TransformData td = new TransformData();

	public RequestedInfo() {
		super();
	}

	@Column(name = "ri_request_timestamp")
	@XmlAttribute
	@XmlSchemaType(name = "dateTime")
	protected Date requestTimestamp;

	@Column(name = "ri_step_code")
	@XmlAttribute
	protected int stepCode;

	@Column(name = "ri_current_date")
	@XmlAttribute
	@XmlSchemaType(name = "date")
	protected Date currentDate;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "client_fk")
	@XmlElementWrapper(name = "StepParametrs")
	@XmlElement(name = "StepParametr")
	protected List<StepParametr> stepParametrs;

	public Date getRequestTimestamp() {
		return requestTimestamp;
	}

	public void setRequestTimestamp(Date requestTimestamp) {
		this.requestTimestamp = requestTimestamp;
	}

	public int getStepCode() {
		return stepCode;
	}

	public void setStepCode(int stepCode) {
		this.stepCode = stepCode;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public List<StepParametr> getStepParametrs() {
		if (stepParametrs == null)
			stepParametrs = new ArrayList<>();
		return stepParametrs;
	}

	public void setStepParametrs(List<StepParametr> stepParametrs) {
		this.stepParametrs = stepParametrs;
	}

	public void writeExternal(ObjectOutput out) throws IOException {
		td.writeNullableObject(out, requestTimestamp);
		td.writeNullableObject(out, stepCode);
		td.writeNullableObject(out, currentDate);

		out.writeInt(getStepParametrs().size());
		for (Externalizable ext : getStepParametrs())
			ext.writeExternal(out);
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		requestTimestamp = in.readBoolean() == true ? new Date(in.readLong()) : null;
		stepCode = in.readBoolean() == true ? in.readInt() : null;
		currentDate = in.readBoolean() == true ? new Date(in.readLong()) : null;

		int count = in.readInt();
		for (int i = 0; i < count; i++) {
			StepParametr ext = new StepParametr();
			ext.readExternal(in);
			getStepParametrs().add(ext);
		}

	}

	// @SuppressWarnings("all")
	// public String toString() {
	// StringBuilder sb = new StringBuilder("{");
	// try {
	// boolean hasFirstProperty = false;
	// Field[] fields = this.getClass().getDeclaredFields();
	// for (int i = 0; i < fields.length - 1; i++) {
	// if (fields[i].get(this) != null) {
	// if (fields[i].getType().equals(String.class)) {
	// if (hasFirstProperty)
	// sb.append(",");
	// sb.append("\"").append(fields[i].getName()).append("\"").append(": \"")
	// .append(fields[i].get(this).toString()).append("\"");
	// hasFirstProperty = true;
	// } else if (fields[i].getType().equals(Date.class)) {
	// if (hasFirstProperty)
	// sb.append(",");
	// sb.append("\"").append(fields[i].getName()).append("\"").append(": \"")
	// .append(transformTimestamp((Date)
	// fields[i].get(this)).toString()).append("\"");
	// hasFirstProperty = true;
	// } else if (fields[i].getType().equals(Set.class) ||
	// fields[i].getType().equals(List.class)) {
	// if (((Collection) fields[i].get(this)).size() > 0) {
	// if (hasFirstProperty)
	// sb.append(",");
	// sb.append("\"").append(fields[i].getName()).append("\"").append(": ")
	// .append(fields[i].get(this).toString());
	// hasFirstProperty = true;
	// }
	// } else {
	// if (hasFirstProperty)
	// sb.append(",");
	// sb.append("\"").append(fields[i].getName()).append("\"").append(": ")
	// .append(fields[i].get(this).toString());
	// hasFirstProperty = true;
	// }
	// }
	// }
	// } catch (Exception e) {
	// sb.append("null");
	// }
	// return sb.append("}").toString();
	// }
}