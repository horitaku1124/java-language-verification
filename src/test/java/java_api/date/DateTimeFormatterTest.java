package java_api.date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import static org.junit.jupiter.api.Assertions.*;

public class DateTimeFormatterTest {
    @BeforeEach
    public void beforeEach() {
    }

    @Nested
    class YmdDefaultParseTest {
        private DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("yyyy/MM/dd");
        @Test
        public void parsableDate() {
            LocalDate date = LocalDate.parse("2020/12/03", formatter);
            assertEquals(2020, date.getYear());
            assertEquals(12, date.getMonthValue());
            assertEquals(3, date.getDayOfMonth());
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "2020/12/01", "0001/01/01", "9999/12/31", "2020/02/29"
        })
        void parsableDateSets(String candidate) {
            LocalDate date = LocalDate.parse(candidate, formatter);
            assertNotNull(date);
        }
        @ParameterizedTest
        @ValueSource(strings = {
                "2020/12/1"
                //, "2020/02/30", "2019/02/29"
        })
        void errorDateSets(String candidate) {
            assertThrows(DateTimeParseException.class, () -> {
                LocalDate date = LocalDate.parse(candidate, formatter);
            });
        }
    }

    @Nested
    class YmdStrictParseTest {

        private DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("uuuu/MM/dd")
                .withResolverStyle(ResolverStyle.STRICT);
        @Test
        public void parsableDate() {
            LocalDate date = LocalDate.parse("2020/12/03", formatter);
            assertEquals(2020, date.getYear());
            assertEquals(12, date.getMonthValue());
            assertEquals(3, date.getDayOfMonth());
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "2020/12/1", "2020/02/30", "2019/02/29"
        })
        void errorDateSets(String candidate) {
            assertThrows(DateTimeParseException.class, () -> {
                LocalDate date = LocalDate.parse(candidate, formatter);
            });
        }
    }
}
