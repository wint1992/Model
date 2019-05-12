package ru.ithex.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

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
import javax.xml.bind.annotation.XmlSchemaType;

@Entity
@Table(name = "employment")
@XmlRootElement(name = "Employment")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employment implements Externalizable {
	private static final long serialVersionUID = 1l;
	transient protected TransformData td = new TransformData();

	public Employment() {
		super();
	}

	@Id
	@SequenceGenerator(name = "seq_gen", sequenceName = "employment_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen")
	@Column(name = "employment_id")
	@XmlAttribute
	protected Integer employmentID;

	@Column(name = "position")
	@XmlAttribute
	protected int position;

	@Column(name = "income_amount")
	@XmlAttribute
	protected BigDecimal incomeAmount;

	@Column(name = "start_working_date")
	@XmlAttribute
	@XmlSchemaType(name = "date")
	protected Date startWorkingDate;

	@Column(name = "end_working_date")
	@XmlAttribute
	@XmlSchemaType(name = "date")
	protected Date endWorkingDate;

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "organization_fk")
	@XmlElement(name = "Organization")
	protected Organization organization;

	public Integer getEmploymentID() {
		return employmentID;
	}

	public void setEmploymentID(Integer employmentID) {
		this.employmentID = employmentID;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public BigDecimal getIncomeAmount() {
		return incomeAmount;
	}

	public void setIncomeAmount(BigDecimal incomeAmount) {
		this.incomeAmount = incomeAmount;
	}

	public Date getStartWorkingDate() {
		return startWorkingDate;
	}

	public void setStartWorkingDate(Date startWorkingDate) {
		this.startWorkingDate = startWorkingDate;
	}

	public Date getEndWorkingDate() {
		return endWorkingDate;
	}

	public void setEndWorkingDate(Date endWorkingDate) {
		this.endWorkingDate = endWorkingDate;
	}

	public Organization getOrganization() {
		if (organization == null)
			organization = new Organization();
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public void writeExternal(ObjectOutput out) throws IOException {
		td.writeNullableObject(out, employmentID);
		td.writeNullableObject(out, position);
		td.writeNullableObject(out, incomeAmount);
		td.writeNullableObject(out, startWorkingDate);
		td.writeNullableObject(out, endWorkingDate);
		getOrganization().writeExternal(out);
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		employmentID = in.readBoolean() == true ? in.readInt() : null;
		position = in.readBoolean() == true ? in.readInt() : null;
		if (in.readBoolean() == true) {
			int countBD = in.readInt();
			byte[] bytesBD = new byte[countBD];
			in.read(bytesBD, 0, countBD);
			incomeAmount = new BigDecimal(new BigInteger(bytesBD), in.readInt());
		}
		startWorkingDate = in.readBoolean() == true ? new Date(in.readLong()) : null;
		endWorkingDate = in.readBoolean() == true ? new Date(in.readLong()) : null;
		getOrganization().readExternal(in);
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