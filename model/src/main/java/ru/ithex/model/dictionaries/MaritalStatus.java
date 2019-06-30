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
@Table(name = "marital_status", schema = "mdm")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class MaritalStatus implements Externalizable {
    private static final long serialVersionUID = 1l;

    @Id
    @SequenceGenerator(name = "seq_gen_marital_status", sequenceName = "marital_status_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen_marital_status")
    @Column(name = "marital_status_id")
    @XmlAttribute
    protected Integer maritalStatusId;

    @Column(name = "marital_status_name")
    @XmlAttribute
    protected String maritalStatusName;

    public void writeExternal(ObjectOutput out) throws IOException {
        writeNullableObject(out, maritalStatusId);
        writeNullableObject(out, maritalStatusName);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        maritalStatusId = readIntFromObjectInput(in);
        maritalStatusName = readStringFromObjectInput(in);
    }
}
