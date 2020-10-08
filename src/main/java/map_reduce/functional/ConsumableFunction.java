package map_reduce.functional;

import java.util.Objects;
import java.util.function.Consumer;

@FunctionalInterface
public interface ConsumableFunction<T, R> {
    default  Consumer<T> atLast(Consumer<R> consumer){
        return (T t) ->consumer.accept(apply(t));
    }

    R apply(T t);

    default <V> ConsumableFunction<T, V> andThen(ConsumableFunction<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t) -> after.apply(apply(t));
    }
}
