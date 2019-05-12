package ru.ithex.model;

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
public class ApplicationCalcs implements Externalizable {
	private static final long serialVersionUID = 1l;
	transient private TransformData td = new TransformData();

	public ApplicationCalcs() {
		super();
	}

	@Column(name = "ac_orders_total_cost")
	@XmlAttribute
	protected BigDecimal ordersTotalCost;

	public BigDecimal getOrdersTotalCost() {
		return ordersTotalCost;
	}

	public void setOrdersTotalCost(BigDecimal ordersTotalCost) {
		this.ordersTotalCost = ordersTotalCost;
	}

	public void writeExternal(ObjectOutput out) throws IOException {
		td.writeNullableObject(out, ordersTotalCost);
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		if (in.readBoolean() == true) {
			int countBD = in.readInt();
			byte[] bytesBD = new byte[countBD];
			in.read(bytesBD, 0, countBD);
			ordersTotalCost = new BigDecimal(new BigInteger(bytesBD), in.readInt());
		}
	}
}
