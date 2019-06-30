package ru.ithex.model.abstraction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ru.ithex.model.dictionaries.DocumentType;

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
public abstract class AbstractDocument implements Externalizable {
    private static final long serialVersionUID = 1l;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "document_type_id")
    @XmlTransient
    protected DocumentType documentType;

    @XmlAttribute
    public Integer getDocumentTypeId(){ return documentType != null ? documentType.getDocumentTypeId() : null;}

    @XmlAttribute
    public String getDocumentType(){ return documentType != null ? documentType.getDocumentTypeName() : null;}

    @Column(name = "issue_date")
    @XmlAttribute
    @XmlSchemaType(name = "date")
    protected Date issueDate;

    @Column(name = "serial")
    @XmlAttribute
    protected String serial;

    @Column(name = "number")
    @XmlAttribute
    protected String number;

    @Column(name = "sub_division_code")
    @XmlAttribute
    protected String subDivisionCode;

    @Column(name = "issuer")
    @XmlAttribute
    protected String issuer;

    @Column(name = "is_original")
    @XmlAttribute
    protected Boolean isOriginal;

    @Column(name = "amount_pages")
    @XmlAttribute
    protected Integer amountPages;

    @Column(name = "link")
    @XmlAttribute
    protected String link;

    @Column(name = "version")
    @XmlAttribute
    protected String version;

    public void writeExternal(ObjectOutput out) throws IOException {
        writeNullableObject(out, issueDate);
        writeNullableObject(out, serial);
        writeNullableObject(out, number);
        writeNullableObject(out, subDivisionCode);
        writeNullableObject(out, issuer);
        writeNullableObject(out, isOriginal);
        writeNullableObject(out, amountPages);
        writeNullableObject(out, link);
        writeNullableObject(out, version);
        documentType.writeExternal(out);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        issueDate = readLongToDateFromObjectInput(in);
        serial = readStringFromObjectInput(in);
        number = readStringFromObjectInput(in);
        subDivisionCode = readStringFromObjectInput(in);
        issuer = readStringFromObjectInput(in);
        isOriginal = readBooleanFromObjectInput(in);
        amountPages = readIntFromObjectInput(in);
        link = readStringFromObjectInput(in);
        version = readStringFromObjectInput(in);

        documentType = new DocumentType();
        documentType.readExternal(in);
    }
}
