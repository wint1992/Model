package ru.ithex.model.abstraction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ru.ithex.model.dictionaries.CallType;

import static ru.ithex.model.utils.Serialization.*;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Date;

@MappedSuperclass
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public abstract class AbstractCallResult implements Externalizable {
    private static final long serialVersionUID = 1l;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "call_type_id")
    @XmlTransient
    protected CallType callType;

    @XmlAttribute
    public Integer getCallTypeId(){ return callType != null ? callType.getCallTypeId() : null;}

    @XmlAttribute
    public String getCallType(){ return callType != null ? callType.getCallTypeName() : null;}

    @Column(name = "is_active")
    @XmlAttribute
    protected boolean isActive;

    @Column(name = "call_date_time")
    @XmlAttribute
    @XmlSchemaType(name = "dateTime")
    protected Date callDateTime;

    @Column(name = "call_decision")
    @XmlAttribute
    protected int callDecision;

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeBoolean(isActive);
        writeNullableObject(out, callDateTime);
        out.writeInt(callDecision);
        callType.writeExternal(out);

    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        isActive = in.readBoolean();
        callDateTime = readLongToDateFromObjectInput(in);
        callDecision = in.readInt();
        callType = new CallType();
        callType.readExternal(in);
    }
}
