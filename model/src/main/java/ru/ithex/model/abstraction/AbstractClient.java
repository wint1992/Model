package ru.ithex.model.abstraction;

import lombok.Data;
import ru.ithex.model.ConfigurationParams;
import ru.ithex.model.RequestedInfo;

import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

@MappedSuperclass
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public abstract class AbstractClient  implements Externalizable {
    private static final long serialVersionUID = 1l;

    @Embedded
    @XmlElement(name = "RequestedInfo")
    protected RequestedInfo requestedInfo;

    @Embedded
    @XmlElement(name = "ConfigurationParams")
    protected ConfigurationParams configurationParams;

    public RequestedInfo getRequestedInfo() {
        if (requestedInfo == null) requestedInfo = new RequestedInfo();
        return requestedInfo;
    }

    public ConfigurationParams getConfigurationParams() {
        if (configurationParams == null) configurationParams = new ConfigurationParams();
        return configurationParams;
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        getRequestedInfo().writeExternal(out);
        getConfigurationParams().writeExternal(out);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        getRequestedInfo().readExternal(in);
        getConfigurationParams().readExternal(in);
    }
}
