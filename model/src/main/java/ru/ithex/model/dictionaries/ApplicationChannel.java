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
@Table(name = "application_channel", schema = "mdm")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class ApplicationChannel implements Externalizable {
    private static final long serialVersionUID = 1l;

    @Id
    @SequenceGenerator(name = "seq_gen_application_channel", sequenceName = "application_channel_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen_application_channel")
    @Column(name = "application_channel_id")
    @XmlAttribute
    protected Integer applicationChannelId;

    @Column(name = "application_channel_name")
    @XmlAttribute
    protected String applicationChannelName;

    public void writeExternal(ObjectOutput out) throws IOException {
        writeNullableObject(out, applicationChannelId);
        writeNullableObject(out, applicationChannelName);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        applicationChannelId = readIntFromObjectInput(in);
        applicationChannelName = readStringFromObjectInput(in);
    }
}
