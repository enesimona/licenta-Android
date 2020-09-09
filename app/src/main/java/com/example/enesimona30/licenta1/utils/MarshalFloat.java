package com.example.enesimona30.licenta1.utils;

import org.ksoap2.serialization.Marshal;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;

/**
 * Created by enesimona30 on 08/05/17.
 */

public class MarshalFloat implements Marshal {

    public Object readInstance(XmlPullParser parser, String namespace, String name, PropertyInfo propertyInfo)
            throws IOException, XmlPullParserException {
        String stringValue = parser.nextText();
        Object result;
        if (name.equals("float")) {
            result = new Float(stringValue);
        } else if (name.equals("double")) {
            result = new Double(stringValue);
        } else if (name.equals("decimal")) {
            result = new java.math.BigDecimal(stringValue);
        } else {
            throw new RuntimeException("float, double, or decimal expected");
        }
        return result;
    }

    public void writeInstance(XmlSerializer writer, Object instance) throws IOException {
        writer.text(instance.toString());
    }

    public void register(SoapSerializationEnvelope cm) {
        cm.addMapping(cm.xsd, "float", Float.class, this);
        cm.addMapping(cm.xsd, "double", Double.class, this);
        cm.addMapping(cm.xsd, "decimal", java.math.BigDecimal.class, this);
    }
}
