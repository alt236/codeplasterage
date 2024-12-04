package dev.alt236.codeplasteragetestapp.classparsing.nested;

import dev.alt236.codeplasteragetestapp.TestInputLogger;
import dev.alt236.codeplasteragetestapp.classparsing.primitivetests.CommonPrimitiveTests;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class JavaNested implements CommonPrimitiveTests {

    @Override
    public void stringTest(@NotNull String arg) {

    }

    @Override
    public void intTest(int arg) {

    }

    @Override
    public void longTest(long arg) {

    }

    @Override
    public void byteTest(byte arg) {

    }

    @Override
    public void doubleTest(double arg) {

    }

    @Override
    public void charTest(char arg) {

    }

    @Override
    public void floatTest(float arg) {

    }

    @Override
    public void shortTest(short arg) {

    }

    @Override
    public void booleanTest(boolean arg) {

    }

    public static class JavaNestedStatic implements CommonPrimitiveTests {

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

    @SuppressWarnings("InnerClassMayBeStatic")
    public class JavaNestedNonStatic implements CommonPrimitiveTests {

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
}
