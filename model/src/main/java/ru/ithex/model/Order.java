package ru.ithex.model;

import static ru.ithex.model.TransformData.bigDecimalToInteger;
import static ru.ithex.model.TransformData.transformDate;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Order {

	public Order() {
		super();
	}

	protected Integer orderID;
	protected Integer productCount;
	protected BigDecimal orderCost;
	protected String orderComment;

	protected Product product;
	protected boolean fake;

	public Integer getOrderID() {
		return orderID;
	}

	public void setOrderID(Integer value) {
		this.orderID = value;
	}

	public Integer getProductCount() {
		return productCount;
	}

	public void setProductCount(Integer value) {
		this.productCount = value;
	}

	public void setProductCount(BigDecimal value) {
		this.productCount = bigDecimalToInteger(value);
	}

	public BigDecimal getOrderCost() {
		return orderCost;
	}

	public void setOrderCost(BigDecimal value) {
		this.orderCost = value;
	}

	public String getOrderComment() {
		return orderComment;
	}

	public void setOrderComment(String value) {
		this.orderComment = value;
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}