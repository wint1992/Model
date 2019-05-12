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
@Table(name = "messanger")
@XmlRootElement(name = "Messanger")
@XmlAccessorType(XmlAccessType.FIELD)
public class Messanger implements Externalizable {
	private static final long serialVersionUID = 1l;
	transient protected TransformData td = new TransformData();

	public Messanger() {
		super();
	}

	@Id
	@SequenceGenerator(name = "seq_gen", sequenceName = "messanger_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen")
	@Column(name = "messanger_id")
	@XmlAttribute
	protected Integer messangerID;

	@Column(name = "messanger_type")
	@XmlAttribute
	protected int messangerType;

	@Column(name = "messanger_account")
	@XmlAttribute
	protected String messangerAccount;

	public Integer getMessangerID() {
		return messangerID;
	}

	public void setMessangerID(Integer messangerID) {
		this.messangerID = messangerID;
	}

	public int getMessangerType() {
		return messangerType;
	}

	public void setMessangerType(int messangerType) {
		this.messangerType = messangerType;
	}

	public String getMessangerAccount() {
		return messangerAccount;
	}

	public void setMessangerAccount(String messangerAccount) {
		this.messangerAccount = messangerAccount;
	}

	public void writeExternal(ObjectOutput out) throws IOException {
		td.writeNullableObject(out, messangerID);
		td.writeNullableObject(out, messangerType);
		td.writeNullableObject(out, messangerAccount);
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		messangerID = in.readBoolean() == true ? in.readInt() : null;
		messangerType = in.readBoolean() == true ? in.readInt() : null;
		messangerAccount = in.readBoolean() == true ? in.readUTF() : null;
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