package ru.ithex.model;

import lombok.Data;
import static ru.ithex.model.utils.Serialization.*;
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

@XmlRootElement(name = "StepParameter")
@Entity
@Table(name = "step_parameter")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class StepParameter implements Externalizable {
	private static final long serialVersionUID = 1l;

	@Id
	@SequenceGenerator(name = "seq_gen_step_parameter", sequenceName = "step_parameter_id_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen_step_parameter")
	@Column(name = "parameter_id")
	@XmlTransient
	private Integer parameterID;

	@Column(name = "parameter_code")
	@XmlAttribute
	private int parameterCode;

	@Column(name = "parameter_value")
	@XmlAttribute
	private String parameterValue;

	public void writeExternal(ObjectOutput out) throws IOException {
		writeNullableObject(out, parameterID);
		out.writeInt(parameterCode);
		writeNullableObject(out, parameterValue);
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		parameterID = readIntFromObjectInput(in);
		parameterCode = in.readInt();
		parameterValue = readStringFromObjectInput(in);
	}

}
