package java_api.date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleDateFormatTests {
    @Test
    public void test() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date date = formatter.parse("2020/01/01");
        assertEquals(1, date.getDate());
        assertEquals(1 - 1, date.getMonth());
        assertEquals(2020 - 1900, date.getYear());
    }
}
