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
@Table(name = "messenger_type", schema = "mdm")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class MessengerType implements Externalizable {
    private static final long serialVersionUID = 1l;

    @Id
    @SequenceGenerator(name = "seq_gen_messenger_type", sequenceName = "messenger_type_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen_messenger_type")
    @Column(name = "messenger_type_id")
    @XmlAttribute
    protected Integer messengerTypeId;

    @Column(name = "messenger_type_name")
    @XmlAttribute
    protected String messengerTypeName;

    public void writeExternal(ObjectOutput out) throws IOException {
        writeNullableObject(out, messengerTypeId);
        writeNullableObject(out, messengerTypeName);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        messengerTypeId = readIntFromObjectInput(in);
        messengerTypeName = readStringFromObjectInput(in);
    }
}
