package ru.ithex.model.abstraction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ru.ithex.model.dictionaries.MessengerType;

import static ru.ithex.model.utils.Serialization.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

@MappedSuperclass
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public abstract class AbstractMessenger implements Externalizable {
    private static final long serialVersionUID = 1l;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "messenger_type_id")
    @XmlTransient
    protected MessengerType messengerType;

    @XmlAttribute
    public Integer getMessengerTypeId(){ return messengerType != null ? messengerType.getMessengerTypeId() : null;}

    @XmlAttribute
    public String getMessengerType(){ return messengerType != null ? messengerType.getMessengerTypeName() : null;}

    @Column(name = "messenger_account")
    @XmlAttribute
    protected String messengerAccount;

    public void writeExternal(ObjectOutput out) throws IOException {
        writeNullableObject(out, messengerAccount);
        messengerType.writeExternal(out);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        messengerAccount = readStringFromObjectInput(in);
        messengerType = new MessengerType();
        messengerType.readExternal(in);
    }
}
