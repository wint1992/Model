package ru.ithex.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
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
import javax.xml.bind.annotation.XmlSchemaType;

@XmlRootElement(name = "Action")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "action")
@Embeddable
public class Action implements Externalizable {
	private static final long serialVersionUID = 1l;
	transient private TransformData td = new TransformData();

	public Action() {
		super();
	}

	@Id
	@SequenceGenerator(name = "seq_gen", sequenceName = "action_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen")
	@Column(name = "action_id")
	@XmlAttribute
	protected Integer actionID;

	@Column(name = "action_type")
	@XmlAttribute
	protected int actionType;

	@Column(name = "action_result")
	@XmlAttribute
	protected int actionResult;

	@Column(name = "action_manager_id")
	@XmlAttribute
	protected Integer actionManagerID;

	@Column(name = "action_previous_manager_id")
	@XmlAttribute
	protected Integer actionPreviousManagerID;

	@Column(name = "action_date_time")
	@XmlAttribute
	@XmlSchemaType(name = "dateTime")
	protected Date actionDateTime;

	@Column(name = "action_comment")
	@XmlAttribute
	protected String actionComment;

	public Integer getActionID() {
		return actionID;
	}

	public void setActionID(Integer actionID) {
		this.actionID = actionID;
	}

	public int getActionType() {
		return actionType;
	}

	public void setActionType(int actionType) {
		this.actionType = actionType;
	}

	public int getActionResult() {
		return actionResult;
	}

	public void setActionResult(int actionResult) {
		this.actionResult = actionResult;
	}

	public Integer getActionManagerID() {
		return actionManagerID;
	}

	public void setActionManagerID(Integer actionManagerID) {
		this.actionManagerID = actionManagerID;
	}

	public Integer getActionPreviousManagerID() {
		return actionPreviousManagerID;
	}

	public void setActionPreviousManagerID(Integer actionPreviousManagerID) {
		this.actionPreviousManagerID = actionPreviousManagerID;
	}

	public Date getActionDateTime() {
		return actionDateTime;
	}

	public void setActionDateTime(Date actionDateTime) {
		this.actionDateTime = actionDateTime;
	}

	public String getActionComment() {
		return actionComment;
	}

	public void setActionComment(String actionComment) {
		this.actionComment = actionComment;
	}

	public void writeExternal(ObjectOutput out) throws IOException {
		td.writeNullableObject(out, actionID);
		td.writeNullableObject(out, actionType);
		td.writeNullableObject(out, actionResult);
		td.writeNullableObject(out, actionManagerID);
		td.writeNullableObject(out, actionPreviousManagerID);
		td.writeNullableObject(out, actionDateTime);
		td.writeNullableObject(out, actionComment);
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		actionID = in.readBoolean() == true ? in.readInt() : null;
		actionType = in.readBoolean() == true ? in.readInt() : null;
		actionResult = in.readBoolean() == true ? in.readInt() : null;
		actionManagerID = in.readBoolean() == true ? in.readInt() : null;
		actionPreviousManagerID = in.readBoolean() == true ? in.readInt() : null;
		actionDateTime = in.readBoolean() == true ? new Date(in.readLong()) : null;
		actionComment = in.readBoolean() == true ? in.readUTF() : null;
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
	// .append(transformTimestamp((Date)
	// fields[i].get(this)).toString()).append("\"");
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