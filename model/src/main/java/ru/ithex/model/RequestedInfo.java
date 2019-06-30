package ru.ithex.model;

import lombok.Data;

import static ru.ithex.model.utils.Serialization.*;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;

@Embeddable
@XmlRootElement(name = "RequestedInfo")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class RequestedInfo implements Externalizable {
	private static final long serialVersionUID = 1l;

	@Column(name = "ri_request_timestamp")
	@XmlAttribute
	@XmlSchemaType(name = "dateTime")
	protected Date requestTimestamp;

	@Column(name = "ri_step_code")
	@XmlAttribute
	protected int stepCode;

	@Column(name = "ri_current_date")
	@XmlAttribute
	@XmlSchemaType(name = "date")
	protected Date currentDate;

	public void writeExternal(ObjectOutput out) throws IOException {
		writeNullableObject(out, requestTimestamp);
		writeNullableObject(out, stepCode);
		writeNullableObject(out, currentDate);
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		requestTimestamp = readLongToDateFromObjectInput(in);
		stepCode = readIntFromObjectInput(in);
		currentDate = readLongToDateFromObjectInput(in);
	}
}