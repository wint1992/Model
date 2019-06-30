package ru.ithex.model;

import lombok.Data;
import static ru.ithex.model.utils.Serialization.*;
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
@Data
@Table(name = "random_value")
public class RandomValue implements Externalizable {
	private static final long serialVersionUID = 1l;

	@Id
	@SequenceGenerator(name = "seq_gen_random_value", sequenceName = "random_value_id_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen_random_value")
	@Column(name = "random_value_id")
	@XmlTransient
	protected Integer randomValueId;

	@Column(name = "value")
	@XmlAttribute
	protected Integer value;

	@Column(name = "create_date_time")
	@XmlAttribute
	@XmlSchemaType(name = "dateTime")
	protected Date createDateTime;

	@Column(name = "where_used")
	@XmlAttribute
	protected String whereUsed;

	public void writeExternal(ObjectOutput out) throws IOException {
		writeNullableObject(out, randomValueId);
		writeNullableObject(out, value);
		writeNullableObject(out, createDateTime);
		writeNullableObject(out, whereUsed);

	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		randomValueId = readIntFromObjectInput(in);
		value = readIntFromObjectInput(in);
		createDateTime = readLongToDateFromObjectInput(in);
		whereUsed = readStringFromObjectInput(in);
	}

}
