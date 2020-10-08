package map_reduce.engine;

import mocks.FakeLogger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Comparator;
import java.util.Map;

import static java.lang.Integer.parseInt;
import static java.util.Comparator.comparingInt;
import static map_reduce.engine.MapReduce.*;
import static matchers.ContainsRegex.containsRegex;
import static matchers.IsSorted.isSortedAccordingTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MapReduceTest {
    @ParameterizedTest
    @ValueSource(strings = {
            "the man, who sold the world",
            "",
            "\"who is - m. here !"
    })
    void prepareInputTest(String input){
        assertThat(PREPARE_INPUT.apply(input), not(containsRegex(UNWANTED_SYMBOLS)));
    }

    @ParameterizedTest
    @MethodSource("test_data.DataProvider#mapProvider")
    void displayOrderedTest(Map<String, Long> map){
        MapReduce.logger = new FakeLogger();
        DISPLAY_ORDERED.accept(map);
        Comparator<String> comparator = comparingInt(a -> parseInt(a.split(" : ")[1]));
        assertThat(
                ((FakeLogger)logger).getEntries(),
                isSortedAccordingTo(comparator.reversed())
        );
    }

    @Test
    void mapReduceTest(){
        String input = "test test oops red apple red test";
        Map<String, Long> actual = MAP_REDUCE.apply(input);
        assertAll(
                () -> assertEquals(3L, actual.get("test")),
                () -> assertEquals(1L, actual.get("apple")),
                () -> assertEquals(2L, actual.get("red")),
                () -> assertEquals(1L, actual.get("oops"))
        );
        System.out.println(actual);
    }

}
