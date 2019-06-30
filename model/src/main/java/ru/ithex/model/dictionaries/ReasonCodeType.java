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
@Table(name = "reason_code_type", schema = "mdm")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class ReasonCodeType implements Externalizable {
    private static final long serialVersionUID = 1l;

    @Id
    @SequenceGenerator(name = "seq_gen_reason_code_type", sequenceName = "reason_code_type_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen_reason_code_type")
    @Column(name = "reason_code_type_id")
    @XmlAttribute
    protected Integer reasonCodeTypeId;

    @Column(name = "reason_code_decision")
    @XmlAttribute
    protected Integer reasonCodeDecision;

    @Column(name = "reason_code_value")
    @XmlAttribute
    protected String reasonCodeValue;

    @Column(name = "reason_code_description")
    @XmlAttribute
    protected String reasonCodeDescription;

    public void writeExternal(ObjectOutput out) throws IOException {
        writeNullableObject(out, reasonCodeTypeId);
        writeNullableObject(out, reasonCodeDecision);
        writeNullableObject(out, reasonCodeValue);
        writeNullableObject(out, reasonCodeDescription);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        reasonCodeTypeId = readIntFromObjectInput(in);
        reasonCodeDecision = readIntFromObjectInput(in);
        reasonCodeValue = readStringFromObjectInput(in);
        reasonCodeDescription = readStringFromObjectInput(in);
    }
}
