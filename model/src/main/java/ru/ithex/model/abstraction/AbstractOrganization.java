package ru.ithex.model.abstraction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ru.ithex.model.dictionaries.OrganizationStatus;

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
public abstract class AbstractOrganization implements Externalizable {
    private static final long serialVersionUID = 1l;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "organization_status_id")
    @XmlTransient
    protected OrganizationStatus organizationStatus;

    @XmlAttribute
    public Integer getOrganizationStatusID(){ return organizationStatus != null ? organizationStatus.getOrganizationStatusID() : null;}

    @XmlAttribute
    public String getOrganizationStatus(){ return organizationStatus != null ? organizationStatus.getOrganizationStatusName() : null;}

    @Column(name = "registration_date")
    @XmlAttribute
    @XmlSchemaType(name = "date")
    protected Date registrationDate;

    @Column(name = "close_date")
    @XmlAttribute
    @XmlSchemaType(name = "date")
    protected Date closeDate;

    @Column(name = "inn")
    @XmlAttribute
    protected String inn;

    @Column(name = "ogrn")
    @XmlAttribute
    protected String ogrn;

    @Column(name = "okopf")
    @XmlAttribute
    protected String okopf;

    @Column(name = "okved")
    @XmlAttribute
    protected String okved;

    @Column(name = "full_name")
    @XmlAttribute
    protected String fullName;

    @Column(name = "okpo")
    @XmlAttribute
    protected String okpo;

    @Column(name = "employee_number")
    @XmlAttribute
    protected Integer employeeNumber;

    @Column(name = "web_site")
    @XmlAttribute
    protected String webSite;

    @Column(name = "is_employment")
    @XmlAttribute
    protected Boolean isEmployment;

    public void writeExternal(ObjectOutput out) throws IOException {
        writeNullableObject(out, registrationDate);
        writeNullableObject(out, closeDate);
        writeNullableObject(out, inn);
        writeNullableObject(out, ogrn);
        writeNullableObject(out, okopf);
        writeNullableObject(out, okved);
        writeNullableObject(out, fullName);
        writeNullableObject(out, okpo);
        writeNullableObject(out, employeeNumber);
        writeNullableObject(out, webSite);
        writeNullableObject(out, isEmployment);
        organizationStatus.writeExternal(out);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        registrationDate = readLongToDateFromObjectInput(in);
        closeDate = readLongToDateFromObjectInput(in);
        inn = readStringFromObjectInput(in);
        ogrn = readStringFromObjectInput(in);
        okopf = readStringFromObjectInput(in);
        okved = readStringFromObjectInput(in);
        fullName = readStringFromObjectInput(in);
        okpo = readStringFromObjectInput(in);
        employeeNumber = readIntFromObjectInput(in);
        webSite = readStringFromObjectInput(in);
        isEmployment = readBooleanFromObjectInput(in);
        organizationStatus = new OrganizationStatus();
        organizationStatus.readExternal(in);
    }
}
