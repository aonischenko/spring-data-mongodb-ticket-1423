package com.dt.spring;

import com.dt.enums.Baz;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;

import java.util.Arrays;
import java.util.List;

public class CustomConverters {

    public static List<Converter<?, ?>> getConvertersToRegister() {
        Converter<?, ?>[] converters = new Converter[]{
                BazToStringConverter.INSTANCE,
                StringToBazConverter.INSTANCE,
        };
        return Arrays.asList(converters);
    }

    @WritingConverter
    public enum BazToStringConverter implements Converter<Baz, String> {
        INSTANCE;

        @Override
        public String convert(Baz value) {
            return value != null ? value.name().toLowerCase() : null;
        }
    }

    @ReadingConverter
    public enum StringToBazConverter implements Converter<String, Baz> {
        INSTANCE;

        @Override
        public Baz convert(String value) {
            if (value != null && !value.isEmpty()) {

                for (Baz baz : Baz.values()) {
                    if (value.toLowerCase().equals(baz.name().toLowerCase())) {
                        return baz;
                    }
                }
            }
            return null;
        }
    }

}
