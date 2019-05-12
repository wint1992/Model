package ru.ithex.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
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
@Table(name = "person")
@XmlRootElement(name = "Person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person implements Externalizable {
	private static final long serialVersionUID = 1l;
	transient protected TransformData td = new TransformData();

	public Person() {
		super();
	}

	@Id
	@SequenceGenerator(name = "seq_gen", sequenceName = "person_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen")
	@Column(name = "person_id")
	@XmlAttribute
	protected Integer personID;

	@Column(name = "person_type")
	@XmlAttribute
	protected int personType;

	@Column(name = "first_name")
	@XmlAttribute
	protected String firstName;

	@Column(name = "last_name")
	@XmlAttribute
	protected String lastName;

	@Column(name = "patronymic_name")
	@XmlAttribute
	protected String patronymicName;

	@Column(name = "is_no_patronymic")
	@XmlAttribute
	protected Byte isNoPatronymic;

	@Column(name = "previous_last_name")
	@XmlAttribute
	protected String previousLastName;

	@Column(name = "birth_place")
	@XmlAttribute
	protected String birthPlace;

	@Column(name = "birth_date")
	@XmlAttribute
	@XmlSchemaType(name = "date")
	protected Date birthDate;

	@Column(name = "death_date")
	@XmlAttribute
	@XmlSchemaType(name = "date")
	protected Date deathDate;

	@Column(name = "age")
	@XmlAttribute
	protected Integer age;

	@Column(name = "sex")
	@XmlAttribute
	protected Integer sex;

	@Column(name = "citizenship")
	@XmlAttribute
	protected Integer citizenship;

	@Column(name = "marital_status")
	@XmlAttribute
	protected Integer maritalStatus;

	@Column(name = "education")
	@XmlAttribute
	protected Integer education;

	@Column(name = "email")
	@XmlAttribute
	protected String email;

	@Column(name = "work_email")
	@XmlAttribute
	protected String workEmail;

	@AttributeOverrides({ //
			@AttributeOverride(name = "country", column = @Column(name = "ra_country")), //
			@AttributeOverride(name = "regionType", column = @Column(name = "ra_region_type")), //
			@AttributeOverride(name = "regionName", column = @Column(name = "ra_region_name")), //
			@AttributeOverride(name = "districtType", column = @Column(name = "ra_district_type")), //
			@AttributeOverride(name = "districtName", column = @Column(name = "ra_district_name")), //
			@AttributeOverride(name = "microdistrict", column = @Column(name = "ra_microdistrict")), //
			@AttributeOverride(name = "locationType", column = @Column(name = "ra_location_type")), //
			@AttributeOverride(name = "cityType", column = @Column(name = "ra_city_type")), //
			@AttributeOverride(name = "city", column = @Column(name = "ra_city")), //
			@AttributeOverride(name = "streetType", column = @Column(name = "ra_street_type")), //
			@AttributeOverride(name = "street", column = @Column(name = "ra_street")), //
			@AttributeOverride(name = "house", column = @Column(name = "ra_house")), //
			@AttributeOverride(name = "construction", column = @Column(name = "ra_construction")), //
			@AttributeOverride(name = "building", column = @Column(name = "ra_building")), //
			@AttributeOverride(name = "apartment", column = @Column(name = "ra_apartment")), //
			@AttributeOverride(name = "postIndex", column = @Column(name = "ra_post_index")), //
			@AttributeOverride(name = "registrationDate", column = @Column(name = "ra_registration_date")), //
			@AttributeOverride(name = "startLivingDate", column = @Column(name = "ra_start_living_date")), //
			@AttributeOverride(name = "gps", column = @Column(name = "ra_gps")), //
			@AttributeOverride(name = "fullAddress", column = @Column(name = "ra_full_address")), //
			@AttributeOverride(name = "codeKLADR", column = @Column(name = "ra_code_kladr")), //
			@AttributeOverride(name = "timeZone", column = @Column(name = "ra_time_zone")), //
			@AttributeOverride(name = "codeKLADE", column = @Column(name = "ra_code_klade")), //
			@AttributeOverride(name = "autonomyType", column = @Column(name = "ra_autonomy_type")), //
			@AttributeOverride(name = "autonomyName", column = @Column(name = "ra_autonomy_name")), //
			@AttributeOverride(name = "location", column = @Column(name = "ra_location")), //
			@AttributeOverride(name = "intracityDistrict", column = @Column(name = "ra_intracity_district")), //
			@AttributeOverride(name = "ownership", column = @Column(name = "ra_ownership")) //
	})
	@Embedded
	@XmlElement(name = "RegAddress")
	protected EAddress regAddress;

	@AttributeOverrides({ //
			@AttributeOverride(name = "country", column = @Column(name = "fa_country")), //
			@AttributeOverride(name = "regionType", column = @Column(name = "fa_region_type")), //
			@AttributeOverride(name = "regionName", column = @Column(name = "fa_region_name")), //
			@AttributeOverride(name = "districtType", column = @Column(name = "fa_district_type")), //
			@AttributeOverride(name = "districtName", column = @Column(name = "fa_district_name")), //
			@AttributeOverride(name = "microdistrict", column = @Column(name = "fa_microdistrict")), //
			@AttributeOverride(name = "locationType", column = @Column(name = "fa_location_type")), //
			@AttributeOverride(name = "cityType", column = @Column(name = "fa_city_type")), //
			@AttributeOverride(name = "city", column = @Column(name = "fa_city")), //
			@AttributeOverride(name = "streetType", column = @Column(name = "fa_street_type")), //
			@AttributeOverride(name = "street", column = @Column(name = "fa_street")), //
			@AttributeOverride(name = "house", column = @Column(name = "fa_house")), //
			@AttributeOverride(name = "construction", column = @Column(name = "fa_construction")), //
			@AttributeOverride(name = "building", column = @Column(name = "fa_building")), //
			@AttributeOverride(name = "apartment", column = @Column(name = "fa_apartment")), //
			@AttributeOverride(name = "postIndex", column = @Column(name = "fa_post_index")), //
			@AttributeOverride(name = "registrationDate", column = @Column(name = "fa_registration_date")), //
			@AttributeOverride(name = "startLivingDate", column = @Column(name = "fa_start_living_date")), //
			@AttributeOverride(name = "gps", column = @Column(name = "fa_gps")), //
			@AttributeOverride(name = "fullAddress", column = @Column(name = "fa_full_address")), //
			@AttributeOverride(name = "codeKLADR", column = @Column(name = "fa_code_kladr")), //
			@AttributeOverride(name = "timeZone", column = @Column(name = "fa_time_zone")), //
			@AttributeOverride(name = "codeKLADE", column = @Column(name = "fa_code_klade")), //
			@AttributeOverride(name = "autonomyType", column = @Column(name = "fa_autonomy_type")), //
			@AttributeOverride(name = "autonomyName", column = @Column(name = "fa_autonomy_name")), //
			@AttributeOverride(name = "location", column = @Column(name = "fa_location")), //
			@AttributeOverride(name = "intracityDistrict", column = @Column(name = "fa_intracity_district")), //
			@AttributeOverride(name = "ownership", column = @Column(name = "fa_ownership")) //
	})
	@Embedded
	@XmlElement(name = "FactAddress")
	protected EAddress factAddress;

	@Embedded
	@XmlElement(name = "PersonCalcs")
	protected PersonCalcs personCalcs;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "person_fk")
	@XmlElementWrapper(name = "Documents")
	@XmlElement(name = "Document")
	protected List<Document> documents;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "person_fk")
	@XmlElementWrapper(name = "Phones")
	@XmlElement(name = "Phone")
	protected List<Phone> phones;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "person_fk")
	@XmlElementWrapper(name = "SocialNetworks")
	@XmlElement(name = "SocialNetwork")
	protected List<SocialNetwork> socialNetworks;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "person_fk")
	@XmlElementWrapper(name = "Messangers")
	@XmlElement(name = "Messanger")
	protected List<Messanger> messangers;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "person_fk")
	@XmlElementWrapper(name = "Employments")
	@XmlElement(name = "Employment")
	protected List<Employment> employments;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "person_fk")
	@XmlElementWrapper(name = "Interests")
	@XmlElement(name = "Interest")
	protected List<Interest> interests;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "person_fk")
	@XmlElementWrapper(name = "ThirdPersons")
	@XmlElement(name = "Person")
	protected List<Person> thirdPersons;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "person_fk")
	@XmlElementWrapper(name = "Autos")
	@XmlElement(name = "Auto")
	protected List<Auto> autos;

	public Integer getPersonID() {
		return personID;
	}

	public void setPersonID(Integer personID) {
		this.personID = personID;
	}

	public int getPersonType() {
		return personType;
	}

	public void setPersonType(int personType) {
		this.personType = personType;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPatronymicName() {
		return patronymicName;
	}

	public void setPatronymicName(String patronymicName) {
		this.patronymicName = patronymicName;
	}

	public Byte getIsNoPatronymic() {
		return isNoPatronymic;
	}

	public void setIsNoPatronymic(Byte isNoPatronymic) {
		this.isNoPatronymic = isNoPatronymic;
	}

	public String getPreviousLastName() {
		return previousLastName;
	}

	public void setPreviousLastName(String previousLastName) {
		this.previousLastName = previousLastName;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getDeathDate() {
		return deathDate;
	}

	public void setDeathDate(Date deathDate) {
		this.deathDate = deathDate;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getCitizenship() {
		return citizenship;
	}

	public void setCitizenship(Integer citizenship) {
		this.citizenship = citizenship;
	}

	public Integer getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(Integer maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Integer getEducation() {
		return education;
	}

	public void setEducation(Integer education) {
		this.education = education;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWorkEmail() {
		return workEmail;
	}

	public void setWorkEmail(String workEmail) {
		this.workEmail = workEmail;
	}

	public EAddress getRegAddress() {
		if (regAddress == null)
			regAddress = new EAddress();
		return regAddress;
	}

	public void setRegAddress(EAddress regAddress) {
		this.regAddress = regAddress;
	}

	public EAddress getFactAddress() {
		if (factAddress == null)
			factAddress = new EAddress();
		return factAddress;
	}

	public void setFactAddress(EAddress factAddress) {
		this.factAddress = factAddress;
	}

	public PersonCalcs getPersonCalcs() {
		if (personCalcs == null)
			personCalcs = new PersonCalcs();
		return personCalcs;
	}

	public void setPersonCalcs(PersonCalcs personCalcs) {
		this.personCalcs = personCalcs;
	}

	public List<Document> getDocuments() {
		if (documents == null)
			documents = new ArrayList<>();
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public List<Phone> getPhones() {
		if (phones == null)
			phones = new ArrayList<>();
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public List<SocialNetwork> getSocialNetworks() {
		if (socialNetworks == null)
			socialNetworks = new ArrayList<>();
		return socialNetworks;
	}

	public void setSocialNetworks(List<SocialNetwork> socialNetworks) {
		this.socialNetworks = socialNetworks;
	}

	public List<Messanger> getMessangers() {
		if (messangers == null)
			messangers = new ArrayList<>();
		return messangers;
	}

	public void setMessangers(List<Messanger> messangers) {
		this.messangers = messangers;
	}

	public List<Employment> getEmployments() {
		if (employments == null)
			employments = new ArrayList<>();
		return employments;
	}

	public void setEmployments(List<Employment> employments) {
		this.employments = employments;
	}

	public List<Interest> getInterests() {
		if (interests == null)
			interests = new ArrayList<>();
		return interests;
	}

	public void setInterests(List<Interest> interests) {
		this.interests = interests;
	}

	public List<Person> getThirdPersons() {
		if (thirdPersons == null)
			thirdPersons = new ArrayList<>();
		return thirdPersons;
	}

	public void setThirdPersons(List<Person> thirdPersons) {
		this.thirdPersons = thirdPersons;
	}

	public List<Auto> getAutos() {
		if (autos == null)
			autos = new ArrayList<>();
		return autos;
	}

	public void setAutos(List<Auto> autos) {
		this.autos = autos;
	}

	public void writeExternal(ObjectOutput out) throws IOException {
		td.writeNullableObject(out, personID);
		td.writeNullableObject(out, personType);
		td.writeNullableObject(out, firstName);
		td.writeNullableObject(out, lastName);
		td.writeNullableObject(out, patronymicName);
		td.writeNullableObject(out, isNoPatronymic);
		td.writeNullableObject(out, previousLastName);
		td.writeNullableObject(out, birthPlace);
		td.writeNullableObject(out, birthDate);
		td.writeNullableObject(out, deathDate);
		td.writeNullableObject(out, age);
		td.writeNullableObject(out, sex);
		td.writeNullableObject(out, citizenship);
		td.writeNullableObject(out, maritalStatus);
		td.writeNullableObject(out, education);
		td.writeNullableObject(out, email);
		td.writeNullableObject(out, workEmail);
		getRegAddress().writeExternal(out);
		getFactAddress().writeExternal(out);
		getPersonCalcs().writeExternal(out);

		out.writeInt(getDocuments().size());
		for (Externalizable ext : getDocuments())
			ext.writeExternal(out);

		out.writeInt(getPhones().size());
		for (Externalizable ext : getPhones())
			ext.writeExternal(out);

		out.writeInt(getSocialNetworks().size());
		for (Externalizable ext : getSocialNetworks())
			ext.writeExternal(out);

		out.writeInt(getMessangers().size());
		for (Externalizable ext : getMessangers())
			ext.writeExternal(out);

		out.writeInt(getEmployments().size());
		for (Externalizable ext : getEmployments())
			ext.writeExternal(out);

		out.writeInt(getInterests().size());
		for (Externalizable ext : getInterests())
			ext.writeExternal(out);

		out.writeInt(getThirdPersons().size());
		for (Externalizable ext : getThirdPersons())
			ext.writeExternal(out);

		out.writeInt(getAutos().size());
		for (Externalizable ext : getAutos())
			ext.writeExternal(out);
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		personID = in.readBoolean() == true ? in.readInt() : null;
		personType = in.readBoolean() == true ? in.readInt() : null;
		firstName = in.readBoolean() == true ? in.readUTF() : null;
		lastName = in.readBoolean() == true ? in.readUTF() : null;
		patronymicName = in.readBoolean() == true ? in.readUTF() : null;
		isNoPatronymic = in.readBoolean() == true ? in.readByte() : null;
		previousLastName = in.readBoolean() == true ? in.readUTF() : null;
		birthPlace = in.readBoolean() == true ? in.readUTF() : null;
		birthDate = in.readBoolean() == true ? new Date(in.readLong()) : null;
		deathDate = in.readBoolean() == true ? new Date(in.readLong()) : null;
		age = in.readBoolean() == true ? in.readInt() : null;
		sex = in.readBoolean() == true ? in.readInt() : null;
		citizenship = in.readBoolean() == true ? in.readInt() : null;
		maritalStatus = in.readBoolean() == true ? in.readInt() : null;
		education = in.readBoolean() == true ? in.readInt() : null;
		email = in.readBoolean() == true ? in.readUTF() : null;
		workEmail = in.readBoolean() == true ? in.readUTF() : null;
		getRegAddress().readExternal(in);
		getFactAddress().readExternal(in);
		getPersonCalcs().readExternal(in);

		int count = in.readInt();
		for (int i = 0; i < count; i++) {
			Document ext = new Document();
			ext.readExternal(in);
			getDocuments().add(ext);
		}

		count = in.readInt();
		for (int i = 0; i < count; i++) {
			Phone ext = new Phone();
			ext.readExternal(in);
			getPhones().add(ext);
		}

		count = in.readInt();
		for (int i = 0; i < count; i++) {
			SocialNetwork ext = new SocialNetwork();
			ext.readExternal(in);
			getSocialNetworks().add(ext);
		}

		count = in.readInt();
		for (int i = 0; i < count; i++) {
			Messanger ext = new Messanger();
			ext.readExternal(in);
			getMessangers().add(ext);
		}

		count = in.readInt();
		for (int i = 0; i < count; i++) {
			Employment ext = new Employment();
			ext.readExternal(in);
			getEmployments().add(ext);
		}

		count = in.readInt();
		for (int i = 0; i < count; i++) {
			Interest ext = new Interest();
			ext.readExternal(in);
			getInterests().add(ext);
		}

		count = in.readInt();
		for (int i = 0; i < count; i++) {
			Person ext = new Person();
			ext.readExternal(in);
			getThirdPersons().add(ext);
		}

		count = in.readInt();
		for (int i = 0; i < count; i++) {
			Auto ext = new Auto();
			ext.readExternal(in);
			getAutos().add(ext);
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