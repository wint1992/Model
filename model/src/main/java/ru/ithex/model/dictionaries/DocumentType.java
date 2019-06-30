package ru.ithex.model.dictionaries;

import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import static ru.ithex.model.utils.Serialization.*;

@Entity
@Table(name = "document_type", schema = "mdm")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class DocumentType implements Externalizable {
    private static final long serialVersionUID = 1l;

    @Id
    @SequenceGenerator(name = "seq_gen_document_type", sequenceName = "document_type_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen_document_type")
    @Column(name = "document_type_id")
    @XmlAttribute
    protected Integer documentTypeId;

    @Column(name = "document_type_name")
    @XmlAttribute
    protected String documentTypeName;

    public void writeExternal(ObjectOutput out) throws IOException {
        writeNullableObject(out, documentTypeId);
        writeNullableObject(out, documentTypeName);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        documentTypeId = readIntFromObjectInput(in);
        documentTypeName = readStringFromObjectInput(in);
    }
}
