package ru.ithex.model;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@Embeddable
@XmlAccessorType(XmlAccessType.FIELD)
public class EAddress extends AbstractAddress {
	private static final long serialVersionUID = 1l;
}