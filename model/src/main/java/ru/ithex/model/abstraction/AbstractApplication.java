package ru.ithex.model.abstraction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ru.ithex.model.*;
import ru.ithex.model.dictionaries.ApplicationChannel;
import ru.ithex.model.dictionaries.ApplicationStatus;
import ru.ithex.model.dictionaries.ApplicationType;

import static ru.ithex.model.utils.Serialization.*;
import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@MappedSuperclass
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public abstract class AbstractApplication implements Externalizable {
    private static final long serialVersionUID = 1l;

    @Column(name = "manager_id")
    @XmlAttribute
    protected Integer managerID;

    @Column(name = "is_actual")
    @XmlAttribute
    protected Boolean isActual;

    @Column(name = "application_date_time")
    @XmlAttribute
    @XmlSchemaType(name = "dateTime")
    protected Date applicationDateTime;

    @Column(name = "application_close_date_time")
    @XmlAttribute
    @XmlSchemaType(name = "dateTime")
    protected Date applicationCloseDateTime;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "application_status_id")
    @XmlTransient
    protected ApplicationStatus applicationStatus;

    @XmlAttribute
    public Integer getApplicationStatusId(){ return applicationStatus != null ? applicationStatus.getApplicationStatusId() : null;}

    @XmlAttribute
    public String getApplicationStatus(){ return applicationStatus != null ? applicationStatus.getApplicationStatusName() : null;}

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "application_type_id")
    @XmlTransient
    protected ApplicationType applicationType;

    @XmlAttribute
    public Integer getApplicationTypeId(){ return applicationType != null ? applicationType.getApplicationTypeId() : null;}

    @XmlAttribute
    public String getApplicationType(){ return applicationType != null ? applicationType.getApplicationTypeName() : null;}

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "application_channel_id")
    @XmlTransient
    protected ApplicationChannel applicationChannel;

    @XmlAttribute
    public Integer getApplicationChannelId(){ return applicationChannel != null ? applicationChannel.getApplicationChannelId() : null;}

    @XmlAttribute
    public String getApplicationChannel(){ return applicationChannel != null ? applicationChannel.getApplicationChannelName() : null;}

    @Column(name = "delivery_date_time")
    @XmlAttribute
    @XmlSchemaType(name = "dateTime")
    protected Date deliveryDateTime;

    @Column(name = "delivery_fact_date_time")
    @XmlAttribute
    @XmlSchemaType(name = "dateTime")
    protected Date deliveryFactDateTime;

    @Column(name = "promo_id")
    @XmlAttribute
    protected Integer promoID;

    @Column(name = "partner_id")
    @XmlAttribute
    protected Integer partnerID;

    @Column(name = "promo_code")
    @XmlAttribute
    protected String promoCode;

    @Embedded
    @XmlElement(name = "ApplicationCalcs")
    protected ApplicationCalcs applicationCalcs;

    public ApplicationCalcs getApplicationCalcs() {
        if (applicationCalcs == null) applicationCalcs = new ApplicationCalcs();
        return applicationCalcs;
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        writeNullableObject(out, managerID);
        writeNullableObject(out, isActual);
        writeNullableObject(out, applicationDateTime);
        writeNullableObject(out, applicationCloseDateTime);
        writeNullableObject(out, deliveryDateTime);
        writeNullableObject(out, deliveryFactDateTime);
        writeNullableObject(out, promoID);
        writeNullableObject(out, partnerID);
        writeNullableObject(out, promoCode);

        getApplicationCalcs().writeExternal(out);
        applicationStatus.writeExternal(out);
        applicationType.writeExternal(out);
        applicationChannel.writeExternal(out);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        managerID = readIntFromObjectInput(in);
        isActual = readBooleanFromObjectInput(in);
        applicationDateTime = readLongToDateFromObjectInput(in);
        applicationCloseDateTime = readLongToDateFromObjectInput(in);
        deliveryDateTime = readLongToDateFromObjectInput(in);
        deliveryFactDateTime = readLongToDateFromObjectInput(in);
        promoID = readIntFromObjectInput(in);
        partnerID = readIntFromObjectInput(in);
        promoCode = readStringFromObjectInput(in);

        getApplicationCalcs().readExternal(in);

        applicationStatus = new ApplicationStatus();
        applicationStatus.readExternal(in);

        applicationType = new ApplicationType();
        applicationType.readExternal(in);

        applicationChannel = new ApplicationChannel();
        applicationChannel.readExternal(in);
    }
}
