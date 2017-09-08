package ru.ithex.model;

import static ru.ithex.model.TransformData.bigDecimalToInteger;
import static ru.ithex.model.TransformData.transformTimestamp;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AppData {

	public AppData() {
		super();
	}

	protected Date appDateTime;
	protected Date appCloseDateTime;
	protected Integer applicationStatus;
	protected Integer appType;
	protected Integer appChannel;
	protected Date deliveryDateTime;
	protected Date deliveryFactDateTime;
	protected BigDecimal ordersTotalCost;

	protected Person person;
	protected Set<Order> orders;
	protected Address delAddresses;
	protected Organization organization;
	protected boolean fake;

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person value) {
		this.person = value;
	}

	public Set<Order> getOrders() {
		if (orders == null) {
			orders = new HashSet<Order>();
		}
		return this.orders;
	}

	public Date getAppDateTime() {
		return appDateTime;
	}

	public void setAppDateTime(Date value) {
		this.appDateTime = value;
	}

	public Date getAppCloseDateTime() {
		return appCloseDateTime;
	}

	public void setAppCloseDateTime(Date value) {
		this.appCloseDateTime = value;
	}

	public Integer getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(Integer value) {
		this.applicationStatus = value;
	}

	public void setApplicationStatus(BigDecimal value) {
		this.applicationStatus = bigDecimalToInteger(value);
	}

	public Integer getAppType() {
		return appType;
	}

	public void setAppType(Integer value) {
		this.appType = value;
	}

	public void setAppType(BigDecimal value) {
		this.appType = bigDecimalToInteger(value);
	}

	public Integer getAppChannel() {
		return appChannel;
	}

	public void setAppChannel(Integer value) {
		this.appChannel = value;
	}

	public void setAppChannel(BigDecimal value) {
		this.appChannel = bigDecimalToInteger(value);
	}

	public BigDecimal getOrdersTotalCost() {
		return ordersTotalCost;
	}

	public void setOrdersTotalCost(BigDecimal value) {
		this.ordersTotalCost = value;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
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
								.append(transformTimestamp((Date) fields[i].get(this)).toString()).append("\"");
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

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Date getDeliveryDateTime() {
		return deliveryDateTime;
	}

	public void setDeliveryDateTime(Date value) {
		this.deliveryDateTime = value;
	}

	public Date getDeliveryFactDateTime() {
		return deliveryFactDateTime;
	}

	public void setDeliveryFactDateTime(Date value) {
		this.deliveryFactDateTime = value;
	}

	public Address getDelAddresses() {
		return delAddresses;
	}

	public void setDelAddresses(Address delAddresses) {
		this.delAddresses = delAddresses;
	}
}