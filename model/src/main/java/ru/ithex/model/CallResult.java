package ru.ithex.model;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import ru.ithex.model.abstraction.AbstractCallResult;
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

@XmlRootElement(name = "CallResult")
@Entity
@Table(name = "call_result")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class CallResult extends AbstractCallResult {
	private static final long serialVersionUID = 1l;

	@Id
	@SequenceGenerator(name = "seq_gen_call_result", sequenceName = "call_result_id_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen_call_result")
	@Column(name = "call_result_id")
	@XmlAttribute
	protected Integer callResultID;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "call_result_id")
	@XmlElementWrapper(name = "ReasonCodes")
	@XmlElement(name = "ReasonCode")
	protected List<ReasonCode> reasonCodes;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "call_result_id")
	@XmlElementWrapper(name = "NextSteps")
	@XmlElement(name = "NextStep")
	protected List<NextStep> nextSteps;

	public List<ReasonCode> getReasonCodes() {
		if (reasonCodes == null) reasonCodes = new ArrayList<>();
		return reasonCodes;
	}

	public List<NextStep> getNextSteps() {
		if (nextSteps == null) nextSteps = new ArrayList<>();
		return nextSteps;
	}

	public void writeExternal(ObjectOutput out) throws IOException {
		super.writeExternal(out);
		writeNullableObject(out, callResultID);

		out.writeInt(getReasonCodes().size());
		for (Externalizable ext : getReasonCodes())
			ext.writeExternal(out);

		out.writeInt(getNextSteps().size());
		for (Externalizable ext : getNextSteps())
			ext.writeExternal(out);

	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		super.readExternal(in);
		callResultID = readIntFromObjectInput(in);

		int count = in.readInt();
		for (int i = 0; i < count; i++) {
			ReasonCode ext = new ReasonCode();
			ext.readExternal(in);
			getReasonCodes().add(ext);
		}

		count = in.readInt();
		for (int i = 0; i < count; i++) {
			NextStep ext = new NextStep();
			ext.readExternal(in);
			getNextSteps().add(ext);
		}
	}
}