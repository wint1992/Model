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
@Table(name = "next_step")
@XmlRootElement(name = "NextStep")
@XmlAccessorType(XmlAccessType.FIELD)
public class NextStep implements Externalizable {
	private static final long serialVersionUID = 1l;
	transient protected TransformData td = new TransformData();

	public NextStep() {
		super();
	}

	@Id
	@SequenceGenerator(name = "seq_gen", sequenceName = "next_step_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen")
	@Column(name = "next_step_id")
	@XmlAttribute
	protected Integer nextStepID;

	@Column(name = "step_code")
	@XmlAttribute
	protected int stepCode;

	@Column(name = "step_plane_date_time")
	@XmlAttribute
	@XmlSchemaType(name = "dateTime")
	protected Date stepPlaneDateTime;

	@Column(name = "step_priority")
	@XmlAttribute
	protected int stepPriority;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "next_step_fk")
	@XmlElementWrapper(name = "StepParametrs")
	@XmlElement(name = "StepParametr")
	protected List<StepParametr> stepParametrs;

	public Integer getNextStepID() {
		return nextStepID;
	}

	public void setNextStepID(Integer nextStepID) {
		this.nextStepID = nextStepID;
	}

	public int getStepCode() {
		return stepCode;
	}

	public void setStepCode(int stepCode) {
		this.stepCode = stepCode;
	}

	public Date getStepPlaneDateTime() {
		return stepPlaneDateTime;
	}

	public void setStepPlaneDateTime(Date stepPlaneDateTime) {
		this.stepPlaneDateTime = stepPlaneDateTime;
	}

	public int getStepPriority() {
		return stepPriority;
	}

	public void setStepPriority(int stepPriority) {
		this.stepPriority = stepPriority;
	}

	public List<StepParametr> getStepParametrs() {
		if (stepParametrs == null)
			stepParametrs = new ArrayList<>();
		return stepParametrs;
	}

	public void setStepParametrs(List<StepParametr> stepParametrs) {
		this.stepParametrs = stepParametrs;
	}

	public void writeExternal(ObjectOutput out) throws IOException {
		td.writeNullableObject(out, nextStepID);
		out.writeInt(stepCode);
		td.writeNullableObject(out, stepPlaneDateTime);
		out.writeInt(stepPriority);

		out.writeInt(getStepParametrs().size());
		for (Externalizable ext : getStepParametrs())
			ext.writeExternal(out);

	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		nextStepID = in.readBoolean() == true ? in.readInt() : null;
		stepCode = in.readInt();
		stepPlaneDateTime = in.readBoolean() == true ? new Date(in.readLong()) : null;
		stepPriority = in.readInt();

		int count = in.readInt();
		for (int i = 0; i < count; i++) {
			StepParametr ext = new StepParametr();
			ext.readExternal(in);
			getStepParametrs().add(ext);
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