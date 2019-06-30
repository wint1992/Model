package ru.ithex.model.abstraction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ru.ithex.model.dictionaries.PhoneType;

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
public abstract class AbstractPhone implements Externalizable {
    private static final long serialVersionUID = 1l;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "phone_type_id")
    @XmlTransient
    protected PhoneType phoneType;

    @XmlAttribute
    public Integer getPhoneTypeId(){ return phoneType != null ? phoneType.getPhoneTypeId() : null;}

    @XmlAttribute
    public String getPhoneType(){ return phoneType != null ? phoneType.getPhoneTypeName() : null;}

    @Column(name = "phone_number")
    @XmlAttribute
    protected String phoneNumber;

    @Column(name = "is_actual")
    @XmlAttribute
    protected Boolean isActual;

    @Column(name = "calls_count")
    @XmlAttribute
    protected Integer callsCount;

    @Column(name = "contacts_count")
    @XmlAttribute
    protected Integer contactsCount;

    @Column(name = "phone_contact_date_time")
    @XmlAttribute
    @XmlSchemaType(name = "dateTime")
    protected Date phoneContactDateTime;

    @Column(name = "operator")
    @XmlAttribute
    protected String operator;

    public void writeExternal(ObjectOutput out) throws IOException {
        writeNullableObject(out, phoneNumber);
        writeNullableObject(out, isActual);
        writeNullableObject(out, callsCount);
        writeNullableObject(out, contactsCount);
        writeNullableObject(out, phoneContactDateTime);
        writeNullableObject(out, operator);
        phoneType.writeExternal(out);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        phoneNumber = readStringFromObjectInput(in);
        isActual = readBooleanFromObjectInput(in);
        callsCount = readIntFromObjectInput(in);
        contactsCount = readIntFromObjectInput(in);
        phoneContactDateTime = readLongToDateFromObjectInput(in);
        operator = readStringFromObjectInput(in);
        phoneType = new PhoneType();
        phoneType.readExternal(in);
    }
}
