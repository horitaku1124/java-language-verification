package java_api.date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeFormatterTest {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    @BeforeEach
    public void beforeEach() {

    }

    @Test
    public void parsableDate() {
        LocalDate date = LocalDate.parse("2020/12/03", formatter);
        assertEquals(2020, date.getYear());
        assertEquals(12, date.getMonthValue());
        assertEquals(3, date.getDayOfMonth());
    }
}
