package hardwarebacked;

import org.checkerframework.checker.crypto.qual.Top;
import org.checkerframework.checker.crypto.qual.Unique;

class SubtypingTest {

    // Here we just want to test the subtyping rules, normally we should not use
    // @UnknownAlgorithmOrProvider and @AllowedAlgorithms in this way.
    void test(@Top String x, @Unique String y, @Unique({"algo1", "algo2"}) String z) {
        @Top String a = x;
        @Top String b = y;
        @Unique({"algo1", "algo2"})
        String e = z;
        @Unique({"algo1"})
        // :: error: assignment
        String f = z;
    }
}
