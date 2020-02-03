package java_api.collection;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class MapTests {
    @Nested
    class MapInterfaceTests {
        @Test
        public void mapLikes() {
            Map<String, String>[] maps = new Map[]{
                    new HashMap<String, String>(),
                    new LinkedHashMap<String, String>(),
                    new WeakHashMap<String, String>(),
                    new Hashtable<String, String>(),
                    new TreeMap<String, String>(),
            };

            for (Map<String, String> map: maps) {
                assertEquals(0, map.size());
                assertTrue(map.isEmpty());

                map.put("a", "1");
                assertEquals(1, map.size());
                assertFalse(map.isEmpty());
                assertTrue(map.containsKey("a"));
                assertFalse(map.containsKey("b"));
                assertEquals("1", map.get("a"));

                map.put("b", "2");
                assertEquals(2, map.size());
                assertTrue(map.containsKey("a"));
                assertTrue(map.containsKey("b"));
                assertEquals("1", map.get("a"));
                assertEquals("2", map.get("b"));

                map.put("b", "3");
                assertEquals(2, map.size());
                assertTrue(map.containsKey("a"));
                assertTrue(map.containsKey("b"));
                assertEquals("3", map.get("b"));

                map.remove("b");
                assertEquals(1, map.size());
                assertTrue(map.containsKey("a"));
                assertFalse(map.containsKey("b"));
                assertNull(map.get("b"));

                map.clear();

                assertEquals(0, map.size());

                System.out.println(map.getClass().getCanonicalName() + " OK");
            }
        }
    }
}
