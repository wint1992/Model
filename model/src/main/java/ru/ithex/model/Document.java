package ru.ithex.model;

import lombok.Data;
import ru.ithex.model.abstraction.AbstractDocument;
import static ru.ithex.model.utils.Serialization.*;
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

@XmlRootElement(name = "Document")
@Entity
@Table(name = "document")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Document extends AbstractDocument {
	private static final long serialVersionUID = 1l;

	@Id
	@SequenceGenerator(name = "seq_gen_document", sequenceName = "document_id_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen_document")
	@Column(name = "document_id")
	@XmlAttribute
	protected Integer documentID;

	public void writeExternal(ObjectOutput out) throws IOException {
		super.writeExternal(out);
		writeNullableObject(out, documentID);
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		super.readExternal(in);
		documentID = readIntFromObjectInput(in);
	}
}