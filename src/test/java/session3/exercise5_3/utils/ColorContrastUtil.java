package session3.exercise5_3.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ColorContrastUtil {

    private ColorContrastUtil() {
    }

    public static boolean isTransparent(String cssColor) {
        return "transparent".equalsIgnoreCase(cssColor)
                || cssColor.contains("rgba") && cssColor.endsWith(", 0)");
    }

    public static double calculateContrastRatio(String foreground, String background) {
        int[] rgbA = parseRgbColor(foreground);
        int[] rgbB = parseRgbColor(background);

        double lumA = calculateRelativeLuminance(rgbA);
        double lumB = calculateRelativeLuminance(rgbB);
        double lighter = Math.max(lumA, lumB);
        double darker = Math.min(lumA, lumB);

        return (lighter + 0.05) / (darker + 0.05);
    }

    private static int[] parseRgbColor(String cssColor) {
        if (cssColor == null) {
            throw new IllegalArgumentException("CSS color value is null.");
        }

        Pattern rgbPattern = Pattern.compile("(\\d+),\\s*(\\d+),\\s*(\\d+)");
        Matcher matcher = rgbPattern.matcher(cssColor);

        if (!matcher.find()) {
            throw new IllegalArgumentException("Unsupported CSS color: " + cssColor);
        }

        return new int[]{
                Integer.parseInt(matcher.group(1)),
                Integer.parseInt(matcher.group(2)),
                Integer.parseInt(matcher.group(3))
        };
    }

    private static double calculateRelativeLuminance(int[] rgb) {
        double r = normalizeChannel(rgb[0]);
        double g = normalizeChannel(rgb[1]);
        double b = normalizeChannel(rgb[2]);

        return 0.2126 * r + 0.7152 * g + 0.0722 * b;
    }


    private static double normalizeChannel(int channelValue) {
        double value = channelValue / 255.0;
        if (value <= 0.03928) {
            return value / 12.92;
        }
        return Math.pow((value + 0.055) / 1.055, 2.4);
    }
}
