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
import java.util.Date;

import static ru.ithex.model.utils.Serialization.*;

@Entity
@Table(name = "promotion", schema = "mdm")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Promotion implements Externalizable {
    private static final long serialVersionUID = 1l;

    @Id
    @SequenceGenerator(name = "seq_gen_promotion", sequenceName = "promotion_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen_promotion")
    @Column(name = "promotion_id")
    @XmlAttribute
    protected Integer promotionID;

    @XmlAttribute
    protected String title;

    @XmlAttribute
    protected String code;

    @Column(name = "begin_date_time")
    @XmlAttribute
    protected Date beginDateTime;

    @Column(name = "end_date_time")
    @XmlAttribute
    protected Date endDateTime;

    @XmlAttribute
    protected String description;

    public void writeExternal(ObjectOutput out) throws IOException {
        writeNullableObject(out, promotionID);
        writeNullableObject(out, title);
        writeNullableObject(out, code);
        writeNullableObject(out, beginDateTime);
        writeNullableObject(out, endDateTime);
        writeNullableObject(out, description);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        promotionID = readIntFromObjectInput(in);
        title = readStringFromObjectInput(in);
        code = readStringFromObjectInput(in);
        beginDateTime = readLongToDateFromObjectInput(in);
        endDateTime = readLongToDateFromObjectInput(in);
        description = readStringFromObjectInput(in);
    }
}
