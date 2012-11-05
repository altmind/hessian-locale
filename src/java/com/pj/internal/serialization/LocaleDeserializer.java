package com.pj.internal.serialization;


import com.caucho.hessian.io.AbstractDeserializer;
import com.caucho.hessian.io.AbstractHessianInput;

import java.io.IOException;
import java.util.Locale;

public class LocaleDeserializer extends AbstractDeserializer {

    public Class getType() {
        return Locale.class;
    }

    public Object readMap(AbstractHessianInput in)
            throws IOException {
        int ref = in.addRef(null);

        String languageValue = null;
        String countryValue = null;
        String variantValue = null;

        while (!in.isEnd()) {
            String key = in.readString();

            if (key.equals("language"))
                languageValue = in.readString();
            else if (key.equals("country"))
                countryValue = in.readString();
            else if (key.equals("variant"))
                variantValue = in.readString();
            else
                in.readString();
        }

        in.readMapEnd();

        Object value = getObject(languageValue, countryValue, variantValue);

        in.setRef(ref, value);

        return value;
    }

    @Override
    public Object readObject(AbstractHessianInput in, Object[] fields) throws IOException {
        int ref = in.addRef(null);

        String languageValue = null;
        String countryValue = null;
        String variantValue = null;

        for (Object key : fields) {
            if (key.equals("language"))
                languageValue = in.readString();
            else if (key.equals("country"))
                countryValue = in.readString();
            else if (key.equals("variant"))
                variantValue = in.readString();
            else
                in.readObject();

        }

        Object value = getObject(languageValue, countryValue, variantValue);

        in.setRef(ref, value);

        return value;
    }

    private Object getObject(String languageValue, String countryValue, String variantValue) {
        Object value = null;
        if (languageValue != null && countryValue != null && variantValue != null) {
            value = new Locale(languageValue, countryValue, variantValue);
        } else if (languageValue != null && countryValue != null) {
            value = new Locale(languageValue, countryValue);
        } else if (languageValue != null) {
            value = new Locale(languageValue);
        } else {
            value = Locale.getDefault();
        }
        return value;
    }

}