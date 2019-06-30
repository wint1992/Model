package ru.ithex.model.utils;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class Serialization {

    public static void writeNullableObject(ObjectOutput out, Object obj) throws IOException {
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
                byte[] bytesBD = ((BigDecimal) obj).unscaledValue().toByteArray();
                out.writeInt(bytesBD.length);
                out.write(bytesBD);
                out.writeInt(((BigDecimal) obj).scale());
            }
        }
    }

    public static Integer readIntFromObjectInput(ObjectInput in) throws IOException{
        return in.readBoolean() == true ? in.readInt() : null;
    }

    public static Date readLongToDateFromObjectInput(ObjectInput in) throws IOException{
        return in.readBoolean() == true ? new Date(in.readLong()) : null;
    }

    public static String readStringFromObjectInput(ObjectInput in) throws IOException{
        return in.readBoolean() == true ? in.readUTF() : null;
    }

    public static Byte readByteFromObjectInput(ObjectInput in) throws IOException{
       return  in.readBoolean() == true ? in.readByte() : null;
    }

    public static Boolean readBooleanFromObjectInput(ObjectInput in) throws IOException{
        return  in.readBoolean() == true ? in.readBoolean() : null;
    }

    public static BigDecimal readBigDecimalFromObjectInput(ObjectInput in) throws IOException{
        if (in.readBoolean() == true) {
            int countBD = in.readInt();
            byte[] bytesBD = new byte[countBD];
            in.read(bytesBD, 0, countBD);
            return new BigDecimal(new BigInteger(bytesBD), in.readInt());
        }
        return null;
    }



}
