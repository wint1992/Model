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
@Table(name = "product")
@XmlRootElement(name = "Product")
@XmlAccessorType(XmlAccessType.FIELD)
public class Product implements Externalizable {
	private static final long serialVersionUID = 1l;
	transient protected TransformData td = new TransformData();

	public Product() {
		super();
	}

	@Id
	@SequenceGenerator(name = "seq_gen", sequenceName = "product_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen")
	@Column(name = "product_id")
	@XmlAttribute
	protected Integer productID;

	@Column(name = "product_name")
	@XmlAttribute
	protected String productName;

	@Column(name = "product_cost")
	@XmlAttribute
	protected BigDecimal productCost;

	@Column(name = "product_type")
	@XmlAttribute
	protected int productType;

	@Column(name = "product_group")
	@XmlAttribute
	protected Integer productGroup;

	@Column(name = "product_subgroup")
	@XmlAttribute
	protected Integer productSubgroup;

	@Column(name = "product_species")
	@XmlAttribute
	protected Integer productSpecies;

	@Column(name = "product_variety")
	@XmlAttribute
	protected Integer productVariety;

	@Column(name = "product_brand")
	@XmlAttribute
	protected String productBrand;

	@Column(name = "product_count")
	@XmlAttribute
	protected Integer productCount;

	@Column(name = "product_weight")
	@XmlAttribute
	protected Double productWeight;

	@Column(name = "product_length")
	@XmlAttribute
	protected Double productLength;

	@Column(name = "product_volume")
	@XmlAttribute
	protected Double productVolume;

	public Integer getProductID() {
		return productID;
	}

	public void setProductID(Integer productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getProductCost() {
		return productCost;
	}

	public void setProductCost(BigDecimal productCost) {
		this.productCost = productCost;
	}

	public int getProductType() {
		return productType;
	}

	public void setProductType(int productType) {
		this.productType = productType;
	}

	public Integer getProductGroup() {
		return productGroup;
	}

	public void setProductGroup(Integer productGroup) {
		this.productGroup = productGroup;
	}

	public Integer getProductSubgroup() {
		return productSubgroup;
	}

	public void setProductSubgroup(Integer productSubgroup) {
		this.productSubgroup = productSubgroup;
	}

	public Integer getProductSpecies() {
		return productSpecies;
	}

	public void setProductSpecies(Integer productSpecies) {
		this.productSpecies = productSpecies;
	}

	public Integer getProductVariety() {
		return productVariety;
	}

	public void setProductVariety(Integer productVariety) {
		this.productVariety = productVariety;
	}

	public String getProductBrand() {
		return productBrand;
	}

	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}

	public Integer getProductCount() {
		return productCount;
	}

	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}

	public Double getProductWeight() {
		return productWeight;
	}

	public void setProductWeight(Double productWeight) {
		this.productWeight = productWeight;
	}

	public Double getProductLength() {
		return productLength;
	}

	public void setProductLength(Double productLength) {
		this.productLength = productLength;
	}

	public Double getProductVolume() {
		return productVolume;
	}

	public void setProductVolume(Double productVolume) {
		this.productVolume = productVolume;
	}

	public void writeExternal(ObjectOutput out) throws IOException {
		td.writeNullableObject(out, productID);
		td.writeNullableObject(out, productName);
		td.writeNullableObject(out, productCost);
		td.writeNullableObject(out, productType);
		td.writeNullableObject(out, productGroup);
		td.writeNullableObject(out, productSubgroup);
		td.writeNullableObject(out, productSpecies);
		td.writeNullableObject(out, productVariety);
		td.writeNullableObject(out, productBrand);
		td.writeNullableObject(out, productCount);
		td.writeNullableObject(out, productWeight);
		td.writeNullableObject(out, productLength);
		td.writeNullableObject(out, productVolume);
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		productID = in.readBoolean() == true ? in.readInt() : null;
		productName = in.readBoolean() == true ? in.readUTF() : null;
		if (in.readBoolean() == true) {
			int countBD = in.readInt();
			byte[] bytesBD = new byte[countBD];
			in.read(bytesBD, 0, countBD);
			productCost = new BigDecimal(new BigInteger(bytesBD), in.readInt());
		}
		productType = in.readBoolean() == true ? in.readInt() : null;
		productGroup = in.readBoolean() == true ? in.readInt() : null;
		productSubgroup = in.readBoolean() == true ? in.readInt() : null;
		productSpecies = in.readBoolean() == true ? in.readInt() : null;
		productVariety = in.readBoolean() == true ? in.readInt() : null;
		productBrand = in.readBoolean() == true ? in.readUTF() : null;
		productCount = in.readBoolean() == true ? in.readInt() : null;
		productWeight = in.readBoolean() == true ? in.readDouble() : null;
		productLength = in.readBoolean() == true ? in.readDouble() : null;
		productVolume = in.readBoolean() == true ? in.readDouble() : null;
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