package dev.alt236.codeplasteragetestapp.classparsing.primitivetests;

import dev.alt236.codeplasteragetestapp.TestInputLogger;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class JavaClass implements CommonPrimitiveTests {
    @Override
    public void stringTest(@NotNull String arg) {
        TestInputLogger.log(arg);
    }

    @Override
    public void intTest(int arg) {
        TestInputLogger.log(arg);
    }

    @Override
    public void longTest(long arg) {
        TestInputLogger.log(arg);
    }

    @Override
    public void byteTest(byte arg) {
        TestInputLogger.log(arg);
    }

    @Override
    public void doubleTest(double arg) {
        TestInputLogger.log(arg);
    }

    @Override
    public void charTest(char arg) {
        TestInputLogger.log(arg);
    }

    @Override
    public void floatTest(float arg) {
        TestInputLogger.log(arg);
    }

    @Override
    public void shortTest(short arg) {
        TestInputLogger.log(arg);
    }

    @Override
    public void booleanTest(boolean arg) {
        TestInputLogger.log(arg);
    }
}
