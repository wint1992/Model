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
@Table(name = "action_type", schema = "mdm")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class ActionType implements Externalizable {
    private static final long serialVersionUID = 1l;

    @Id
    @SequenceGenerator(name = "seq_gen_action_type", sequenceName = "action_type_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen_action_type")
    @Column(name = "action_type_id")
    @XmlAttribute
    protected Integer actionTypeId;

    @Column(name = "action_type_name")
    @XmlAttribute
    protected String actionTypeName;

    public void writeExternal(ObjectOutput out) throws IOException {
        writeNullableObject(out, actionTypeId);
        writeNullableObject(out, actionTypeName);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        actionTypeId = readIntFromObjectInput(in);
        actionTypeName = readStringFromObjectInput(in);
    }
}
