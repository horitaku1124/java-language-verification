package java_lang;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * see https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/String.html
 */
public class StringClassTests {
    /**
     * see https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Formatter.html#syntax
     */
    @Nested
    class StringFormat {
        @Test
        public void formatString() {
            var str = String.format("%s%s", "A", "B");
            assertEquals("AB", str);
        }
        @Test
        public void formatString2() {
            var str = String.format("%s %<s %s", "A", "B");
            assertEquals("A A B", str);
        }
        @SuppressWarnings("MalformedFormatString")
        @Test
        public void formatString3() {
            String str;
            str = String.format("%4$s %3$s %2$s %1$s %4$s %3$s %2$s %1$s", "a", "b", "c", "d");
            assertEquals("d c b a d c b a", str);
            str = String.format("%s %s %<s %<s", "a", "b", "c", "d");
            assertEquals("a b b b", str);
            str = String.format("%2$s %s %<s %s", "a", "b", "c", "d");
            assertEquals("b a a b", str);
        }
        @Test
        public void formatPadding() {
            var str = String.format("%3s", "A");
            assertEquals("  A", str);
        }
    }
}
