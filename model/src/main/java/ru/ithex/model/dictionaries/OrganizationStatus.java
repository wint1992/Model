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
@Table(name = "organization_status", schema = "mdm")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class OrganizationStatus implements Externalizable {
    private static final long serialVersionUID = 1l;

    @Id
    @SequenceGenerator(name = "seq_gen_organization_status", sequenceName = "organization_status_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen_organization_status")
    @Column(name = "organization_status_id")
    @XmlAttribute
    protected Integer organizationStatusID;

    @Column(name = "organization_status_name")
    @XmlAttribute
    protected String organizationStatusName;

    public void writeExternal(ObjectOutput out) throws IOException {
        writeNullableObject(out, organizationStatusID);
        writeNullableObject(out, organizationStatusName);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        organizationStatusID = readIntFromObjectInput(in);
        organizationStatusName = readStringFromObjectInput(in);
    }
}
