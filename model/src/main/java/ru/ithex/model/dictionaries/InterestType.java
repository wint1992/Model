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
@Table(name = "interest_type", schema = "mdm")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class InterestType implements Externalizable {
    private static final long serialVersionUID = 1l;

    @Id
    @SequenceGenerator(name = "seq_gen_interest_type", sequenceName = "interest_type_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen_interest_type")
    @Column(name = "interest_type_id")
    @XmlAttribute
    protected Integer interestTypeId;

    @Column(name = "interest_type_name")
    @XmlAttribute
    protected String interestTypeName;

    public void writeExternal(ObjectOutput out) throws IOException {
        writeNullableObject(out, interestTypeId);
        writeNullableObject(out, interestTypeName);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        interestTypeId = readIntFromObjectInput(in);
        interestTypeName = readStringFromObjectInput(in);
    }
}
