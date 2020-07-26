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
        List<String> list = new ArrayList<String>() {{
            add("aa");
            add("bb");
        }};

        assertEquals(2, list.size());
        assertEquals("aa", list.get(0));
        assertEquals("bb", list.get(1));
    }
}
