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
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SimpleDateFormatTests {
    @Nested
    class YmdParseTest {
        SimpleDateFormat formatterLenient = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Calendar cal;
        @BeforeEach
        public void beforeEach() {
            formatterLenient.setLenient(true);
            formatter.setLenient(false);
            cal = Calendar.getInstance();
        }

        @Test
        public void normalPattern() throws ParseException {
            Date date = formatterLenient.parse("2020/12/31");
            cal.setTime(date);

            assertEquals(2020, cal.get(Calendar.YEAR));
            assertEquals(Calendar.DECEMBER, cal.get(Calendar.MONTH));
            assertEquals(31, cal.get(Calendar.DAY_OF_MONTH));
        }
        @Test
        public void minMax() throws ParseException {
            Date date = formatterLenient.parse("0001/01/01");
            cal.setTime(date);

            assertEquals(1, cal.get(Calendar.YEAR));
            assertEquals(Calendar.JANUARY, cal.get(Calendar.MONTH));
            assertEquals(1, cal.get(Calendar.DAY_OF_MONTH));

            date = formatterLenient.parse("9999/12/31");
            cal.setTime(date);

            assertEquals(9999, cal.get(Calendar.YEAR));
            assertEquals(12 - 1, cal.get(Calendar.MONTH));
            assertEquals(31, cal.get(Calendar.DAY_OF_MONTH));
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "2020/12/ 3", "2020/12/\t3", "2020/12/3 ", "2020/12/3a",  "2020/12/3\t",
                "2020/12/3abcde", "2020/12/3 1",
                "2020/11/33"
        })
        void lunientAccepts(String candidate) throws ParseException {
            Date date = formatterLenient.parse(candidate);
            cal.setTime(date);
            assertEquals(2020, cal.get(Calendar.YEAR));
            assertEquals(Calendar.DECEMBER, cal.get(Calendar.MONTH));
            assertEquals(3, cal.get(Calendar.DAY_OF_MONTH));

            if (!"2020/11/33".equals(candidate)) {
                date = formatter.parse(candidate);
                cal.setTime(date);
                assertEquals(2020, cal.get(Calendar.YEAR));
                assertEquals(Calendar.DECEMBER, cal.get(Calendar.MONTH));
                assertEquals(3, cal.get(Calendar.DAY_OF_MONTH));
            }
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "2020/12/ 3", "2020/12/\t3", "2020/12/3 ", "2020/12/3a",  "2020/12/3\t",
                "2020/12/3abcde", "2020/12/3 1"
        })
        void nonLunientAccepts(String candidate) throws ParseException {
            Date date = formatter.parse(candidate);
            cal.setTime(date);
            assertEquals(2020, cal.get(Calendar.YEAR));
            assertEquals(Calendar.DECEMBER, cal.get(Calendar.MONTH));
            assertEquals(3, cal.get(Calendar.DAY_OF_MONTH));
        }

        @ParameterizedTest
        @ValueSource(strings = { "2020/12/a3", "2020/12/32"})
        void errorCase(String candidate)  {
            assertThrows(ParseException.class, () -> {
                Date date = formatter.parse(candidate);
                cal.setTime(date);
                assertEquals(2020, cal.get(Calendar.YEAR));
                assertEquals(Calendar.DECEMBER, cal.get(Calendar.MONTH));
                assertEquals(3, cal.get(Calendar.DAY_OF_MONTH));
            });
        }
    }

    @Nested
    class YmdHisParseTest {
        SimpleDateFormat formatterLenient = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal;
        @BeforeEach
        public void beforeEach() {
            formatterLenient.setLenient(true);
            formatter.setLenient(false);
            cal = Calendar.getInstance();
        }

        @Test
        public void normalPattern() throws ParseException {
            Date date = formatter.parse("2020/12/31 12:34:56");
            cal.setTime(date);

            assertEquals(2020, cal.get(Calendar.YEAR));
            assertEquals(Calendar.DECEMBER, cal.get(Calendar.MONTH));
            assertEquals(31, cal.get(Calendar.DAY_OF_MONTH));
            assertEquals(12, cal.get(Calendar.HOUR_OF_DAY));
            assertEquals(34, cal.get(Calendar.MINUTE));
            assertEquals(56, cal.get(Calendar.SECOND));
        }
        @Test
        public void normalPattern2() throws ParseException {
            Date date = formatterLenient.parse("2020/12/31 12:34:56");
            cal.setTime(date);

            assertEquals(2020, cal.get(Calendar.YEAR));
            assertEquals(Calendar.DECEMBER, cal.get(Calendar.MONTH));
            assertEquals(31, cal.get(Calendar.DAY_OF_MONTH));
            assertEquals(12, cal.get(Calendar.HOUR_OF_DAY));
            assertEquals(34, cal.get(Calendar.MINUTE));
            assertEquals(56, cal.get(Calendar.SECOND));
        }
        @ParameterizedTest
        @ValueSource(strings = {
                "2020/12/31 23:59:43 ",
                "2020/12/31 23:59:43a",
                "2020/12/31 23:59:43\t",
                "2020/12/31  23:59:43",
//                "2020/12/31 23:59:431",
        })
        void lunientAccepts(String candidate) throws ParseException {
            Date date = formatterLenient.parse(candidate);
            cal.setTime(date);
            assertEquals(2020, cal.get(Calendar.YEAR));
            assertEquals(Calendar.DECEMBER, cal.get(Calendar.MONTH));
            assertEquals(31, cal.get(Calendar.DAY_OF_MONTH));
            assertEquals(23, cal.get(Calendar.HOUR_OF_DAY));
            assertEquals(59, cal.get(Calendar.MINUTE));
            assertEquals(43, cal.get(Calendar.SECOND));

            date = formatter.parse(candidate);
            cal.setTime(date);
            assertEquals(2020, cal.get(Calendar.YEAR));
            assertEquals(Calendar.DECEMBER, cal.get(Calendar.MONTH));
            assertEquals(31, cal.get(Calendar.DAY_OF_MONTH));
            assertEquals(23, cal.get(Calendar.HOUR_OF_DAY));
            assertEquals(59, cal.get(Calendar.MINUTE));
            assertEquals(43, cal.get(Calendar.SECOND));
        }
    }
}
