package dev.alt236.codeplasteragetestapp.classparsing.arraytests;

import dev.alt236.codeplasteragetestapp.TestInputLogger;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@SuppressWarnings("unused")
public class JavaClass implements CommonArrayTests {

    @Override
    public void booleanArrayTest(@NotNull Boolean[] args) {
        TestInputLogger.log(args);
    }

    @Override
    public void byteArrayTest(@NotNull Byte[] args) {
        TestInputLogger.log(args);
    }

    @Override
    public void charArrayTest(@NotNull Character[] args) {
        TestInputLogger.log(args);
    }

    @Override
    public void doubleArrayTest(@NotNull Double[] args) {
        TestInputLogger.log(args);
    }

    @Override
    public void intArrayTest(@NotNull Integer[] args) {
        TestInputLogger.log(args);
    }

    @Override
    public void longArrayTest(@NotNull Long[] args) {
        TestInputLogger.log(args);
    }

    @Override
    public void shortTest(@NotNull Short[] args) {
        TestInputLogger.log(args);
    }

    @Override
    public void stringArrayTest(@NotNull String[] args) {
        TestInputLogger.log(args);
    }

    @Override
    public void serializableArray(@NotNull Serializable[] args) {
        TestInputLogger.log(args);
    }
}
