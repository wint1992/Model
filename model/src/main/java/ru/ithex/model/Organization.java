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
@Table(name = "organization")
@XmlRootElement(name = "Organization")
@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Externalizable {
	private static final long serialVersionUID = 1l;
	transient protected TransformData td = new TransformData();

	public Organization() {
		super();
	}

	@Id
	@SequenceGenerator(name = "seq_gen", sequenceName = "organization_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen")
	@Column(name = "organization_id")
	@XmlAttribute
	protected Integer organizationID;

	@Column(name = "status")
	@XmlAttribute
	protected Integer status;

	@Column(name = "registration_date")
	@XmlAttribute
	@XmlSchemaType(name = "date")
	protected Date registrationDate;

	@Column(name = "close_date")
	@XmlAttribute
	@XmlSchemaType(name = "date")
	protected Date closeDate;

	@Column(name = "inn")
	@XmlAttribute
	protected String inn;

	@Column(name = "ogrn")
	@XmlAttribute
	protected String ogrn;

	@Column(name = "okopf")
	@XmlAttribute
	protected String okopf;

	@Column(name = "okved")
	@XmlAttribute
	protected String okved;

	@Column(name = "full_name")
	@XmlAttribute
	protected String fullName;

	@Column(name = "okpo")
	@XmlAttribute
	protected String okpo;

	@Column(name = "employee_number")
	@XmlAttribute
	protected Integer employeeNumber;

	@Column(name = "web_site")
	@XmlAttribute
	protected String webSite;

	@Column(name = "is_employment")
	@XmlAttribute
	protected Integer isEmployment;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "organization_fk")
	@XmlElementWrapper(name = "Employees")
	@XmlElement(name = "Person")
	protected List<Person> employees;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "organization_fk")
	@XmlElementWrapper(name = "Products")
	@XmlElement(name = "Product")
	protected List<Product> products;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "organization_fk")
	@XmlElementWrapper(name = "Clients")
	@XmlElement(name = "Client")
	protected List<Client> clients;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "organization_fk")
	@XmlElementWrapper(name = "Addresses")
	@XmlElement(name = "Address")
	protected List<Address> addresses;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "organization_fk")
	@XmlElementWrapper(name = "Phones")
	@XmlElement(name = "Phone")
	protected List<Phone> phones;

	public Integer getOrganizationID() {
		return organizationID;
	}

	public void setOrganizationID(Integer organizationID) {
		this.organizationID = organizationID;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public String getInn() {
		return inn;
	}

	public void setInn(String inn) {
		this.inn = inn;
	}

	public String getOgrn() {
		return ogrn;
	}

	public void setOgrn(String ogrn) {
		this.ogrn = ogrn;
	}

	public String getOkopf() {
		return okopf;
	}

	public void setOkopf(String okopf) {
		this.okopf = okopf;
	}

	public String getOkved() {
		return okved;
	}

	public void setOkved(String okved) {
		this.okved = okved;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getOkpo() {
		return okpo;
	}

	public void setOkpo(String okpo) {
		this.okpo = okpo;
	}

	public Integer getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(Integer employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public List<Person> getEmployees() {
		if (employees == null)
			employees = new ArrayList<>();
		return employees;
	}

	public void setEmployees(List<Person> employees) {
		this.employees = employees;
	}

	public List<Product> getProducts() {
		if (products == null)
			products = new ArrayList<>();
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<Client> getClients() {
		if (clients == null)
			clients = new ArrayList<>();
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public List<Address> getAddresses() {
		if (addresses == null)
			addresses = new ArrayList<>();
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<Phone> getPhones() {
		if (phones == null)
			phones = new ArrayList<>();
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public Integer getIsEmployment() {
		return isEmployment;
	}

	public void setIsEmployment(Integer isEmployment) {
		this.isEmployment = isEmployment;
	}

	public void writeExternal(ObjectOutput out) throws IOException {
		td.writeNullableObject(out, organizationID);
		td.writeNullableObject(out, status);
		td.writeNullableObject(out, registrationDate);
		td.writeNullableObject(out, closeDate);
		td.writeNullableObject(out, inn);
		td.writeNullableObject(out, ogrn);
		td.writeNullableObject(out, okopf);
		td.writeNullableObject(out, okved);
		td.writeNullableObject(out, fullName);
		td.writeNullableObject(out, okpo);
		td.writeNullableObject(out, employeeNumber);
		td.writeNullableObject(out, webSite);
		td.writeNullableObject(out, isEmployment);

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
		organizationID = in.readBoolean() == true ? in.readInt() : null;
		status = in.readBoolean() == true ? in.readInt() : null;
		registrationDate = in.readBoolean() == true ? new Date(in.readLong()) : null;
		closeDate = in.readBoolean() == true ? new Date(in.readLong()) : null;
		inn = in.readBoolean() == true ? in.readUTF() : null;
		ogrn = in.readBoolean() == true ? in.readUTF() : null;
		okopf = in.readBoolean() == true ? in.readUTF() : null;
		okved = in.readBoolean() == true ? in.readUTF() : null;
		fullName = in.readBoolean() == true ? in.readUTF() : null;
		okpo = in.readBoolean() == true ? in.readUTF() : null;
		employeeNumber = in.readBoolean() == true ? in.readInt() : null;
		webSite = in.readBoolean() == true ? in.readUTF() : null;
		isEmployment = in.readBoolean() == true ? in.readInt() : null;

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
	// \"").append(transformDate((Date)
	// fields[i].get(this)).toString()).append("\"");
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