package java_lang;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TypeInference {
    @Test
    public void arrayUseAnonymousGenerics() {
        List<String> list = new ArrayList<>() {{
            add("aa");
            add("bb");
        }};

        assertEquals(2, list.size());
        assertEquals("aa", list.get(0));
        assertEquals("bb", list.get(1));
    }

    @Test
    public void listOfInitialize() {
        var set = List.of("A", "B", "C");
        assertEquals(3, set.size());
    }
    @Test
    public void setOfInitialize() {
        var set = Set.of("A", "B", "C");
        assertEquals(3, set.size());
    }
    @Test
    public void mapOfInitialize() {
        var set = Map.of(
                "a", "AA",
                "b", "BB",
                "c", "CC"
        );
        assertEquals(3, set.size());
        assertEquals("AA", set.get("a"));
        assertEquals("BB", set.get("b"));
        assertEquals("CC", set.get("c"));
    }
}
