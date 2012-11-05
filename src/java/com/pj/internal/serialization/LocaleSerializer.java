package com.pj.internal.serialization;

import com.caucho.hessian.io.AbstractHessianOutput;
import com.caucho.hessian.io.AbstractSerializer;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Locale;

public class LocaleSerializer extends AbstractSerializer
{
    public void writeObject(Object obj, AbstractHessianOutput out) throws IOException
    {

        if (obj == null)
            out.writeNull();
        else
        {
            Class cl = obj.getClass();

            if (out.addRef(obj))
                return;

            int ref = out.writeObjectBegin(cl.getName());

            Locale loc = (Locale) obj;

            if (ref < -1)
            {
                if (loc.getLanguage()!=null){
                    out.writeString("language");
                    out.writeString(loc.getLanguage());
                }
                if (loc.getCountry()!=null){
                    out.writeString("country");
                    out.writeString(loc.getCountry());
                }
                if (loc.getVariant()!=null){
                    out.writeString("variant");
                    out.writeString(loc.getVariant());
                }

                out.writeMapEnd();
            }
            else
            {
                if (ref == -1)
                {
                    out.writeInt(3);
                    out.writeString("language");
                    out.writeString("country");
                    out.writeString("variant");
                    out.writeObjectBegin(cl.getName());
                }

                out.writeString(loc.getLanguage());
                out.writeString(loc.getCountry());
                out.writeString(loc.getVariant());
            }
        }
    }
}