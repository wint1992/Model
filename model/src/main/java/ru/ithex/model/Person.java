package ru.ithex.model;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ru.ithex.model.abstraction.AbstractPerson;
import ru.ithex.model.dictionaries.InterestType;

import static ru.ithex.model.utils.Serialization.*;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Person")
@Entity
@Table(name = "person")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Person extends AbstractPerson {
	private static final long serialVersionUID = 1l;

	@Id
	@SequenceGenerator(name = "seq_gen_person", sequenceName = "person_id_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen_person")
	@Column(name = "person_id")
	@XmlAttribute
	protected Integer personID;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "person_id")
	@XmlElementWrapper(name = "Documents")
	@XmlElement(name = "Document")
	protected List<Document> documents;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "person_id")
	@XmlElementWrapper(name = "Phones")
	@XmlElement(name = "Phone")
	protected List<Phone> phones;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "person_id")
	@XmlElementWrapper(name = "SocialNetworks")
	@XmlElement(name = "SocialNetwork")
	protected List<SocialNetwork> socialNetworks;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "person_id")
	@XmlElementWrapper(name = "Messengers")
	@XmlElement(name = "Messenger")
	protected List<Messenger> messengers;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "person_id")
	@XmlElementWrapper(name = "Employments")
	@XmlElement(name = "Employment")
	protected List<Employment> employments;

	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name="interest",
			joinColumns = @JoinColumn(name="person_id", referencedColumnName="person_id"),
			inverseJoinColumns = @JoinColumn(name="interest_type_id", referencedColumnName="interest_type_id")
	)
	@XmlElementWrapper(name = "Interests")
	@XmlElement(name = "Interest")
	protected List<InterestType> interests;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "parent_person_id")
	@XmlElementWrapper(name = "ThirdPersons")
	@XmlElement(name = "Person")
	protected List<Person> thirdPersons;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "person_id")
	@XmlElementWrapper(name = "Autos")
	@XmlElement(name = "Auto")
	protected List<Auto> autos;

	public List<Document> getDocuments() {
		if (documents == null) documents = new ArrayList<>();
		return documents;
	}

	public List<Phone> getPhones() {
		if (phones == null) phones = new ArrayList<>();
		return phones;
	}

	public List<SocialNetwork> getSocialNetworks() {
		if (socialNetworks == null) socialNetworks = new ArrayList<>();
		return socialNetworks;
	}

	public List<Messenger> getMessangers() {
		if (messengers == null) messengers = new ArrayList<>();
		return messengers;
	}

	public List<Employment> getEmployments() {
		if (employments == null) employments = new ArrayList<>();
		return employments;
	}

	public List<InterestType> getInterests() {
		if (interests == null) interests = new ArrayList<>();
		return interests;
	}

	public List<Person> getThirdPersons() {
		if (thirdPersons == null) thirdPersons = new ArrayList<>();
		return thirdPersons;
	}

	public List<Auto> getAutos() {
		if (autos == null) autos = new ArrayList<>();
		return autos;
	}

	public void writeExternal(ObjectOutput out) throws IOException {
		super.writeExternal(out);
		writeNullableObject(out, personID);

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
		super.readExternal(in);
		personID = readIntFromObjectInput(in);

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
			Messenger ext = new Messenger();
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
			InterestType ext = new InterestType();
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
}