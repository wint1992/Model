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

@Entity
@Table(name = "address")
@XmlAccessorType(XmlAccessType.FIELD)
public class Address extends AbstractAddress implements Externalizable {
	private static final long serialVersionUID = 1l;
	transient private TransformData td = new TransformData();

	public Address() {
		super();
	}

	@Id
	@SequenceGenerator(name = "seq_gen", sequenceName = "address_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen")
	@Column(name = "address_id")
	@XmlAttribute
	private Integer addressID;

	@Column(name = "address_type")
	@XmlAttribute
	protected int addressType;

	public void setAddressID(Integer addressID) {
		this.addressID = addressID;
	}

	public int getAddressType() {
		return addressType;
	}

	public void setAddressType(int addressType) {
		this.addressType = addressType;
	}

	public Integer getAddressID() {
		return addressID;
	}

	public void writeExternal(ObjectOutput out) throws IOException {
		super.writeExternal(out);
		td.writeNullableObject(out, addressID);
		td.writeNullableObject(out, addressType);
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		super.readExternal(in);
		addressID = in.readBoolean() == true ? in.readInt() : null;
		addressType = in.readBoolean() == true ? in.readInt() : null;

	}

}
