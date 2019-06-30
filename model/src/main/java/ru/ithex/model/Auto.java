package ru.ithex.model;

import lombok.Data;
import ru.ithex.model.abstraction.AbstractAuto;
import ru.ithex.model.utils.TransformData;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import static ru.ithex.model.utils.Serialization.*;

@XmlRootElement(name = "Auto")
@Entity
@Table(name = "auto")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Auto extends AbstractAuto {
	private static final long serialVersionUID = 1l;

	@Id
	@SequenceGenerator(name = "seq_gen_auto", sequenceName = "auto_id_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen_auto")
	@Column(name = "auto_id")
	@XmlAttribute
	protected Integer autoID;

	public void writeExternal(ObjectOutput out) throws IOException {
		super.writeExternal(out);
		writeNullableObject(out, autoID);
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		super.readExternal(in);
		autoID = readIntFromObjectInput(in);
	}
}