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
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "RandomValue")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "random_value")
public class RandomValue implements Externalizable {
	private static final long serialVersionUID = 1l;
	transient protected TransformData td = new TransformData();

	public RandomValue() {
		super();
	}

	@Id
	@SequenceGenerator(name = "seq_gen", sequenceName = "random_value_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen")
	@Column(name = "id")
	@XmlTransient
	protected Integer id;

	@Column(name = "value")
	@XmlAttribute
	protected int value;

	@Column(name = "create_date_time")
	@XmlAttribute
	@XmlSchemaType(name = "dateTime")
	protected Date createDateTime;

	@Column(name = "where_used")
	@XmlAttribute
	protected String whereUsed;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getWhereUsed() {
		return whereUsed;
	}

	public void setWhereUsed(String whereUsed) {
		this.whereUsed = whereUsed;
	}

	public Integer getId() {
		return id;
	}

	public void writeExternal(ObjectOutput out) throws IOException {
		td.writeNullableObject(out, id);
		td.writeNullableObject(out, value);
		td.writeNullableObject(out, createDateTime);
		td.writeNullableObject(out, whereUsed);

	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		id = in.readBoolean() == true ? in.readInt() : null;
		value = in.readBoolean() == true ? in.readInt() : null;
		createDateTime = in.readBoolean() == true ? new Date(in.readLong()) : null;
		whereUsed = in.readBoolean() == true ? in.readUTF() : null;
	}

}
