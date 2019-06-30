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
@Table(name = "person_segment", schema = "mdm")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonSegment implements Externalizable {
    private static final long serialVersionUID = 1l;

    @Id
    @SequenceGenerator(name = "seq_gen_person_segment", sequenceName = "person_segment_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen_person_segment")
    @Column(name = "person_segment_id")
    @XmlAttribute
    protected Integer personSegmentId;

    @Column(name = "person_segment_name")
    @XmlAttribute
    protected String personSegmentName;

    public void writeExternal(ObjectOutput out) throws IOException {
        writeNullableObject(out, personSegmentId);
        writeNullableObject(out, personSegmentName);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        personSegmentId = readIntFromObjectInput(in);
        personSegmentName = readStringFromObjectInput(in);
    }
}
