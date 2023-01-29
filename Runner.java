public class Runner {
    public static void main(String... args) {
        runTests();
    }

    //runs tests over HashTable functionality
    private static void runTests() {
        HashTable<Integer, Integer> table = new HashTable<>();
        //setting table to nearly resize itself (with 0.75 load factor)
        for (int i = 0; i < 7; i++) {
            table.put(i, i);
        }

        String method = "put()";
        int test = 1;
        table.put(1, 2);
        int expected = 7;
        if (table.size() == expected) {
            printTest(test, method, true);
        } else {
            printTest(test, method, false);
        }

        test++;
        table.put(1, 3);
        if (table.size() == expected) {
            printTest(test, method, true);
        } else {
            printTest(test, method, false);
        }

        test++;
        method = "get()";
        expected = 3;
        if (table.get(1) == expected) {
            printTest(test, method, true);
        } else {
            printTest(test, method, false);
        }

        test++;
        method = "remove()";
        if (table.remove(1) == expected) {
            printTest(test, method, true);
        } else {
            printTest(test, method, false);
        }

        test++;
        expected = 6;
        if (table.size() == expected) {
            printTest(test, method, true);
        } else {
            printTest(test, method, false);
        }
    }

    private static void printTest(int test, String method, boolean passed) {
        String result = (passed) ? "PASSED" : "FAILED";
        System.out.printf("Test %d on %s: %s\n", test, method, result);
    }
}
