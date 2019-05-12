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
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "step_parametr")
@XmlRootElement(name = "StepParametr")
@XmlAccessorType(XmlAccessType.FIELD)
public class StepParametr implements Externalizable {
	private static final long serialVersionUID = 1l;
	transient protected TransformData td = new TransformData();

	public StepParametr() {
		super();
	}

	@Id
	@SequenceGenerator(name = "seq_gen", sequenceName = "next_step_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen")
	@Column(name = "parametr_id")
	@XmlTransient
	private Integer parametrID;

	@Column(name = "parametr_code")
	@XmlAttribute
	private int parametrCode;

	@Column(name = "parametr_value")
	@XmlAttribute
	private String parametrValue;

	public Integer getParametrID() {
		return parametrID;
	}

	public void setParametrID(Integer parametrID) {
		this.parametrID = parametrID;
	}

	public int getParametrCode() {
		return parametrCode;
	}

	public void setParametrCode(int parametrCode) {
		this.parametrCode = parametrCode;
	}

	public String getParametrValue() {
		return parametrValue;
	}

	public void setParametrValue(String parametrValue) {
		this.parametrValue = parametrValue;
	}

	public void writeExternal(ObjectOutput out) throws IOException {
		td.writeNullableObject(out, parametrID);
		out.writeInt(parametrCode);
		td.writeNullableObject(out, parametrValue);
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		parametrID = in.readBoolean() == true ? in.readInt() : null;
		parametrCode = in.readInt();
		parametrValue = in.readBoolean() == true ? in.readUTF() : null;
	}

}
