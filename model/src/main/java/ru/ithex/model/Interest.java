package ru.ithex.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

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

@Entity
@Table(name = "interest")
@XmlRootElement(name = "Interest")
@XmlAccessorType(XmlAccessType.FIELD)
public class Interest implements Externalizable {
	private static final long serialVersionUID = 1l;
	transient protected TransformData td = new TransformData();

	public Interest() {
		super();
	}

	@Id
	@SequenceGenerator(name = "seq_gen", sequenceName = "interest_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen")
	@Column(name = "interest_id")
	@XmlAttribute
	protected Integer interestID;

	@Column(name = "interest_type")
	@XmlAttribute
	protected int interestType;

	@Column(name = "interest_value")
	@XmlAttribute
	protected String interestValue;

	public Integer getInterestID() {
		return interestID;
	}

	public void setInterestID(Integer interestID) {
		this.interestID = interestID;
	}

	public int getInterestType() {
		return interestType;
	}

	public void setInterestType(int interestType) {
		this.interestType = interestType;
	}

	public String getInterestValue() {
		return interestValue;
	}

	public void setInterestValue(String interestValue) {
		this.interestValue = interestValue;
	}

	public void writeExternal(ObjectOutput out) throws IOException {
		td.writeNullableObject(out, interestID);
		td.writeNullableObject(out, interestType);
		td.writeNullableObject(out, interestValue);
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		interestID = in.readBoolean() == true ? in.readInt() : null;
		interestType = in.readBoolean() == true ? in.readInt() : null;
		interestValue = in.readBoolean() == true ? in.readUTF() : null;
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
	// .append(transformDate((Date) fields[i].get(this)).toString()).append("\"");
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