package ru.ithex.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.math.BigDecimal;
import java.math.BigInteger;

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
@Table(name = "auto")
@XmlRootElement(name = "Auto")
@XmlAccessorType(XmlAccessType.FIELD)
public class Auto implements Externalizable {
	private static final long serialVersionUID = 1l;
	transient private TransformData td = new TransformData();

	public Auto() {
		super();
	}

	@Id
	@SequenceGenerator(name = "seq_gen", sequenceName = "auto_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen")
	@Column(name = "auto_id")
	@XmlAttribute
	protected Integer autoID;

	@Column(name = "make")
	@XmlAttribute
	protected String make;

	@Column(name = "model")
	@XmlAttribute
	protected String model;

	@Column(name = "year")
	@XmlAttribute
	protected Integer year;

	@Column(name = "is_credit")
	@XmlAttribute
	protected Byte isCredit;

	@Column(name = "cost")
	@XmlAttribute
	protected BigDecimal cost;

	@Column(name = "car_number")
	@XmlAttribute
	protected String carNumber;

	@Column(name = "auto_reg_serial_number")
	@XmlAttribute
	protected String autoRegSerialNumber;

	@Column(name = "vin")
	@XmlAttribute
	protected String vin;

	public Integer getAutoID() {
		return autoID;
	}

	public void setAutoID(Integer autoID) {
		this.autoID = autoID;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Byte getIsCredit() {
		return isCredit;
	}

	public void setIsCredit(Byte isCredit) {
		this.isCredit = isCredit;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getAutoRegSerialNumber() {
		return autoRegSerialNumber;
	}

	public void setAutoRegSerialNumber(String autoRegSerialNumber) {
		this.autoRegSerialNumber = autoRegSerialNumber;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public void writeExternal(ObjectOutput out) throws IOException {
		td.writeNullableObject(out, autoID);
		td.writeNullableObject(out, make);
		td.writeNullableObject(out, model);
		td.writeNullableObject(out, year);
		td.writeNullableObject(out, isCredit);
		td.writeNullableObject(out, cost);
		td.writeNullableObject(out, carNumber);
		td.writeNullableObject(out, autoRegSerialNumber);
		td.writeNullableObject(out, vin);
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		autoID = in.readBoolean() == true ? in.readInt() : null;
		make = in.readBoolean() == true ? in.readUTF() : null;
		model = in.readBoolean() == true ? in.readUTF() : null;
		year = in.readBoolean() == true ? in.readInt() : null;
		isCredit = in.readBoolean() == true ? in.readByte() : null;
		if (in.readBoolean() == true) {
			int countBD = in.readInt();
			byte[] bytesBD = new byte[countBD];
			in.read(bytesBD, 0, countBD);
			cost = new BigDecimal(new BigInteger(bytesBD), in.readInt());
		}
		carNumber = in.readBoolean() == true ? in.readUTF() : null;
		autoRegSerialNumber = in.readBoolean() == true ? in.readUTF() : null;
		vin = in.readBoolean() == true ? in.readUTF() : null;
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