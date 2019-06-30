package ru.ithex.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ru.ithex.model.dictionaries.ReasonCodeType;

import static ru.ithex.model.utils.Serialization.*;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Date;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "ReasonCode")
@Entity
@Table(name = "reason_code")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class ReasonCode implements Externalizable {
	private static final long serialVersionUID = 1l;

	@Id
	@SequenceGenerator(name = "seq_gen_reason_code", sequenceName = "reason_code_id_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen_reason_code")
	@Column(name = "reason_code_id")
	@XmlAttribute
	protected Integer reasonCodeID;

	@Column(name = "fired_timestamp")
	@XmlAttribute
	@XmlSchemaType(name = "dateTime")
	protected Date firedTimestamp;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "reason_code_type_id")
	@XmlTransient
	protected ReasonCodeType reasonCodeType;

	@XmlAttribute
	public Integer getReasonCodeDecision(){ return reasonCodeType != null ? reasonCodeType.getReasonCodeDecision() : null;}

	@XmlAttribute
	public String getReasonCodeValue(){ return reasonCodeType != null ? reasonCodeType.getReasonCodeValue() : null;}

	public void writeExternal(ObjectOutput out) throws IOException {
		writeNullableObject(out, reasonCodeID);
		writeNullableObject(out, firedTimestamp);
		reasonCodeType.writeExternal(out);
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		reasonCodeID = readIntFromObjectInput(in);
		firedTimestamp = readLongToDateFromObjectInput(in);
		reasonCodeType = new ReasonCodeType();
		reasonCodeType.readExternal(in);
	}
}