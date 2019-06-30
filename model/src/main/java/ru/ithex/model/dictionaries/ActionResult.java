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
@Table(name = "action_result", schema = "mdm")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class ActionResult implements Externalizable {
    private static final long serialVersionUID = 1l;

    @Id
    @SequenceGenerator(name = "seq_gen_action_result", sequenceName = "action_result_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen_action_result")
    @Column(name = "action_result_id")
    @XmlAttribute
    protected Integer actionResultId;

    @Column(name = "action_result_name")
    @XmlAttribute
    protected String actionResultName;

    public void writeExternal(ObjectOutput out) throws IOException {
        writeNullableObject(out, actionResultId);
        writeNullableObject(out, actionResultName);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        actionResultId = readIntFromObjectInput(in);
        actionResultName = readStringFromObjectInput(in);
    }
}
