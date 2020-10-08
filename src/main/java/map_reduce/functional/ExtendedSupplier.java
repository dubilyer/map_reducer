package map_reduce.functional;

import java.util.function.Consumer;
import java.util.function.Supplier;

@FunctionalInterface
public interface ExtendedSupplier<T> extends Supplier<T> {

    default void andThen(Consumer<T> consumer){
        consumer.accept(get());
    }
}
