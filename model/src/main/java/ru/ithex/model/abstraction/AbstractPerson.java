package ru.ithex.model.abstraction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ru.ithex.model.*;
import ru.ithex.model.dictionaries.Citizenship;
import ru.ithex.model.dictionaries.Education;
import ru.ithex.model.dictionaries.MaritalStatus;
import ru.ithex.model.dictionaries.PersonType;

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
public abstract class AbstractPerson implements Externalizable {
    private static final long serialVersionUID = 1l;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_type_id")
    @XmlTransient
    protected PersonType personType;

    @XmlAttribute
    public Integer getPersonTypeId(){ return personType != null ? personType.getPersonTypeId() : null;}

    @XmlAttribute
    public String getPersonType(){ return personType != null ? personType.getPersonTypeName() : null;}

    @Column(name = "first_name")
    @XmlAttribute
    protected String firstName;

    @Column(name = "last_name")
    @XmlAttribute
    protected String lastName;

    @Column(name = "patronymic_name")
    @XmlAttribute
    protected String patronymicName;

    @Column(name = "is_no_patronymic")
    @XmlAttribute
    protected Boolean isNoPatronymic;

    @Column(name = "previous_last_name")
    @XmlAttribute
    protected String previousLastName;

    @Column(name = "birth_place")
    @XmlAttribute
    protected String birthPlace;

    @Column(name = "birth_date")
    @XmlAttribute
    @XmlSchemaType(name = "date")
    protected Date birthDate;

    @Column(name = "death_date")
    @XmlAttribute
    @XmlSchemaType(name = "date")
    protected Date deathDate;

    @Column(name = "age")
    @XmlAttribute
    protected Integer age;

    @Column(name = "sex")
    @XmlAttribute
    protected Integer sex;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "citizenship_id")
    @XmlTransient
    protected Citizenship citizenship;

    @XmlAttribute
    public Integer getCitizenshipId(){ return citizenship != null ? citizenship.getCitizenshipId() : null;}

    @XmlAttribute
    public String getCitizenship(){ return citizenship != null ? citizenship.getCitizenshipName() : null;}

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "marital_status_id")
    @XmlTransient
    protected MaritalStatus maritalStatus;

    @XmlAttribute
    public Integer getMaritalStatusId(){ return maritalStatus != null ? maritalStatus.getMaritalStatusId() : null;}

    @XmlAttribute
    public String getMaritalStatus(){ return maritalStatus != null ? maritalStatus.getMaritalStatusName() : null;}

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "education_id")
    @XmlTransient
    protected Education education;

    @XmlAttribute
    public Integer getEducationId(){ return education != null ? education.getEducationId() : null;}

    @XmlAttribute
    public String getEducation(){ return education != null ? education.getEducationName() : null;}

    @Column(name = "email")
    @XmlAttribute
    protected String email;

    @Column(name = "work_email")
    @XmlAttribute
    protected String workEmail;

    @AttributeOverrides({ //
            @AttributeOverride(name = "country", column = @Column(name = "ra_country")), //
            @AttributeOverride(name = "regionType", column = @Column(name = "ra_region_type")), //
            @AttributeOverride(name = "regionName", column = @Column(name = "ra_region_name")), //
            @AttributeOverride(name = "districtType", column = @Column(name = "ra_district_type")), //
            @AttributeOverride(name = "districtName", column = @Column(name = "ra_district_name")), //
            @AttributeOverride(name = "microdistrict", column = @Column(name = "ra_microdistrict")), //
            @AttributeOverride(name = "locationType", column = @Column(name = "ra_location_type")), //
            @AttributeOverride(name = "cityType", column = @Column(name = "ra_city_type")), //
            @AttributeOverride(name = "city", column = @Column(name = "ra_city")), //
            @AttributeOverride(name = "streetType", column = @Column(name = "ra_street_type")), //
            @AttributeOverride(name = "street", column = @Column(name = "ra_street")), //
            @AttributeOverride(name = "house", column = @Column(name = "ra_house")), //
            @AttributeOverride(name = "construction", column = @Column(name = "ra_construction")), //
            @AttributeOverride(name = "building", column = @Column(name = "ra_building")), //
            @AttributeOverride(name = "apartment", column = @Column(name = "ra_apartment")), //
            @AttributeOverride(name = "postIndex", column = @Column(name = "ra_post_index")), //
            @AttributeOverride(name = "registrationDate", column = @Column(name = "ra_registration_date")), //
            @AttributeOverride(name = "startLivingDate", column = @Column(name = "ra_start_living_date")), //
            @AttributeOverride(name = "gps", column = @Column(name = "ra_gps")), //
            @AttributeOverride(name = "fullAddress", column = @Column(name = "ra_full_address")), //
            @AttributeOverride(name = "codeKLADR", column = @Column(name = "ra_code_kladr")), //
            @AttributeOverride(name = "timeZone", column = @Column(name = "ra_time_zone")), //
            @AttributeOverride(name = "codeKLADE", column = @Column(name = "ra_code_klade")), //
            @AttributeOverride(name = "autonomyType", column = @Column(name = "ra_autonomy_type")), //
            @AttributeOverride(name = "autonomyName", column = @Column(name = "ra_autonomy_name")), //
            @AttributeOverride(name = "location", column = @Column(name = "ra_location")), //
            @AttributeOverride(name = "intracityDistrict", column = @Column(name = "ra_intracity_district")), //
            @AttributeOverride(name = "ownership", column = @Column(name = "ra_ownership")) //
    })
    @Embedded
    @XmlElement(name = "RegAddress")
    protected EAddress regAddress;

    @AttributeOverrides({ //
            @AttributeOverride(name = "country", column = @Column(name = "fa_country")), //
            @AttributeOverride(name = "regionType", column = @Column(name = "fa_region_type")), //
            @AttributeOverride(name = "regionName", column = @Column(name = "fa_region_name")), //
            @AttributeOverride(name = "districtType", column = @Column(name = "fa_district_type")), //
            @AttributeOverride(name = "districtName", column = @Column(name = "fa_district_name")), //
            @AttributeOverride(name = "microdistrict", column = @Column(name = "fa_microdistrict")), //
            @AttributeOverride(name = "locationType", column = @Column(name = "fa_location_type")), //
            @AttributeOverride(name = "cityType", column = @Column(name = "fa_city_type")), //
            @AttributeOverride(name = "city", column = @Column(name = "fa_city")), //
            @AttributeOverride(name = "streetType", column = @Column(name = "fa_street_type")), //
            @AttributeOverride(name = "street", column = @Column(name = "fa_street")), //
            @AttributeOverride(name = "house", column = @Column(name = "fa_house")), //
            @AttributeOverride(name = "construction", column = @Column(name = "fa_construction")), //
            @AttributeOverride(name = "building", column = @Column(name = "fa_building")), //
            @AttributeOverride(name = "apartment", column = @Column(name = "fa_apartment")), //
            @AttributeOverride(name = "postIndex", column = @Column(name = "fa_post_index")), //
            @AttributeOverride(name = "registrationDate", column = @Column(name = "fa_registration_date")), //
            @AttributeOverride(name = "startLivingDate", column = @Column(name = "fa_start_living_date")), //
            @AttributeOverride(name = "gps", column = @Column(name = "fa_gps")), //
            @AttributeOverride(name = "fullAddress", column = @Column(name = "fa_full_address")), //
            @AttributeOverride(name = "codeKLADR", column = @Column(name = "fa_code_kladr")), //
            @AttributeOverride(name = "timeZone", column = @Column(name = "fa_time_zone")), //
            @AttributeOverride(name = "codeKLADE", column = @Column(name = "fa_code_klade")), //
            @AttributeOverride(name = "autonomyType", column = @Column(name = "fa_autonomy_type")), //
            @AttributeOverride(name = "autonomyName", column = @Column(name = "fa_autonomy_name")), //
            @AttributeOverride(name = "location", column = @Column(name = "fa_location")), //
            @AttributeOverride(name = "intracityDistrict", column = @Column(name = "fa_intracity_district")), //
            @AttributeOverride(name = "ownership", column = @Column(name = "fa_ownership")) //
    })
    @Embedded
    @XmlElement(name = "FactAddress")
    protected EAddress factAddress;

    @Embedded
    @XmlElement(name = "PersonCalcs")
    protected PersonCalcs personCalcs;

    public EAddress getRegAddress() {
        if (regAddress == null) regAddress = new EAddress();
        return regAddress;
    }

    public EAddress getFactAddress() {
        if (factAddress == null) factAddress = new EAddress();
        return factAddress;
    }

    public PersonCalcs getPersonCalcs() {
        if (personCalcs == null) personCalcs = new PersonCalcs();
        return personCalcs;
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        writeNullableObject(out, firstName);
        writeNullableObject(out, lastName);
        writeNullableObject(out, patronymicName);
        writeNullableObject(out, isNoPatronymic);
        writeNullableObject(out, previousLastName);
        writeNullableObject(out, birthPlace);
        writeNullableObject(out, birthDate);
        writeNullableObject(out, deathDate);
        writeNullableObject(out, age);
        writeNullableObject(out, sex);
        writeNullableObject(out, email);
        writeNullableObject(out, workEmail);
        getRegAddress().writeExternal(out);
        getFactAddress().writeExternal(out);
        getPersonCalcs().writeExternal(out);
        personType.writeExternal(out);
        citizenship.writeExternal(out);
        maritalStatus.writeExternal(out);
        education.writeExternal(out);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        firstName = readStringFromObjectInput(in);
        lastName = readStringFromObjectInput(in);
        patronymicName = readStringFromObjectInput(in);
        isNoPatronymic = readBooleanFromObjectInput(in);
        previousLastName = readStringFromObjectInput(in);
        birthPlace = readStringFromObjectInput(in);
        birthDate = readLongToDateFromObjectInput(in);
        deathDate = readLongToDateFromObjectInput(in);
        age = readIntFromObjectInput(in);
        sex = readIntFromObjectInput(in);
        email = readStringFromObjectInput(in);
        workEmail = readStringFromObjectInput(in);
        getRegAddress().readExternal(in);
        getFactAddress().readExternal(in);
        getPersonCalcs().readExternal(in);

        personType = new PersonType();
        personType.readExternal(in);

        citizenship = new Citizenship();
        citizenship.readExternal(in);

        maritalStatus = new MaritalStatus();
        maritalStatus.readExternal(in);

        education = new Education();
        education.readExternal(in);
    }
}
