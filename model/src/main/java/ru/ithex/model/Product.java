package ru.ithex.model;

import static ru.ithex.model.TransformData.transformDate;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Product {

	public Product() {
		super();
	}

	protected Integer productID;
	protected String productName;
	protected BigDecimal productCost;
	protected Integer productType;
	protected Integer productGroup;
	protected Integer productSubgroup;
	protected Integer productSpecies;
	protected Integer productVariety;
	protected String productBrand;
	protected Integer productCount;
	protected BigDecimal productWeight;
	protected BigDecimal productLength;
	protected BigDecimal productVolume;
	protected boolean fake;

	public Integer getProductID() {
		return productID;
	}

	public void setProductID(Integer value) {
		this.productID = value;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String value) {
		this.productName = value;
	}

	public BigDecimal getProductCost() {
		return productCost;
	}

	public void setProductCost(BigDecimal value) {
		this.productCost = value;
	}

	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer value) {
		this.productType = value;
	}

	public Integer getProductGroup() {
		return productGroup;
	}

	public void setProductGroup(Integer value) {
		this.productGroup = value;
	}

	public Integer getProductSubgroup() {
		return productSubgroup;
	}

	public void setProductSubgroup(Integer value) {
		this.productSubgroup = value;
	}

	public Integer getProductSpecies() {
		return productSpecies;
	}

	public void setProductSpecies(Integer value) {
		this.productSpecies = value;
	}

	public Integer getProductVariety() {
		return productVariety;
	}

	public void setProductVariety(Integer value) {
		this.productVariety = value;
	}

	public String getProductBrand() {
		return productBrand;
	}

	public void setProductBrand(String value) {
		this.productBrand = value;
	}

	public Integer getProductCount() {
		return productCount;
	}

	public void setProductCount(Integer value) {
		this.productCount = value;
	}

	public BigDecimal getProductWeight() {
		return productWeight;
	}

	public void setProductWeight(BigDecimal value) {
		this.productWeight = value;
	}

	public BigDecimal getProductLength() {
		return productLength;
	}

	public void setProductLength(BigDecimal value) {
		this.productLength = value;
	}

	public BigDecimal getProductVolume() {
		return productVolume;
	}

	public void setProductVolume(BigDecimal value) {
		this.productVolume = value;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("{");
		try {
			boolean hasFirstProperty = false;
			Field[] fields = this.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length - 1; i++) {
				if (fields[i].get(this) != null) {
					if (fields[i].getType().equals(String.class)) {
						if (hasFirstProperty)
							sb.append(",");
						sb.append("\"").append(fields[i].getName()).append("\"").append(": \"")
								.append(fields[i].get(this).toString()).append("\"");
						hasFirstProperty = true;
					} else if (fields[i].getType().equals(Date.class)) {
						if (hasFirstProperty)
							sb.append(",");
						sb.append("\"").append(fields[i].getName()).append("\"").append(": \"")
								.append(transformDate((Date) fields[i].get(this)).toString()).append("\"");
						hasFirstProperty = true;
					} else if (fields[i].getType().equals(Set.class) || fields[i].getType().equals(List.class)) {
						if (((Collection) fields[i].get(this)).size() > 0) {
							if (hasFirstProperty)
								sb.append(",");
							sb.append("\"").append(fields[i].getName()).append("\"").append(": ")
									.append(fields[i].get(this).toString());
							hasFirstProperty = true;
						}
					} else {
						if (hasFirstProperty)
							sb.append(",");
						sb.append("\"").append(fields[i].getName()).append("\"").append(": ")
								.append(fields[i].get(this).toString());
						hasFirstProperty = true;
					}
				}
			}
		} catch (Exception e) {
			sb.append("null");
		}
		return sb.append("}").toString();
	}
}