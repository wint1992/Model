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
@Table(name = "call_type", schema = "mdm")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class CallType implements Externalizable {
    private static final long serialVersionUID = 1l;

    @Id
    @SequenceGenerator(name = "seq_gen_call_type", sequenceName = "call_type_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen_call_type")
    @Column(name = "call_type_id")
    @XmlAttribute
    protected Integer callTypeId;

    @Column(name = "call_type_name")
    @XmlAttribute
    protected String callTypeName;

    public void writeExternal(ObjectOutput out) throws IOException {
        writeNullableObject(out, callTypeId);
        writeNullableObject(out, callTypeName);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        callTypeId = readIntFromObjectInput(in);
        callTypeName = readStringFromObjectInput(in);
    }
}
