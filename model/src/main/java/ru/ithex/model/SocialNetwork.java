package ru.ithex.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

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
@Table(name = "social_network")
@XmlRootElement(name = "SocialNetwork")
@XmlAccessorType(XmlAccessType.FIELD)
public class SocialNetwork implements Externalizable {
	private static final long serialVersionUID = 1l;
	transient protected TransformData td = new TransformData();

	public SocialNetwork() {
		super();
	}

	@Id
	@SequenceGenerator(name = "seq_gen", sequenceName = "social_network_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen")
	@Column(name = "network_id")
	@XmlAttribute
	protected Integer networkID;

	@Column(name = "network_type")
	@XmlAttribute
	protected int networkType;

	@Column(name = "network_account")
	@XmlAttribute
	protected String networkAccount;

	@Column(name = "interest_segment1")
	@XmlAttribute
	protected String interestSegment1;

	@Column(name = "interest_segment2")
	@XmlAttribute
	protected String interestSegment2;

	@Column(name = "interest_segment3")
	@XmlAttribute
	protected String interestSegment3;

	@Column(name = "friends_num")
	@XmlAttribute
	protected Integer friendsNum;

	public Integer getNetworkID() {
		return networkID;
	}

	public void setNetworkID(Integer networkID) {
		this.networkID = networkID;
	}

	public int getNetworkType() {
		return networkType;
	}

	public void setNetworkType(int networkType) {
		this.networkType = networkType;
	}

	public String getNetworkAccount() {
		return networkAccount;
	}

	public void setNetworkAccount(String networkAccount) {
		this.networkAccount = networkAccount;
	}

	public String getInterestSegment1() {
		return interestSegment1;
	}

	public void setInterestSegment1(String interestSegment1) {
		this.interestSegment1 = interestSegment1;
	}

	public String getInterestSegment2() {
		return interestSegment2;
	}

	public void setInterestSegment2(String interestSegment2) {
		this.interestSegment2 = interestSegment2;
	}

	public String getInterestSegment3() {
		return interestSegment3;
	}

	public void setInterestSegment3(String interestSegment3) {
		this.interestSegment3 = interestSegment3;
	}

	public Integer getFriendsNum() {
		return friendsNum;
	}

	public void setFriendsNum(Integer friendsNum) {
		this.friendsNum = friendsNum;
	}

	public void writeExternal(ObjectOutput out) throws IOException {
		td.writeNullableObject(out, networkID);
		td.writeNullableObject(out, networkType);
		td.writeNullableObject(out, networkAccount);
		td.writeNullableObject(out, interestSegment1);
		td.writeNullableObject(out, interestSegment2);
		td.writeNullableObject(out, interestSegment3);
		td.writeNullableObject(out, friendsNum);
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		networkID = in.readBoolean() == true ? in.readInt() : null;
		networkType = in.readBoolean() == true ? in.readInt() : null;
		networkAccount = in.readBoolean() == true ? in.readUTF() : null;
		interestSegment1 = in.readBoolean() == true ? in.readUTF() : null;
		interestSegment2 = in.readBoolean() == true ? in.readUTF() : null;
		interestSegment3 = in.readBoolean() == true ? in.readUTF() : null;
		friendsNum = in.readBoolean() == true ? in.readInt() : null;
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