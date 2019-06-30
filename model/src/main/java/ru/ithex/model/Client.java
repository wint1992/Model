package ru.ithex.model;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ru.ithex.model.abstraction.AbstractClient;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;
import static ru.ithex.model.utils.Serialization.*;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Client")
@Entity
@Table(name = "client")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Client extends AbstractClient {
	private static final long serialVersionUID = 1l;

	@Id
	@SequenceGenerator(name = "seq_gen_client", sequenceName = "client_id_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen_client")
	@Column(name = "client_id")
	@XmlAttribute
	protected Integer clientID;

	@OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "person_id")
	@XmlElement(name = "Person")
	protected Person person;

	@OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "organization_id")
	@XmlElement(name = "Organization")
	protected Organization organization;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "client_id")
	@XmlElementWrapper(name = "Applications")
	@XmlElement(name = "Application")
	protected List<Application> applications;

	public Person getPerson() {
		if (person == null) person = new Person();
		return person;
	}

	public Organization getOrganization() {
		if (organization == null) organization = new Organization();
		return organization;
	}

	public List<Application> getApplications() {
		if (applications == null) applications = new ArrayList<>();
		return applications;
	}

	public void writeExternal(ObjectOutput out) throws IOException {
		super.writeExternal(out);
		writeNullableObject(out, clientID);

		getPerson().writeExternal(out);
		getOrganization().writeExternal(out);

		out.writeInt(getApplications().size());
		for (Externalizable ext : getApplications())
			ext.writeExternal(out);
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		super.readExternal(in);
		clientID = readIntFromObjectInput(in);

		getPerson().readExternal(in);
		getOrganization().readExternal(in);

		int count = in.readInt();
		for (int i = 0; i < count; i++) {
			Application ext = new Application();
			ext.readExternal(in);
			getApplications().add(ext);
		}

	}

}
