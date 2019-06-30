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
@Table(name = "person_type", schema = "mdm")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonType implements Externalizable {
    private static final long serialVersionUID = 1l;

    @Id
    @SequenceGenerator(name = "seq_gen_person_type", sequenceName = "person_type_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen_person_type")
    @Column(name = "person_type_id")
    @XmlAttribute
    protected Integer personTypeId;

    @Column(name = "person_type_name")
    @XmlAttribute
    protected String personTypeName;

    public void writeExternal(ObjectOutput out) throws IOException {
        writeNullableObject(out, personTypeId);
        writeNullableObject(out, personTypeName);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        personTypeId = readIntFromObjectInput(in);
        personTypeName = readStringFromObjectInput(in);
    }
}
