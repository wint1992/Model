package ru.ithex.model;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ru.ithex.model.abstraction.AbstractApplication;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

import static ru.ithex.model.utils.Serialization.*;

@XmlRootElement(name = "Application")
@Entity
@Table(name = "application")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Application extends AbstractApplication {
	private static final long serialVersionUID = 1l;

	@Id
	@SequenceGenerator(name = "seq_gen_application", sequenceName = "application_id_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen_application")
	@Column(name = "application_id")
	@XmlAttribute
	protected Integer applicationID;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "application_id")
	@XmlElementWrapper(name = "Orders")
	@XmlElement(name = "Order")
	protected List<Order> orders;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "application_id")
	@XmlElementWrapper(name = "Addresses")
	@XmlElement(name = "Address")
	protected List<Address> addresses;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "application_id")
	@XmlElementWrapper(name = "Actions")
	@XmlElement(name = "Action")
	protected List<Action> actions;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "application_id")
	@XmlElementWrapper(name = "CallResults")
	@XmlElement(name = "CallResult")
	protected List<CallResult> callResults;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "application_id")
	@XmlElementWrapper(name = "RandomValues")
	@XmlElement(name = "RandomValue")
	protected List<RandomValue> randomValues;

	public List<Order> getOrders() {
		if (orders == null) orders = new ArrayList<>();
		return orders;
	}

	public List<Address> getAddresses() {
		if (addresses == null) addresses = new ArrayList<>();
		return addresses;
	}

	public List<Action> getActions() {
		if (actions == null) actions = new ArrayList<>();
		return actions;
	}

	public List<CallResult> getCallResults() {
		if (callResults == null) callResults = new ArrayList<>();
		return callResults;
	}

	public List<RandomValue> getRandomValues() {
		if (randomValues == null) randomValues = new ArrayList<>();
		return randomValues;
	}

	public void writeExternal(ObjectOutput out) throws IOException {
		super.writeExternal(out);
		writeNullableObject(out, applicationID);

		out.writeInt(getOrders().size());
		for (Externalizable ext : getOrders())
			ext.writeExternal(out);

		out.writeInt(getAddresses().size());
		for (Externalizable ext : getAddresses())
			ext.writeExternal(out);

		out.writeInt(getActions().size());
		for (Externalizable ext : getActions())
			ext.writeExternal(out);

		out.writeInt(getCallResults().size());
		for (Externalizable ext : getCallResults())
			ext.writeExternal(out);

		out.writeInt(getRandomValues().size());
		for (Externalizable ext : getRandomValues())
			ext.writeExternal(out);
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		super.readExternal(in);
		applicationID = readIntFromObjectInput(in);

		int count = in.readInt();
		for (int i = 0; i < count; i++) {
			Order ext = new Order();
			ext.readExternal(in);
			getOrders().add(ext);
		}

		count = in.readInt();
		for (int i = 0; i < count; i++) {
			Address ext = new Address();
			ext.readExternal(in);
			getAddresses().add(ext);
		}

		count = in.readInt();
		for (int i = 0; i < count; i++) {
			Action ext = new Action();
			ext.readExternal(in);
			getActions().add(ext);
		}

		count = in.readInt();
		for (int i = 0; i < count; i++) {
			CallResult ext = new CallResult();
			ext.readExternal(in);
			getCallResults().add(ext);
		}

		count = in.readInt();
		for (int i = 0; i < count; i++) {
			RandomValue ext = new RandomValue();
			ext.readExternal(in);
			getRandomValues().add(ext);
		}
	}
}