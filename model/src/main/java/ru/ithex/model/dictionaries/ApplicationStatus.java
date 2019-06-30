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
@Table(name = "application_status", schema = "mdm")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class ApplicationStatus implements Externalizable {
    private static final long serialVersionUID = 1l;

    @Id
    @SequenceGenerator(name = "seq_gen_application_status", sequenceName = "application_status_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen_application_status")
    @Column(name = "application_status_id")
    @XmlAttribute
    protected Integer applicationStatusId;

    @Column(name = "application_status_name")
    @XmlAttribute
    protected String applicationStatusName;

    public void writeExternal(ObjectOutput out) throws IOException {
        writeNullableObject(out, applicationStatusId);
        writeNullableObject(out, applicationStatusName);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        applicationStatusId = readIntFromObjectInput(in);
        applicationStatusName = readStringFromObjectInput(in);
    }
}
