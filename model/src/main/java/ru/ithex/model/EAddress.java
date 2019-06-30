package ru.ithex.model;

import lombok.Data;
import ru.ithex.model.abstraction.AbstractAddress;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@Embeddable
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class EAddress extends AbstractAddress {
}