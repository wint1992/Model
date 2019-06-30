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
@Table(name = "product_specification_view", schema = "mdm")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductSpecification implements Externalizable {
    private static final long serialVersionUID = 1l;

    @Id
    @Column(name = "product_specification_id")
    @XmlAttribute
    protected Integer productSpecificationID;

    @XmlAttribute
    protected String type;

    @Column(name = "type_code")
    @XmlAttribute
    protected String typeCode;

    @XmlAttribute
    protected String g1;

    @Column(name = "g1_code")
    @XmlAttribute
    protected String g1Code;

    @XmlAttribute
    protected String g2;

    @Column(name = "g2_code")
    @XmlAttribute
    protected String g2Code;

    @XmlAttribute
    protected String g3;

    @Column(name = "g3_code")
    @XmlAttribute
    protected String g3Code;

    @XmlAttribute
    protected String g4;

    @Column(name = "g4_code")
    @XmlAttribute
    protected String g4Code;

    public void writeExternal(ObjectOutput out) throws IOException {
        writeNullableObject(out, productSpecificationID);
        writeNullableObject(out, type);
        writeNullableObject(out, typeCode);
        writeNullableObject(out, g1);
        writeNullableObject(out, g1Code);
        writeNullableObject(out, g2);
        writeNullableObject(out, g2Code);
        writeNullableObject(out, g3);
        writeNullableObject(out, g3Code);
        writeNullableObject(out, g4);
        writeNullableObject(out, g4Code);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        productSpecificationID = readIntFromObjectInput(in);
        type = readStringFromObjectInput(in);
        typeCode = readStringFromObjectInput(in);
        g1 = readStringFromObjectInput(in);
        g1Code = readStringFromObjectInput(in);
        g2 = readStringFromObjectInput(in);
        g2Code = readStringFromObjectInput(in);
        g3 = readStringFromObjectInput(in);
        g3Code = readStringFromObjectInput(in);
        g4 = readStringFromObjectInput(in);
        g4Code = readStringFromObjectInput(in);
    }
}
