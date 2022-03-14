package hardwarebacked;

import org.checkerframework.checker.crypto.qual.AllowedAlgorithms;
import org.checkerframework.checker.crypto.qual.UnknownAlgorithmOrProvider;

class SubtypingTest {

    // Here we just want to test the subtyping rules, normally we should not use
    // @UnknownAlgorithmOrProvider and @AllowedAlgorithms in this way.
    void test(
            @UnknownAlgorithmOrProvider String x,
            @AllowedAlgorithms String y,
            @AllowedAlgorithms({"algo1", "algo2"}) String z) {
        @UnknownAlgorithmOrProvider String a = x;
        @UnknownAlgorithmOrProvider String b = y;
        @AllowedAlgorithms({"algo1", "algo2"})
        String e = z;
        @AllowedAlgorithms({"algo1"})
        // :: error: assignment
        String f = z;
    }
}
