package ru.ithex.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ru.ithex.model.dictionaries.PersonSegment;

import static ru.ithex.model.utils.Serialization.*;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.math.BigDecimal;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@Embeddable
@Data
@XmlRootElement(name = "PersonCalcs")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonCalcs implements Externalizable {
	private static final long serialVersionUID = 1l;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pc_person_segment_id")
	@XmlTransient
	protected PersonSegment personSegment;

	@XmlAttribute
	public Integer getPersonSegmentId(){ return personSegment != null ? personSegment.getPersonSegmentId() : null;}

	@XmlAttribute
	public String getPersonSegment(){ return personSegment != null ? personSegment.getPersonSegmentName() : null;}

	@Column(name = "pc_total_income")
	@XmlAttribute
	protected BigDecimal totalIncome;

	public void writeExternal(ObjectOutput out) throws IOException {
		writeNullableObject(out, totalIncome);
		personSegment.writeExternal(out);
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		totalIncome = readBigDecimalFromObjectInput(in);
		personSegment = new PersonSegment();
		personSegment.readExternal(in);
	}
}