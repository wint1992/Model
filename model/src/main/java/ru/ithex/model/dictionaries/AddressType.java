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
@Table(name = "address_type", schema = "mdm")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class AddressType implements Externalizable {
    private static final long serialVersionUID = 1l;

    @Id
    @SequenceGenerator(name = "seq_gen_address_type", sequenceName = "address_type_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen_address_type")
    @Column(name = "address_type_id")
    @XmlAttribute
    protected Integer addressTypeId;

    @Column(name = "address_type_name")
    @XmlAttribute
    protected String addressTypeName;

    public void writeExternal(ObjectOutput out) throws IOException {
        writeNullableObject(out, addressTypeId);
        writeNullableObject(out, addressTypeName);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        addressTypeId = readIntFromObjectInput(in);
        addressTypeName = readStringFromObjectInput(in);
    }
}
