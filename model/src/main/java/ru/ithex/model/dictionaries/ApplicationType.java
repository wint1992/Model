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
@Table(name = "application_type", schema = "mdm")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class ApplicationType implements Externalizable {
    private static final long serialVersionUID = 1l;

    @Id
    @SequenceGenerator(name = "seq_gen_application_type", sequenceName = "application_type_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen_application_type")
    @Column(name = "application_type_id")
    @XmlAttribute
    protected Integer applicationTypeId;

    @Column(name = "application_type_name")
    @XmlAttribute
    protected String applicationTypeName;

    public void writeExternal(ObjectOutput out) throws IOException {
        writeNullableObject(out, applicationTypeId);
        writeNullableObject(out, applicationTypeName);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        applicationTypeId = readIntFromObjectInput(in);
        applicationTypeName = readStringFromObjectInput(in);
    }
}
