package ru.ithex.model;

import static ru.ithex.model.TransformData.bigDecimalToInteger;
import static ru.ithex.model.TransformData.transformDate;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class SocialNetwork {

	public SocialNetwork() {
		super();
	}

	protected Integer networkID;
	protected Integer networkType;
	protected String networkAccount;
	protected String interestSegment1;
	protected String interestSegment2;
	protected String interestSegment3;
	protected Integer friendsNum;
	protected boolean fake;

	public Integer getNetworkID() {
		return networkID;
	}

	public void setNetworkID(Integer value) {
		this.networkID = value;
	}

	public Integer getNetworkType() {
		return networkType;
	}

	public void setNetworkType(Integer value) {
		this.networkType = value;
	}

	public void setNetworkType(BigDecimal value) {
		this.networkType = bigDecimalToInteger(value);
	}

	public String getNetworkAccount() {
		return networkAccount;
	}

	public void setNetworkAccount(String value) {
		this.networkAccount = value;
	}

	public String getInterestSegment1() {
		return interestSegment1;
	}

	public void setInterestSegment1(String value) {
		this.interestSegment1 = value;
	}

	public String getInterestSegment2() {
		return interestSegment2;
	}

	public void setInterestSegment2(String value) {
		this.interestSegment2 = value;
	}

	public String getInterestSegment3() {
		return interestSegment3;
	}

	public void setInterestSegment3(String value) {
		this.interestSegment3 = value;
	}

	public Integer getFriendsNum() {
		return friendsNum;
	}

	public void setFriendsNum(Integer value) {
		this.friendsNum = value;
	}

	public void setFriendsNum(BigDecimal value) {
		this.friendsNum = bigDecimalToInteger(value);
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