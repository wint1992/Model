package ru.ithex.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
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

@Entity
@Table(name = "client")
@XmlRootElement(name = "Client")
@XmlAccessorType(XmlAccessType.FIELD)
public class Client implements Externalizable {
	private static final long serialVersionUID = 1l;
	transient private TransformData td = new TransformData();

	@Id
	@SequenceGenerator(name = "seq_gen", sequenceName = "client_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen")
	@Column(name = "client_id")
	@XmlAttribute
	protected Integer clientID;

	@OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "person_fk")
	@XmlElement(name = "Person")
	protected Person person;

	@OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "organization_fk")
	@XmlElement(name = "Organization")
	protected Organization organization;

	@Embedded
	@XmlElement(name = "RequestedInfo")
	protected RequestedInfo requestedInfo;

	@Embedded
	@XmlElement(name = "ConfigurationParams")
	protected ConfigurationParams configurationParams;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "client_fk")
	@XmlElementWrapper(name = "Applications")
	@XmlElement(name = "Application")
	protected List<Application> applications;

	public Client() {
		super();
	}

	public Person getPerson() {
		if (person == null)
			person = new Person();
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Organization getOrganization() {
		if (organization == null)
			organization = new Organization();
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public List<Application> getApplications() {
		if (applications == null)
			applications = new ArrayList<>();
		return applications;
	}

	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}

	public Integer getClientID() {
		return clientID;
	}

	public void setClientID(Integer clientID) {
		this.clientID = clientID;
	}

	public RequestedInfo getRequestedInfo() {
		if (requestedInfo == null)
			requestedInfo = new RequestedInfo();
		return requestedInfo;
	}

	public void setRequestedInfo(RequestedInfo requestedInfo) {
		this.requestedInfo = requestedInfo;
	}

	public ConfigurationParams getConfigurationParams() {
		if (configurationParams == null)
			configurationParams = new ConfigurationParams();
		return configurationParams;
	}

	public void setConfigurationParams(ConfigurationParams configurationParams) {
		this.configurationParams = configurationParams;
	}

	public void writeExternal(ObjectOutput out) throws IOException {
		td.writeNullableObject(out, clientID);

		getPerson().writeExternal(out);
		getOrganization().writeExternal(out);
		getRequestedInfo().writeExternal(out);
		getConfigurationParams().writeExternal(out);

		out.writeInt(getApplications().size());
		for (Externalizable ext : getApplications())
			ext.writeExternal(out);
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		clientID = in.readBoolean() == true ? in.readInt() : null;

		getPerson().readExternal(in);
		getOrganization().readExternal(in);
		getRequestedInfo().readExternal(in);
		getConfigurationParams().readExternal(in);

		int count = in.readInt();
		for (int i = 0; i < count; i++) {
			Application ext = new Application();
			ext.readExternal(in);
			getApplications().add(ext);
		}

	}

}
