package ru.ithex.model.abstraction;

import lombok.Data;
import static ru.ithex.model.utils.Serialization.*;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Date;

@MappedSuperclass
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public abstract class AbstractNextStep implements Externalizable {
    private static final long serialVersionUID = 1l;

    @Column(name = "step_code")
    @XmlAttribute
    protected int stepCode;

    @Column(name = "step_plane_date_time")
    @XmlAttribute
    @XmlSchemaType(name = "dateTime")
    protected Date stepPlaneDateTime;

    @Column(name = "step_priority")
    @XmlAttribute
    protected int stepPriority;

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(stepCode);
        writeNullableObject(out, stepPlaneDateTime);
        out.writeInt(stepPriority);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        stepCode = in.readInt();
        stepPlaneDateTime = readLongToDateFromObjectInput(in);
        stepPriority = in.readInt();
    }
}
