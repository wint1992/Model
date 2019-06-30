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
@Table(name = "network_type", schema = "mdm")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class NetworkType implements Externalizable {
    private static final long serialVersionUID = 1l;

    @Id
    @SequenceGenerator(name = "seq_gen_network_type", sequenceName = "network_type_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen_network_type")
    @Column(name = "network_type_id")
    @XmlAttribute
    protected Integer networkTypeId;

    @Column(name = "network_type_name")
    @XmlAttribute
    protected String networkTypeName;

    public void writeExternal(ObjectOutput out) throws IOException {
        writeNullableObject(out, networkTypeId);
        writeNullableObject(out, networkTypeName);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        networkTypeId = readIntFromObjectInput(in);
        networkTypeName = readStringFromObjectInput(in);
    }
}
