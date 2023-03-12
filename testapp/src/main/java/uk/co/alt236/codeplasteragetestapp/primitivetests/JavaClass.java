package uk.co.alt236.codeplasteragetestapp.primitivetests;

import org.jetbrains.annotations.NotNull;
import uk.co.alt236.codeplasteragetestapp.TestInputLogger;

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
