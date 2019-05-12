package ru.ithex.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Date;

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
import javax.xml.bind.annotation.XmlSchemaType;

@Entity
@Table(name = "document")
@XmlRootElement(name = "Document")
@XmlAccessorType(XmlAccessType.FIELD)
public class Document implements Externalizable {
	private static final long serialVersionUID = 1l;
	transient private TransformData td = new TransformData();

	public Document() {
		super();
	}

	@Id
	@SequenceGenerator(name = "seq_gen", sequenceName = "document_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen")
	@Column(name = "doc_id")
	@XmlAttribute
	protected Integer docID;

	@Column(name = "doc_type")
	@XmlAttribute
	protected int docType;

	@Column(name = "issue_date")
	@XmlAttribute
	@XmlSchemaType(name = "date")
	protected Date issueDate;

	@Column(name = "serial")
	@XmlAttribute
	protected String serial;

	@Column(name = "number")
	@XmlAttribute
	protected String number;

	@Column(name = "sub_division_code")
	@XmlAttribute
	protected String subDivisionCode;

	@Column(name = "issuer")
	@XmlAttribute
	protected String issuer;

	@Column(name = "is_original")
	@XmlAttribute
	protected Byte isOriginal;

	@Column(name = "amount_pages")
	@XmlAttribute
	protected Integer amountPages;

	@Column(name = "link")
	@XmlAttribute
	protected String link;

	@Column(name = "version")
	@XmlAttribute
	protected String version;

	public Integer getDocID() {
		return docID;
	}

	public void setDocID(Integer docID) {
		this.docID = docID;
	}

	public int getDocType() {
		return docType;
	}

	public void setDocType(int docType) {
		this.docType = docType;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getSubDivisionCode() {
		return subDivisionCode;
	}

	public void setSubDivisionCode(String subDivisionCode) {
		this.subDivisionCode = subDivisionCode;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public Byte getIsOriginal() {
		return isOriginal;
	}

	public void setIsOriginal(Byte isOriginal) {
		this.isOriginal = isOriginal;
	}

	public Integer getAmountPages() {
		return amountPages;
	}

	public void setAmountPages(Integer amountPages) {
		this.amountPages = amountPages;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void writeExternal(ObjectOutput out) throws IOException {
		td.writeNullableObject(out, docID);
		td.writeNullableObject(out, docType);
		td.writeNullableObject(out, issueDate);
		td.writeNullableObject(out, serial);
		td.writeNullableObject(out, number);
		td.writeNullableObject(out, subDivisionCode);
		td.writeNullableObject(out, issuer);
		td.writeNullableObject(out, isOriginal);
		td.writeNullableObject(out, amountPages);
		td.writeNullableObject(out, link);
		td.writeNullableObject(out, version);
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		docID = in.readBoolean() == true ? in.readInt() : null;
		docType = in.readBoolean() == true ? in.readInt() : null;
		issueDate = in.readBoolean() == true ? new Date(in.readLong()) : null;
		serial = in.readBoolean() == true ? in.readUTF() : null;
		number = in.readBoolean() == true ? in.readUTF() : null;
		subDivisionCode = in.readBoolean() == true ? in.readUTF() : null;
		issuer = in.readBoolean() == true ? in.readUTF() : null;
		isOriginal = in.readBoolean() == true ? in.readByte() : null;
		amountPages = in.readBoolean() == true ? in.readInt() : null;
		link = in.readBoolean() == true ? in.readUTF() : null;
		version = in.readBoolean() == true ? in.readUTF() : null;
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