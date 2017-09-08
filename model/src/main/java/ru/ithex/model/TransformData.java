package ru.ithex.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class TransformData implements Serializable {

	private static final long serialVersionUID = -4852071941436382431L;

	public static XMLGregorianCalendar transformDate(Date date) {
		if (date == null) {
			return null;
		}
		try {
			return DatatypeFactory.newInstance()
					.newXMLGregorianCalendar(new SimpleDateFormat("yyyy-MM-dd").format(date));
		} catch (DatatypeConfigurationException e) {
			return null;
		}
	}

	public static XMLGregorianCalendar transformTimestamp(Date date) {
		if (date == null) {
			return null;
		}
		try {
			return DatatypeFactory.newInstance()
					.newXMLGregorianCalendar(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(date));
		} catch (DatatypeConfigurationException e) {
			return null;
		}
	}

	public static Integer bigDecimalToInteger(BigDecimal value) {
		return value == null ? null : value.intValue();
	}

	public static Boolean bigDecimalToBoolean(BigDecimal value) {
		return value == null ? null : (value.compareTo(new BigDecimal(0)) == 0 ? false : true);
	}

	public static Integer diffDays(Date dfrom, Date dto) {
		return diffDays(transformTimestamp(dfrom), transformTimestamp(dto));
	}

	public static Integer diffDays(XMLGregorianCalendar dfrom, XMLGregorianCalendar dto) {
		return diffDays(dfrom.toGregorianCalendar(), dto.toGregorianCalendar());
	}

	public static Integer diffDays(GregorianCalendar dfrom, GregorianCalendar dto) {
		Integer count = 0;
		if (dfrom.compareTo(dto) != 1)
			for (GregorianCalendar dfrom_muta = (GregorianCalendar) dfrom.clone(); dfrom_muta
					.compareTo(dto) == -1; dfrom_muta.add(Calendar.DATE, 1))
				count++;
		else
			for (GregorianCalendar dfrom_muta = (GregorianCalendar) dto.clone(); dfrom_muta
					.compareTo(dfrom) == -1; dfrom_muta.add(Calendar.DATE, 1))
				count++;
		return count;
	}
}