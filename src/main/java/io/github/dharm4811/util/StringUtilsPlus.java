package io.github.dharm4811.util;

import java.text.Normalizer;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Small string helper utilities.
 */
public final class StringUtilsPlus {

    private StringUtilsPlus() {}

    public static boolean isNullOrBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    public static String truncate(String s, int maxLength) {
        if (s == null) return null;
        if (maxLength < 0) throw new IllegalArgumentException("maxLength must be >= 0");
        if (s.length() <= maxLength) return s;
        return s.substring(0, maxLength);
    }

    public static boolean safeEqualsIgnoreCase(String a, String b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return a.equalsIgnoreCase(b);
    }

    // slugify: ascii-only, lowercase, replace non-alphanum with '-'
    private static final Pattern NON_LATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    public static String slugify(String input) {
        if (input == null) return null;
        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NON_LATIN.matcher(normalized).replaceAll("");
        slug = slug.replaceAll("-{2,}", "-");
        slug = slug.replaceAll("^-|-$", "");
        return slug.toLowerCase(Locale.ROOT);
    }
}

