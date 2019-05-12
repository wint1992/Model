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
@Table(name = "application")
@XmlRootElement(name = "Application")
@XmlAccessorType(XmlAccessType.FIELD)
public class Application implements Externalizable {
	private static final long serialVersionUID = 1l;
	transient private TransformData td = new TransformData();

	public Application() {
		super();
	}

	@Id
	@SequenceGenerator(name = "seq_gen", sequenceName = "application_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen")
	@Column(name = "application_id")
	@XmlAttribute
	protected Integer applicationID;

	@Column(name = "manager_id")
	@XmlAttribute
	protected Integer managerID;

	@Column(name = "is_actual")
	@XmlAttribute
	protected int isActual;

	@Column(name = "application_date_time")
	@XmlAttribute
	@XmlSchemaType(name = "dateTime")
	protected Date applicationDateTime;

	@Column(name = "application_close_date_time")
	@XmlAttribute
	@XmlSchemaType(name = "dateTime")
	protected Date applicationCloseDateTime;

	@Column(name = "application_status")
	@XmlAttribute
	protected int applicationStatus;

	@Column(name = "application_type")
	@XmlAttribute
	protected int applicationType;

	@Column(name = "application_channel")
	@XmlAttribute
	protected int applicationChannel;

	@Column(name = "delivery_date_time")
	@XmlAttribute
	@XmlSchemaType(name = "dateTime")
	protected Date deliveryDateTime;

	@Column(name = "delivery_fact_date_time")
	@XmlAttribute
	@XmlSchemaType(name = "dateTime")
	protected Date deliveryFactDateTime;

	@Column(name = "promo_id")
	@XmlAttribute
	protected Integer promoID;

	@Column(name = "partner_id")
	@XmlAttribute
	protected Integer partnerID;

	@Column(name = "promo_code")
	@XmlAttribute
	protected String promoCode;

	@Embedded
	@XmlElement(name = "ApplicationCalcs")
	protected ApplicationCalcs applicationCalcs;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "application_fk")
	@XmlElementWrapper(name = "Orders")
	@XmlElement(name = "Order")
	protected List<Order> orders;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "application_fk")
	@XmlElementWrapper(name = "Addresses")
	@XmlElement(name = "Address")
	protected List<Address> addresses;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "application_fk")
	@XmlElementWrapper(name = "Actions")
	@XmlElement(name = "Action")
	protected List<Action> actions;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "application_fk")
	@XmlElementWrapper(name = "CallResults")
	@XmlElement(name = "CallResult")
	protected List<CallResult> callResults;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "application_fk")
	@XmlElementWrapper(name = "RandomValues")
	@XmlElement(name = "RandomValue")
	protected List<RandomValue> randomValues;

	public Integer getApplicationID() {
		return applicationID;
	}

	public void setApplicationID(Integer applicationID) {
		this.applicationID = applicationID;
	}

	public Integer getManagerID() {
		return managerID;
	}

	public void setManagerID(Integer managerID) {
		this.managerID = managerID;
	}

	public Date getApplicationDateTime() {
		return applicationDateTime;
	}

	public void setApplicationDateTime(Date applicationDateTime) {
		this.applicationDateTime = applicationDateTime;
	}

	public Date getApplicationCloseDateTime() {
		return applicationCloseDateTime;
	}

	public void setApplicationCloseDateTime(Date applicationCloseDateTime) {
		this.applicationCloseDateTime = applicationCloseDateTime;
	}

	public int getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(int applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public int getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(int applicationType) {
		this.applicationType = applicationType;
	}

	public int getApplicationChannel() {
		return applicationChannel;
	}

	public void setApplicationChannel(int applicationChannel) {
		this.applicationChannel = applicationChannel;
	}

	public Date getDeliveryDateTime() {
		return deliveryDateTime;
	}

	public void setDeliveryDateTime(Date deliveryDateTime) {
		this.deliveryDateTime = deliveryDateTime;
	}

	public Date getDeliveryFactDateTime() {
		return deliveryFactDateTime;
	}

	public void setDeliveryFactDateTime(Date deliveryFactDateTime) {
		this.deliveryFactDateTime = deliveryFactDateTime;
	}

	public ApplicationCalcs getApplicationCalcs() {
		if (applicationCalcs == null)
			applicationCalcs = new ApplicationCalcs();
		return applicationCalcs;
	}

	public void setApplicationCalcs(ApplicationCalcs applicationCalcs) {
		this.applicationCalcs = applicationCalcs;
	}

	public List<Order> getOrders() {
		if (orders == null)
			orders = new ArrayList<>();
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<Address> getAddresses() {
		if (addresses == null)
			addresses = new ArrayList<>();
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<Action> getActions() {
		if (actions == null)
			actions = new ArrayList<>();
		return actions;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	}

	public List<CallResult> getCallResults() {
		if (callResults == null)
			callResults = new ArrayList<>();
		return callResults;
	}

	public void setCallResults(List<CallResult> callResults) {
		this.callResults = callResults;
	}

	public List<RandomValue> getRandomValues() {
		if (randomValues == null)
			randomValues = new ArrayList<>();
		return randomValues;
	}

	public void setRandomValues(List<RandomValue> randomValues) {
		this.randomValues = randomValues;
	}

	public int getIsActual() {
		return isActual;
	}

	public void setIsActual(int isActual) {
		this.isActual = isActual;
	}

	public Integer getPromoID() {
		return promoID;
	}

	public void setPromoID(Integer promoID) {
		this.promoID = promoID;
	}

	public Integer getPartnerID() {
		return partnerID;
	}

	public void setPartnerID(Integer partnerID) {
		this.partnerID = partnerID;
	}

	public String getPromoCode() {
		return promoCode;
	}

	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}

	public void writeExternal(ObjectOutput out) throws IOException {
		td.writeNullableObject(out, applicationID);
		td.writeNullableObject(out, managerID);
		td.writeNullableObject(out, isActual);
		td.writeNullableObject(out, applicationDateTime);
		td.writeNullableObject(out, applicationCloseDateTime);
		td.writeNullableObject(out, applicationStatus);
		td.writeNullableObject(out, applicationType);
		td.writeNullableObject(out, applicationChannel);
		td.writeNullableObject(out, deliveryDateTime);
		td.writeNullableObject(out, deliveryFactDateTime);
		td.writeNullableObject(out, promoID);
		td.writeNullableObject(out, partnerID);
		td.writeNullableObject(out, promoCode);

		getApplicationCalcs().writeExternal(out);

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
		applicationID = in.readBoolean() == true ? in.readInt() : null;
		managerID = in.readBoolean() == true ? in.readInt() : null;
		isActual = in.readBoolean() == true ? in.readInt() : null;
		applicationDateTime = in.readBoolean() == true ? new Date(in.readLong()) : null;
		applicationCloseDateTime = in.readBoolean() == true ? new Date(in.readLong()) : null;
		applicationStatus = in.readBoolean() == true ? in.readInt() : null;
		applicationType = in.readBoolean() == true ? in.readInt() : null;
		applicationChannel = in.readBoolean() == true ? in.readInt() : null;
		deliveryDateTime = in.readBoolean() == true ? new Date(in.readLong()) : null;
		deliveryFactDateTime = in.readBoolean() == true ? new Date(in.readLong()) : null;
		promoID = in.readBoolean() == true ? in.readInt() : null;
		partnerID = in.readBoolean() == true ? in.readInt() : null;
		promoCode = in.readBoolean() == true ? in.readUTF() : null;

		getApplicationCalcs().readExternal(in);

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
	// \"").append(transformTimestamp((Date) fields[i].get(this)).toString())
	// .append("\"");
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