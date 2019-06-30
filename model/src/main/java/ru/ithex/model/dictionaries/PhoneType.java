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
@Table(name = "phone_type", schema = "mdm")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class PhoneType implements Externalizable {
    private static final long serialVersionUID = 1l;

    @Id
    @SequenceGenerator(name = "seq_gen_phone_type", sequenceName = "phone_type_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen_phone_type")
    @Column(name = "phone_type_id")
    @XmlAttribute
    protected Integer phoneTypeId;

    @Column(name = "phone_type_name")
    @XmlAttribute
    protected String phoneTypeName;

    public void writeExternal(ObjectOutput out) throws IOException {
        writeNullableObject(out, phoneTypeId);
        writeNullableObject(out, phoneTypeName);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        phoneTypeId = readIntFromObjectInput(in);
        phoneTypeName = readStringFromObjectInput(in);
    }
}
