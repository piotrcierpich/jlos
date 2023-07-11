package pl.kololos.api.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class NormalizeLinkTest {
    @ParameterizedTest
    @MethodSource("normalizeWhiteSpace")
    public void normalizeWhiteSpace(String link, String expected) {
        String normalized = NormalizeLink.execute(link);
        assertEquals(expected, normalized);
    }

    public static Stream<Arguments> normalizeWhiteSpace() {
        return Stream.of(
                Arguments.arguments("Lorem", "lorem"),
                Arguments.arguments("Lorem ipsum", "lorem_ipsum"),
                Arguments.arguments("Lorem ipsum, 123elit.", "lorem_ipsum_123elit"),
                Arguments.arguments("Lorem ipsum, consectetuer adipiscing elit.", "lorem_ipsum_consectetuer_adipiscing_elit".substring(0, 30)),
                Arguments.arguments("Lorem{}ipsum@#$", "lorem%7B%7Dipsum%40%23%24"),
                Arguments.arguments("Zażółć gęślą jaźń", "zazolc_gesla_jazn")
        );
    }
}
