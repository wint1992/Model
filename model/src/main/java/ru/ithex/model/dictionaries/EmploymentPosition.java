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
@Table(name = "employment_position", schema = "mdm")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class EmploymentPosition implements Externalizable {
    private static final long serialVersionUID = 1l;

    @Id
    @SequenceGenerator(name = "seq_gen_employment_position", sequenceName = "employment_position_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen_employment_position")
    @Column(name = "employment_position_id")
    @XmlAttribute
    protected Integer employmentPositionId;

    @Column(name = "employment_position_name")
    @XmlAttribute
    protected String employmentPositionName;

    public void writeExternal(ObjectOutput out) throws IOException {
        writeNullableObject(out, employmentPositionId);
        writeNullableObject(out, employmentPositionName);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        employmentPositionId = readIntFromObjectInput(in);
        employmentPositionName = readStringFromObjectInput(in);
    }
}
