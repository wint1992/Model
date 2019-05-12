package ru.ithex.model;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.ObjectOutput;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class TransformData extends PropertyEditorSupport {

	public static Date stringToDate(String dateWithFormat) {
		// 1-й аргумент в text - дата/время, 2-ой - формат; по умолчанию формат
		// yyyy-MM-dd,
		// для указания времени использовать пример: "1992-08-24T00:00:00,
		// yyyy-MM-dd'T'HH:mm:ss "
		String[] split = dateWithFormat.split(",");
		Date resultDate = null;
		try {
			if (split.length == 2)
				resultDate = new SimpleDateFormat(split[1]).parse(split[0]);
			else
				resultDate = new SimpleDateFormat("yyyy-MM-dd").parse(split[0]);
		} catch (Exception e) {
		}

		return resultDate;
	}

	private static String dateToString(Date date, String returningFormat) {
		return new SimpleDateFormat(returningFormat).format(date);
	}

	public static String dateToString(Date date) {
		return dateToString(date, "yyyy-MM-dd");
	}

	public static String timestampToString(Date date) {
		return dateToString(date, "yyyy-MM-dd'T'HH:mm:ss");
	}

	public static XMLGregorianCalendar transformDate(Date date) {
		XMLGregorianCalendar result = null;
		try {
			result = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateToString(date));
		} catch (DatatypeConfigurationException e) {
		}
		return result;
	}

	public static XMLGregorianCalendar transformTimestamp(Date date) {
		XMLGregorianCalendar result = null;
		try {
			result = DatatypeFactory.newInstance().newXMLGregorianCalendar(timestampToString(date));
		} catch (DatatypeConfigurationException e) {
		}
		return result;
	}

	public static Integer bigDecimalToInteger(BigDecimal value) {
		return value == null ? null : value.intValue();
	}

	public static Boolean bigDecimalToBoolean(BigDecimal value) {
		return value == null ? null : (value.intValue() == 0 ? false : true);
	}

	public static Integer diffDays(Date dfrom, Date dto) {
		return dfrom == null | dto == null ? null : (int) TimeUnit.DAYS.convert(Math.abs(dto.getTime() - dfrom.getTime()), TimeUnit.MILLISECONDS);
	}

	public static Integer diffDays(XMLGregorianCalendar dfrom, XMLGregorianCalendar dto) {
		return dfrom == null | dto == null ? null : diffDays(dfrom.toGregorianCalendar().getTime(), dto.toGregorianCalendar().getTime());
	}

	public static Integer diffDays(String d1WithFormat, String d2WithFormat) {
		return d1WithFormat == null | d2WithFormat == null ? null : diffDays(stringToDate(d1WithFormat), stringToDate(d2WithFormat));
	}

	public static Integer diffDays(GregorianCalendar dfrom, GregorianCalendar dto) {
		return dfrom == null | dto == null ? null : diffDays(dfrom.getTime(), dto.getTime());
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		setValue(stringToDate(text));
	}

	int countBD;
	byte[] bytesBD;

	public void writeNullableObject(ObjectOutput out, Object obj) throws IOException {
		if (obj == null) {
			out.writeBoolean(false);
		} else {
			out.writeBoolean(true);
			if (obj instanceof String)
				out.writeUTF((String) obj);
			else if (obj instanceof Integer)
				out.writeInt((Integer) obj);
			else if (obj instanceof Byte)
				out.writeByte((Byte) obj);
			else if (obj instanceof Long)
				out.writeLong((Long) obj);
			else if (obj instanceof Date)
				out.writeLong(((Date) obj).getTime());
			else if (obj instanceof Double)
				out.writeDouble((Double) obj);
			else if (obj instanceof Boolean)
				out.writeBoolean((Boolean) obj);
			else if (obj instanceof BigDecimal) {
				bytesBD = ((BigDecimal) obj).unscaledValue().toByteArray();
				out.writeInt(bytesBD.length);
				out.write(bytesBD);
				out.writeInt(((BigDecimal) obj).scale());
			}
		}
	}
}