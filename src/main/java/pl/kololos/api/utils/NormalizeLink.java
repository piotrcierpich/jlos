package pl.kololos.api.utils;

import org.apache.commons.lang3.StringUtils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class NormalizeLink {
    private static final int MAX_LENGTH = 30;
    private static final String[] DIACRITICS = new String[]{"Ą", "ą", "Ć", "ć", "Ę", "ę", "Ł", "ł", "Ń", "ń", "Ó", "ó", "Ś", "ś", "Ź", "ź", "Ż", "ż"};

    private static final String[] DIACRITICS_NORMALIZED = new String[]{"A", "a", "C", "c", "E", "e", "L", "l", "N", "n", "O", "o", "S", "s", "Z", "z", "Z", "z"};

    public static String execute(String link) {
        if (StringUtils.isBlank(link)) {
            throw new IllegalArgumentException("link to normalize cannot be empty");
        }
        String normalized = link
                .replace(" ", "_")
                .replaceAll("[,\\.]", "")
                .toLowerCase(Locale.getDefault());
        normalized = removeDiacritics(normalized);
        // encodes to HTML form submit, close enough
        String encoded = URLEncoder.encode(normalized, StandardCharsets.UTF_8);
        return encoded.substring(0, Math.min(encoded.length(), MAX_LENGTH));
    }

    private static String removeDiacritics(String normalized) {
        for (int i = 0; i < DIACRITICS.length; i++) {
            normalized = normalized.replace(DIACRITICS[i], DIACRITICS_NORMALIZED[i]);
        }
        return normalized;
    }
}
