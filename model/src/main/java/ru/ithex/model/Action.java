package ru.ithex.model;

import lombok.Data;
import ru.ithex.model.abstraction.AbstractAction;

import static ru.ithex.model.utils.Serialization.*;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Action")
@Entity
@Table(name = "action")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Action extends AbstractAction {
	private static final long serialVersionUID = 1l;

	@Id
	@SequenceGenerator(name = "seq_gen_action", sequenceName = "action_id_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen_action")
	@Column(name = "action_id")
	@XmlAttribute
	protected Integer actionID;

	public void writeExternal(ObjectOutput out) throws IOException {
		super.writeExternal(out);
		writeNullableObject(out, actionID);
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		super.readExternal(in);
		actionID = readIntFromObjectInput(in);
	}
}