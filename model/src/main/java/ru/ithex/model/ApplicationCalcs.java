package ru.ithex.model;

import lombok.Data;
import static ru.ithex.model.utils.Serialization.*;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Embeddable
@XmlRootElement(name = "ApplicationCalcs")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class ApplicationCalcs implements Externalizable {
	private static final long serialVersionUID = 1l;

	@Column(name = "ac_orders_total_cost")
	@XmlAttribute
	protected BigDecimal ordersTotalCost;

	public void writeExternal(ObjectOutput out) throws IOException {
		writeNullableObject(out, ordersTotalCost);
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		ordersTotalCost = readBigDecimalFromObjectInput(in);
	}
}
