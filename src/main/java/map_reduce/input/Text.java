package map_reduce.input;

import map_reduce.functional.ExtendedSupplier;

import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.Scanner;

public final class Text {
    private Text() {
    }

    public static final ExtendedSupplier<String> GET_TEXT = () ->
            new Scanner(
                    Optional.ofNullable(
                            Thread.currentThread().getContextClassLoader().getResourceAsStream("text")
                    ).orElseThrow()
                    , StandardCharsets.UTF_8
            ).useDelimiter("\\A")
                    .next();
}
