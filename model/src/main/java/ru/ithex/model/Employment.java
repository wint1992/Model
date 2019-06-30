package ru.ithex.model;

import lombok.Data;
import ru.ithex.model.abstraction.AbstractEmployment;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import static ru.ithex.model.utils.Serialization.*;
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

@XmlRootElement(name = "Employment")
@Entity
@Table(name = "employment")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Employment extends AbstractEmployment {
	private static final long serialVersionUID = 1l;

	@Id
	@SequenceGenerator(name = "seq_gen_employment", sequenceName = "employment_id_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen_employment")
	@Column(name = "employment_id")
	@XmlAttribute
	protected Integer employmentID;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "organization_id")
	@XmlElement(name = "Organization")
	protected Organization organization;

	public Organization getOrganization() {
		if (organization == null) organization = new Organization();
		return organization;
	}

	public void writeExternal(ObjectOutput out) throws IOException {
		super.writeExternal(out);
		writeNullableObject(out, employmentID);
		getOrganization().writeExternal(out);
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		super.readExternal(in);
		employmentID = readIntFromObjectInput(in);
		getOrganization().readExternal(in);
	}
}