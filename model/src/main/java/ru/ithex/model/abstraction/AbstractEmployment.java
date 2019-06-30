package ru.ithex.model.abstraction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ru.ithex.model.dictionaries.EmploymentPosition;

import static ru.ithex.model.utils.Serialization.*;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@MappedSuperclass
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public abstract class AbstractEmployment implements Externalizable {
    private static final long serialVersionUID = 1l;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employment_position_id")
    @XmlTransient
    protected EmploymentPosition employmentPosition;

    @XmlAttribute
    public Integer getEmploymentPositionId(){ return employmentPosition != null ? employmentPosition.getEmploymentPositionId() : null;}

    @XmlAttribute
    public String getEmploymentPosition(){ return employmentPosition != null ? employmentPosition.getEmploymentPositionName() : null;}

    @Column(name = "income_amount")
    @XmlAttribute
    protected BigDecimal incomeAmount;

    @Column(name = "start_working_date")
    @XmlAttribute
    @XmlSchemaType(name = "date")
    protected Date startWorkingDate;

    @Column(name = "end_working_date")
    @XmlAttribute
    @XmlSchemaType(name = "date")
    protected Date endWorkingDate;

    public void writeExternal(ObjectOutput out) throws IOException {
        writeNullableObject(out, incomeAmount);
        writeNullableObject(out, startWorkingDate);
        writeNullableObject(out, endWorkingDate);
        employmentPosition.writeExternal(out);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        incomeAmount = readBigDecimalFromObjectInput(in);
        startWorkingDate = readLongToDateFromObjectInput(in);
        endWorkingDate = readLongToDateFromObjectInput(in);
        employmentPosition = new EmploymentPosition();
        employmentPosition.readExternal(in);
    }
}
