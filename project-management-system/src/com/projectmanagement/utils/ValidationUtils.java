package com.projectmanagement.utils;

public final class ValidationUtils {
    private ValidationUtils() {
    }

    public static boolean isWithinRange(double value, double min, double max) {
        return value >= min && value <= max;
    }

    public static boolean isNonEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

    public static boolean isPositive(int value) {
        return value > 0;
    }
}

