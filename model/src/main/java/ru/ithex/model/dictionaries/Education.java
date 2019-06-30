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
@Table(name = "education", schema = "mdm")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Education implements Externalizable {
    private static final long serialVersionUID = 1l;

    @Id
    @SequenceGenerator(name = "seq_gen_education", sequenceName = "education_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen_education")
    @Column(name = "education_id")
    @XmlAttribute
    protected Integer educationId;

    @Column(name = "education_name")
    @XmlAttribute
    protected String educationName;

    public void writeExternal(ObjectOutput out) throws IOException {
        writeNullableObject(out, educationId);
        writeNullableObject(out, educationName);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        educationId = readIntFromObjectInput(in);
        educationName = readStringFromObjectInput(in);
    }
}
