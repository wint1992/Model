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
@Table(name = "phone")
@XmlRootElement(name = "Phone")
@XmlAccessorType(XmlAccessType.FIELD)
public class Phone implements Externalizable {
	private static final long serialVersionUID = 1l;
	transient protected TransformData td = new TransformData();

	public Phone() {
		super();
	}

	@Id
	@SequenceGenerator(name = "seq_gen", sequenceName = "phone_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen")
	@Column(name = "phone_id")
	@XmlAttribute
	protected Integer phoneID;

	@Column(name = "phone_type")
	@XmlAttribute
	protected int phoneType;

	@Column(name = "phone_number")
	@XmlAttribute
	protected String phoneNumber;

	@Column(name = "is_actual")
	@XmlAttribute
	protected Integer isActual;

	@Column(name = "calls_count")
	@XmlAttribute
	protected Integer callsCount;

	@Column(name = "contacts_count")
	@XmlAttribute
	protected Integer contactsCount;

	@Column(name = "phone_contact_date_time")
	@XmlAttribute
	@XmlSchemaType(name = "dateTime")
	protected Date phoneContactDateTime;

	@Column(name = "operator")
	@XmlAttribute
	protected String operator;

	public Integer getPhoneID() {
		return phoneID;
	}

	public void setPhoneID(Integer phoneID) {
		this.phoneID = phoneID;
	}

	public int getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(int phoneType) {
		this.phoneType = phoneType;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getIsActual() {
		return isActual;
	}

	public void setIsActual(Integer isActual) {
		this.isActual = isActual;
	}

	public Integer getCallsCount() {
		return callsCount;
	}

	public void setCallsCount(Integer callsCount) {
		this.callsCount = callsCount;
	}

	public Integer getContactsCount() {
		return contactsCount;
	}

	public void setContactsCount(Integer contactsCount) {
		this.contactsCount = contactsCount;
	}

	public Date getPhoneContactDateTime() {
		return phoneContactDateTime;
	}

	public void setPhoneContactDateTime(Date phoneContactDateTime) {
		this.phoneContactDateTime = phoneContactDateTime;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public void writeExternal(ObjectOutput out) throws IOException {
		td.writeNullableObject(out, phoneID);
		td.writeNullableObject(out, phoneType);
		td.writeNullableObject(out, phoneNumber);
		td.writeNullableObject(out, isActual);
		td.writeNullableObject(out, callsCount);
		td.writeNullableObject(out, contactsCount);
		td.writeNullableObject(out, phoneContactDateTime);
		td.writeNullableObject(out, operator);
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		phoneID = in.readBoolean() == true ? in.readInt() : null;
		phoneType = in.readBoolean() == true ? in.readInt() : null;
		phoneNumber = in.readBoolean() == true ? in.readUTF() : null;
		isActual = in.readBoolean() == true ? in.readInt() : null;
		callsCount = in.readBoolean() == true ? in.readInt() : null;
		contactsCount = in.readBoolean() == true ? in.readInt() : null;
		phoneContactDateTime = in.readBoolean() == true ? new Date(in.readLong()) : null;
		operator = in.readBoolean() == true ? in.readUTF() : null;
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