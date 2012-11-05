package com.pj.internal.serialization;

import com.caucho.hessian.io.AbstractSerializerFactory;
import com.caucho.hessian.io.Deserializer;
import com.caucho.hessian.io.HessianProtocolException;
import com.caucho.hessian.io.Serializer;

import java.util.Locale;

public class LocaleSerializerFactory extends AbstractSerializerFactory
{
    public Serializer getSerializer(Class cl) throws HessianProtocolException
    {
        if (Locale.class.isAssignableFrom(cl)) {
            return new LocaleSerializer();
        }
        return null;
    }

    public Deserializer getDeserializer(Class cl) throws HessianProtocolException
    {
        if (Locale.class.isAssignableFrom(cl)) {
            return new LocaleDeserializer();
        }
        return null;
    }
}
