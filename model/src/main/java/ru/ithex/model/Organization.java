package ru.ithex.model;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ru.ithex.model.abstraction.AbstractOrganization;
import ru.ithex.model.dictionaries.Product;

import static ru.ithex.model.utils.Serialization.*;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
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

@XmlRootElement(name = "Organization")
@Entity
@Table(name = "organization")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Organization extends AbstractOrganization {
	private static final long serialVersionUID = 1l;

	@Id
	@SequenceGenerator(name = "seq_gen_organization", sequenceName = "organization_id_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen_organization")
	@Column(name = "organization_id")
	@XmlAttribute
	protected Integer organizationID;

	@OneToMany(fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "organization_id")
	@XmlElementWrapper(name = "Employees")
	@XmlElement(name = "Person")
	protected List<Person> employees;

	@OneToMany(fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "organization_id")
	@XmlElementWrapper(name = "Products")
	@XmlElement(name = "Product")
	protected List<Product> products;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "organization_id")
	@XmlElementWrapper(name = "Clients")
	@XmlElement(name = "Client")
	protected List<Client> clients;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "organization_id")
	@XmlElementWrapper(name = "Addresses")
	@XmlElement(name = "Address")
	protected List<Address> addresses;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "organization_id")
	@XmlElementWrapper(name = "Phones")
	@XmlElement(name = "Phone")
	protected List<Phone> phones;

	public List<Person> getEmployees() {
		if (employees == null) employees = new ArrayList<>();
		return employees;
	}

	public List<Product> getProducts() {
		if (products == null) products = new ArrayList<>();
		return products;
	}

	public List<Client> getClients() {
		if (clients == null) clients = new ArrayList<>();
		return clients;
	}

	public List<Address> getAddresses() {
		if (addresses == null) addresses = new ArrayList<>();
		return addresses;
	}

	public List<Phone> getPhones() {
		if (phones == null) phones = new ArrayList<>();
		return phones;
	}

	public void writeExternal(ObjectOutput out) throws IOException {
		super.writeExternal(out);
		writeNullableObject(out, organizationID);

		out.writeInt(getEmployees().size());
		for (Externalizable ext : getEmployees())
			ext.writeExternal(out);

		out.writeInt(getProducts().size());
		for (Externalizable ext : getProducts())
			ext.writeExternal(out);

		out.writeInt(getClients().size());
		for (Externalizable ext : getClients())
			ext.writeExternal(out);

		out.writeInt(getAddresses().size());
		for (Externalizable ext : getAddresses())
			ext.writeExternal(out);

		out.writeInt(getPhones().size());
		for (Externalizable ext : getPhones())
			ext.writeExternal(out);
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		super.readExternal(in);
		organizationID = readIntFromObjectInput(in);

		int count = in.readInt();
		for (int i = 0; i < count; i++) {
			Person ext = new Person();
			ext.readExternal(in);
			getEmployees().add(ext);
		}

		count = in.readInt();
		for (int i = 0; i < count; i++) {
			Product ext = new Product();
			ext.readExternal(in);
			getProducts().add(ext);
		}

		count = in.readInt();
		for (int i = 0; i < count; i++) {
			Client ext = new Client();
			ext.readExternal(in);
			getClients().add(ext);
		}

		count = in.readInt();
		for (int i = 0; i < count; i++) {
			Address ext = new Address();
			ext.readExternal(in);
			getAddresses().add(ext);
		}

		count = in.readInt();
		for (int i = 0; i < count; i++) {
			Phone ext = new Phone();
			ext.readExternal(in);
			getPhones().add(ext);
		}
	}
}