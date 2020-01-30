package java_api.date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleDateFormatTests {
    @Nested
    class YmdTest {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Calendar cal;
        @BeforeEach
        public void beforeEach() {
            formatter.setLenient(false);
            cal = Calendar.getInstance();
        }

        @Test
        public void notmalPattern() throws ParseException {
            Date date = formatter.parse("2020/12/31");
            cal.setTime(date);

            assertEquals(2020, cal.get(Calendar.YEAR));
            assertEquals(12 - 1, cal.get(Calendar.MONTH));
            assertEquals(31, cal.get(Calendar.DAY_OF_MONTH));
        }
        @Test
        public void minMax() throws ParseException {
            Date date = formatter.parse("0001/01/01");
            cal.setTime(date);

            assertEquals(1, cal.get(Calendar.YEAR));
            assertEquals(1 - 1, cal.get(Calendar.MONTH));
            assertEquals(1, cal.get(Calendar.DAY_OF_MONTH));

            date = formatter.parse("9999/12/31");
            cal.setTime(date);

            assertEquals(9999, cal.get(Calendar.YEAR));
            assertEquals(12 - 1, cal.get(Calendar.MONTH));
            assertEquals(31, cal.get(Calendar.DAY_OF_MONTH));
        }

        @ParameterizedTest
        @ValueSource(strings = { "2020/12/3 ",  "2020/12/3a",  "2020/12/3\t"})
        void palindromes(String candidate) throws ParseException {
            Date date = formatter.parse(candidate);
            cal.setTime(date);
            assertEquals(2020, cal.get(Calendar.YEAR));
            assertEquals(12 - 1, cal.get(Calendar.MONTH));
            assertEquals(3, cal.get(Calendar.DAY_OF_MONTH));
        }
    }
}
