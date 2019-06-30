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
@Table(name = "partner", schema = "mdm")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Partner implements Externalizable {
    private static final long serialVersionUID = 1l;

    @Id
    @SequenceGenerator(name = "seq_gen_partner", sequenceName = "partner_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen_partner")
    @Column(name = "partner_id")
    @XmlAttribute
    protected Integer partnerID;

    @XmlAttribute
    protected String value;

    public void writeExternal(ObjectOutput out) throws IOException {
        writeNullableObject(out, partnerID);
        writeNullableObject(out, value);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        partnerID = readIntFromObjectInput(in);
        value = readStringFromObjectInput(in);
    }
}
