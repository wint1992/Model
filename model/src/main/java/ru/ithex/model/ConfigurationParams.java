package ru.ithex.model;

import lombok.Data;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Embeddable
@Data
@XmlRootElement(name = "ConfigurationParams")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConfigurationParams implements Externalizable {
	private static final long serialVersionUID = 1l;

	// @Column(name = "cp_some_var")
	// @XmlAttribute
	// protected BigDecimal someVar;

	public void writeExternal(ObjectOutput out) throws IOException {
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
	}
}
