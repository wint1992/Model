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
@Table(name = "citizenship", schema = "mdm")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Citizenship implements Externalizable {
    private static final long serialVersionUID = 1l;

    @Id
    @SequenceGenerator(name = "seq_gen_citizenship", sequenceName = "citizenship_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen_citizenship")
    @Column(name = "citizenship_id")
    @XmlAttribute
    protected Integer citizenshipId;

    @Column(name = "citizenship_name")
    @XmlAttribute
    protected String citizenshipName;

    public void writeExternal(ObjectOutput out) throws IOException {
        writeNullableObject(out, citizenshipId);
        writeNullableObject(out, citizenshipName);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        citizenshipId = readIntFromObjectInput(in);
        citizenshipName = readStringFromObjectInput(in);
    }
}
