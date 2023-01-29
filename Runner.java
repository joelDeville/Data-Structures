public class Runner {
    public static void main(String... args) {
        runListTests();
        runHashTests();
    }

    //runs tests over List functionality
    private static void runListTests() {
        System.out.println("Testing List");
        VariableList<Integer> list = new VariableList<>();
        //setting list to nearly resize itself
        for (int i = 0; i < 9; i++) {
            list.add(i);
        }

        //testing add
        String method = "add()";
        int test = 1;
        list.add(1);
        int expected = 10;
        if (list.size() == expected) {
            printTest(test, method, true);
        } else {
            printTest(test, method, false);
        }

        //testing get
        test++;
        method = "get()";
        expected = 1;
        if (list.get(9) == expected) {
            printTest(test, method, true);
        } else {
            printTest(test, method, false);
        }

        //testing remove() on index
        test++;
        expected = 1;
        method = "remove()";
        if (list.remove(9) == expected) {
            printTest(test, method, true);
        } else {
            printTest(test, method, false);
        }

        //testing remove() on value
        test++;
        method = "remove()";
        boolean exp = true;
        if (list.remove(Integer.valueOf(8)) == exp) {
            printTest(test, method, true);
        } else {
            printTest(test, method, false);
        }

        test++;
        expected = 8;
        if (list.size() == expected) {
            printTest(test, method, true);
        } else {
            printTest(test, method, false);
        }
    }

    //runs tests over HashTable functionality
    private static void runHashTests() {
        System.out.println("Testing hash table");
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
