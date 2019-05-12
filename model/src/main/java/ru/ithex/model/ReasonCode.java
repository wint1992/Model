package ru.ithex.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;

@Entity
@Table(name = "reason_code")
@XmlRootElement(name = "ReasonCode")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReasonCode implements Externalizable {
	private static final long serialVersionUID = 1l;
	transient protected TransformData td = new TransformData();

	public ReasonCode() {
		super();
	}

	@Id
	@SequenceGenerator(name = "seq_gen", sequenceName = "reason_code_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen")
	@Column(name = "reason_code_id")
	@XmlAttribute
	protected Integer reasonCodeID;

	@Column(name = "fired_timestamp")
	@XmlAttribute
	@XmlSchemaType(name = "dateTime")
	protected Date firedTimestamp;

	@Column(name = "decision_reason_code")
	@XmlAttribute
	protected String decisionReasonCode;

	@Column(name = "decision_reason_value")
	@XmlAttribute
	protected int decisionReasonValue;

	public Integer getReasonCodeID() {
		return reasonCodeID;
	}

	public void setReasonCodeID(Integer reasonCodeID) {
		this.reasonCodeID = reasonCodeID;
	}

	public Date getFiredTimestamp() {
		return firedTimestamp;
	}

	public void setFiredTimestamp(Date firedTimestamp) {
		this.firedTimestamp = firedTimestamp;
	}

	public String getDecisionReasonCode() {
		return decisionReasonCode;
	}

	public void setDecisionReasonCode(String decisionReasonCode) {
		this.decisionReasonCode = decisionReasonCode;
	}

	public int getDecisionReasonValue() {
		return decisionReasonValue;
	}

	public void setDecisionReasonValue(int decisionReasonValue) {
		this.decisionReasonValue = decisionReasonValue;
	}

	public void writeExternal(ObjectOutput out) throws IOException {
		td.writeNullableObject(out, reasonCodeID);
		td.writeNullableObject(out, firedTimestamp);
		td.writeNullableObject(out, decisionReasonCode);
		out.writeInt(decisionReasonValue);
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		reasonCodeID = in.readBoolean() == true ? in.readInt() : null;
		firedTimestamp = in.readBoolean() == true ? new Date(in.readLong()) : null;
		decisionReasonCode = in.readBoolean() == true ? in.readUTF() : null;
		decisionReasonValue = in.readInt();
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