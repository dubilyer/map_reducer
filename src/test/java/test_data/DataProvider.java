package test_data;

import org.junit.jupiter.params.provider.Arguments;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@SuppressWarnings({"unused", "for testing"})
public class DataProvider {
    public static Stream<Arguments> mapProvider() {
        Map<String, Long> m = new HashMap<>();
        m.put("a", 122L);
        m.put("b", 123L);
        m.put("c", 127L);
        m.put("d", 125L);

        Map<String, Long> m2 = new HashMap<>();
        m2.put("a", 12L);
        m2.put("b", 123L);
        m2.put("c", 127000L);
        m2.put("d", 125L);

        return Stream.of(
                Arguments.of(m),
                Arguments.of(m2)
        );
    }
}
