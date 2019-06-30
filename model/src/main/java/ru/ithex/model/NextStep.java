package ru.ithex.model;

import lombok.Data;
import ru.ithex.model.abstraction.AbstractNextStep;
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

@XmlRootElement(name = "NextStep")
@Entity
@Table(name = "next_step")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class NextStep extends AbstractNextStep {
	private static final long serialVersionUID = 1l;

	@Id
	@SequenceGenerator(name = "seq_gen_next_step", sequenceName = "next_step_id_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen_next_step")
	@Column(name = "next_step_id")
	@XmlAttribute
	protected Integer nextStepID;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "next_step_id")
	@XmlElementWrapper(name = "StepParameters")
	@XmlElement(name = "StepParameter")
	protected List<StepParameter> stepParameters;

	public List<StepParameter> getStepParametrs() {
		if (stepParameters == null) stepParameters = new ArrayList<>();
		return stepParameters;
	}

	public void writeExternal(ObjectOutput out) throws IOException {
		super.writeExternal(out);
		writeNullableObject(out, nextStepID);

		out.writeInt(getStepParametrs().size());
		for (Externalizable ext : getStepParametrs())
			ext.writeExternal(out);

	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		super.readExternal(in);
		nextStepID = readIntFromObjectInput(in);

		int count = in.readInt();
		for (int i = 0; i < count; i++) {
			StepParameter ext = new StepParameter();
			ext.readExternal(in);
			getStepParametrs().add(ext);
		}
	}
}