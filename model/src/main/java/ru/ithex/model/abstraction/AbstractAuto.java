package ru.ithex.model.abstraction;

import lombok.Data;
import static ru.ithex.model.utils.Serialization.*;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.math.BigDecimal;

@MappedSuperclass
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public abstract class AbstractAuto implements Externalizable {
    private static final long serialVersionUID = 1l;

    @Column(name = "make")
    @XmlAttribute
    protected String make;

    @Column(name = "model")
    @XmlAttribute
    protected String model;

    @Column(name = "year")
    @XmlAttribute
    protected Integer year;

    @Column(name = "is_credit")
    @XmlAttribute
    protected Boolean isCredit;

    @Column(name = "cost")
    @XmlAttribute
    protected BigDecimal cost;

    @Column(name = "car_number")
    @XmlAttribute
    protected String carNumber;

    @Column(name = "auto_reg_serial_number")
    @XmlAttribute
    protected String autoRegSerialNumber;

    @Column(name = "vin")
    @XmlAttribute
    protected String vin;

    public void writeExternal(ObjectOutput out) throws IOException {
        writeNullableObject(out, make);
        writeNullableObject(out, model);
        writeNullableObject(out, year);
        writeNullableObject(out, isCredit);
        writeNullableObject(out, cost);
        writeNullableObject(out, carNumber);
        writeNullableObject(out, autoRegSerialNumber);
        writeNullableObject(out, vin);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        make = readStringFromObjectInput(in);
        model = readStringFromObjectInput(in);
        year = readIntFromObjectInput(in);
        isCredit = readBooleanFromObjectInput(in);
        cost = readBigDecimalFromObjectInput(in);
        carNumber = readStringFromObjectInput(in);
        autoRegSerialNumber = readStringFromObjectInput(in);
        vin = readStringFromObjectInput(in);
    }
}
