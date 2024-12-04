package dev.alt236.codeplasteragetestapp.classparsing.statictests;

import dev.alt236.codeplasteragetestapp.TestInputLogger;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class JavaStatic {
    public static void stringArrayTest(@NotNull String[] args) {
        TestInputLogger.log(args);
    }

    public static void intArrayTest(@NotNull Integer[] args) {
        TestInputLogger.log(args);
    }

    public static void longArrayTest(@NotNull Long[] args) {
        TestInputLogger.log(args);
    }

    public static void byteArrayTest(@NotNull Byte[] args) {
        TestInputLogger.log(args);
    }

    public static void doubleArrayTest(@NotNull Double[] args) {
        TestInputLogger.log(args);
    }
}
