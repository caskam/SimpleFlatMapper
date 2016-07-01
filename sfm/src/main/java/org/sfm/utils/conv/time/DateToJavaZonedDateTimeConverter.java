package org.sfm.utils.conv.time;

import org.sfm.utils.conv.Converter;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class DateToJavaZonedDateTimeConverter implements Converter<Date, ZonedDateTime> {
    private final ZoneId zoneId;

    public DateToJavaZonedDateTimeConverter(ZoneId zoneId) {
        this.zoneId = zoneId;
    }

    @Override
    public ZonedDateTime convert(Date in) throws Exception {
        if (in == null) return null;
        return in.toInstant().atZone(zoneId);
    }
}
