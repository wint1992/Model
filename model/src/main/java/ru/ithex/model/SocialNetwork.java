package ru.ithex.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ru.ithex.model.dictionaries.NetworkType;

import static ru.ithex.model.utils.Serialization.*;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "SocialNetwork")
@Entity
@Table(name = "social_network")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class SocialNetwork implements Externalizable {
	private static final long serialVersionUID = 1l;

	@Id
	@SequenceGenerator(name = "seq_gen_social_network", sequenceName = "social_network_id_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen_social_network")
	@Column(name = "network_id")
	@XmlAttribute
	protected Integer networkID;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "network_type_id")
	@XmlTransient
	protected NetworkType networkType;

	@XmlAttribute
	public Integer getNetworkTypeId(){ return networkType != null ? networkType.getNetworkTypeId() : null;}

	@XmlAttribute
	public String getNetworkType(){ return networkType != null ? networkType.getNetworkTypeName() : null;}

	@Column(name = "network_account")
	@XmlAttribute
	protected String networkAccount;

	@Column(name = "interest_segment1")
	@XmlAttribute
	protected String interestSegment1;

	@Column(name = "interest_segment2")
	@XmlAttribute
	protected String interestSegment2;

	@Column(name = "interest_segment3")
	@XmlAttribute
	protected String interestSegment3;

	@Column(name = "friends_num")
	@XmlAttribute
	protected Integer friendsNum;

	public void writeExternal(ObjectOutput out) throws IOException {
		writeNullableObject(out, networkID);
		writeNullableObject(out, networkAccount);
		writeNullableObject(out, interestSegment1);
		writeNullableObject(out, interestSegment2);
		writeNullableObject(out, interestSegment3);
		writeNullableObject(out, friendsNum);
		networkType.writeExternal(out);
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		networkID = readIntFromObjectInput(in);
		networkAccount = readStringFromObjectInput(in);
		interestSegment1 = readStringFromObjectInput(in);
		interestSegment2 = readStringFromObjectInput(in);
		interestSegment3 = readStringFromObjectInput(in);
		friendsNum = readIntFromObjectInput(in);
		networkType = new NetworkType();
		networkType.readExternal(in);
	}
}