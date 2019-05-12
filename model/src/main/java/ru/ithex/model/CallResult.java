package ru.ithex.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;

@Entity
@Table(name = "call_result")
@XmlRootElement(name = "CallResult")
@XmlAccessorType(XmlAccessType.FIELD)
public class CallResult implements Externalizable {
	private static final long serialVersionUID = 1l;
	transient private TransformData td = new TransformData();

	public CallResult() {
		super();
	}

	@Id
	@SequenceGenerator(name = "seq_gen", sequenceName = "call_result_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen")
	@Column(name = "call_result_id")
	@XmlAttribute
	protected Integer callResultID;

	@Column(name = "call_type")
	@XmlAttribute
	protected int callType;

	@Column(name = "is_active")
	@XmlAttribute
	protected int isActive;

	@Column(name = "call_date_time")
	@XmlAttribute
	@XmlSchemaType(name = "dateTime")
	protected Date callDateTime;

	@Column(name = "call_decision")
	@XmlAttribute
	protected int callDecision;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "call_result_fk")
	@XmlElementWrapper(name = "ReasonCodes")
	@XmlElement(name = "ReasonCode")
	protected List<ReasonCode> reasonCodes;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "call_result_fk")
	@XmlElementWrapper(name = "NextSteps")
	@XmlElement(name = "NextStep")
	protected List<NextStep> nextSteps;

	public Integer getCallResultID() {
		return callResultID;
	}

	public void setCallResultID(Integer callResultID) {
		this.callResultID = callResultID;
	}

	public int getCallType() {
		return callType;
	}

	public void setCallType(int callType) {
		this.callType = callType;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public Date getCallDateTime() {
		return callDateTime;
	}

	public void setCallDateTime(Date callDateTime) {
		this.callDateTime = callDateTime;
	}

	public int getCallDecision() {
		return callDecision;
	}

	public void setCallDecision(int callDecision) {
		this.callDecision = callDecision;
	}

	public List<ReasonCode> getReasonCodes() {
		if (reasonCodes == null)
			reasonCodes = new ArrayList<>();
		return reasonCodes;
	}

	public void setReasonCodes(List<ReasonCode> reasonCodes) {
		this.reasonCodes = reasonCodes;
	}

	public List<NextStep> getNextSteps() {
		if (nextSteps == null)
			nextSteps = new ArrayList<>();
		return nextSteps;
	}

	public void setNextSteps(List<NextStep> nextSteps) {
		this.nextSteps = nextSteps;
	}

	public void writeExternal(ObjectOutput out) throws IOException {
		td.writeNullableObject(out, callResultID);
		out.writeInt(callType);
		out.writeInt(isActive);
		td.writeNullableObject(out, callDateTime);
		out.writeInt(callDecision);

		out.writeInt(getReasonCodes().size());
		for (Externalizable ext : getReasonCodes())
			ext.writeExternal(out);

		out.writeInt(getNextSteps().size());
		for (Externalizable ext : getNextSteps())
			ext.writeExternal(out);

	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		callResultID = in.readBoolean() == true ? in.readInt() : null;
		callType = in.readInt();
		isActive = in.readInt();
		callDateTime = in.readBoolean() == true ? new Date(in.readLong()) : null;
		callDecision = in.readInt();

		int count = in.readInt();
		for (int i = 0; i < count; i++) {
			ReasonCode ext = new ReasonCode();
			ext.readExternal(in);
			getReasonCodes().add(ext);
		}

		count = in.readInt();
		for (int i = 0; i < count; i++) {
			NextStep ext = new NextStep();
			ext.readExternal(in);
			getNextSteps().add(ext);
		}
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
	// sb.append("\"").append(fields[i].getName()).append("\"").append(":
	// \"").append(fields[i].get(this).toString()).append("\"");
	// hasFirstProperty = true;
	// } else if (fields[i].getType().equals(Date.class)) {
	// if (hasFirstProperty)
	// sb.append(",");
	// sb.append("\"").append(fields[i].getName()).append("\"").append(":
	// \"").append(transformTimestamp((Date) fields[i].get(this)).toString())
	// .append("\"");
	// hasFirstProperty = true;
	// } else if (fields[i].getType().equals(Set.class) ||
	// fields[i].getType().equals(List.class)) {
	// if (((Collection) fields[i].get(this)).size() > 0) {
	// if (hasFirstProperty)
	// sb.append(",");
	// sb.append("\"").append(fields[i].getName()).append("\"").append(":
	// ").append(fields[i].get(this).toString());
	// hasFirstProperty = true;
	// }
	// } else {
	// if (hasFirstProperty)
	// sb.append(",");
	// sb.append("\"").append(fields[i].getName()).append("\"").append(":
	// ").append(fields[i].get(this).toString());
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