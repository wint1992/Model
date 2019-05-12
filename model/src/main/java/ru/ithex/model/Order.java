package ru.ithex.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "order")
@XmlRootElement(name = "Order")
@XmlAccessorType(XmlAccessType.FIELD)
public class Order implements Externalizable {
	private static final long serialVersionUID = 1l;
	transient protected TransformData td = new TransformData();

	public Order() {
		super();
	}

	@Id
	@SequenceGenerator(name = "seq_gen", sequenceName = "order_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen")
	@Column(name = "order_id")
	@XmlAttribute
	protected Integer orderID;

	@Column(name = "product_count")
	@XmlAttribute
	protected int productCount;

	@Column(name = "order_cost")
	@XmlAttribute
	protected BigDecimal orderCost;

	@Column(name = "order_comment")
	@XmlAttribute
	protected String orderComment;

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "product_fk")
	@XmlElement(name = "Product")
	protected Product product;

	public Integer getOrderID() {
		return orderID;
	}

	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	public BigDecimal getOrderCost() {
		return orderCost;
	}

	public void setOrderCost(BigDecimal orderCost) {
		this.orderCost = orderCost;
	}

	public String getOrderComment() {
		return orderComment;
	}

	public void setOrderComment(String orderComment) {
		this.orderComment = orderComment;
	}

	public Product getProduct() {
		if (product == null)
			product = new Product();
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void writeExternal(ObjectOutput out) throws IOException {
		td.writeNullableObject(out, orderID);
		td.writeNullableObject(out, productCount);
		td.writeNullableObject(out, orderCost);
		td.writeNullableObject(out, orderComment);
		getProduct().writeExternal(out);
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		orderID = in.readBoolean() == true ? in.readInt() : null;
		productCount = in.readBoolean() == true ? in.readInt() : null;
		if (in.readBoolean() == true) {
			int countBD = in.readInt();
			byte[] bytesBD = new byte[countBD];
			in.read(bytesBD, 0, countBD);
			orderCost = new BigDecimal(new BigInteger(bytesBD), in.readInt());
		}
		orderComment = in.readBoolean() == true ? in.readUTF() : null;
		getProduct().readExternal(in);
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