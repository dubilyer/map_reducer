package map_reduce.engine;

import map_reduce.functional.ConsumableFunction;
import map_reduce.functional.UnaryConsumableFunction;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Consumer;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingByConcurrent;

public class MapReduce {
    static final String UNWANTED_SYMBOLS = "[.,0-9\"“”’_]";
    private static final String SEPARATOR = System.getProperty("line.separator");

    static Logger logger = Logger.getLogger(MapReduce.class.getName());

    private MapReduce() {
    }

    static final UnaryConsumableFunction<String> PREPARE_INPUT = s -> s
            .replaceAll(UNWANTED_SYMBOLS, "")
            .replace(SEPARATOR, " ")
            .toLowerCase();

    private static final Collector<String, ?, ConcurrentMap<String, Long>> GROUP_FACTOR = groupingByConcurrent(
            identity(),
            counting()
    );

    static final ConsumableFunction<String, Map<String, Long>> MAP_REDUCE = input -> Stream
            .of(input.split(" "))
            .parallel()
            .collect(GROUP_FACTOR);

    static final Consumer<Map<String, Long>> DISPLAY_ORDERED = map ->
            map
                    .entrySet()
                    .stream()
                    .filter(e -> !e.getKey().isBlank())
                    .filter(e -> e.getValue() > 10)
                    .sorted(Entry.<String, Long>comparingByValue().reversed())
                    .forEach(s -> logger.info(s.getKey() + " : " + s.getValue())
                    );

    public static final Consumer<String> MAP_REDUCE_AND_DISPLAY = s ->
            PREPARE_INPUT
                    .andThen(MAP_REDUCE)
                    .atLast(DISPLAY_ORDERED)
                    .accept(s);
}