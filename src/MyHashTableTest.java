import java.util.Random;

public class MyHashTableTest {

    // 10 000 elements...

    public static void main(String[] args) {

        MyHashTable<MyTestingClass, Integer> myHashTable = new MyHashTable<>();
        Random random = new Random();

        for (int i = 0; i < 10000; i++) { // filling hash table with 10 000 elements...
            String randomPassword = generateRandomPassword();
            int[] randomGrades = generateRandomGrades();
            MyTestingClass test = new MyTestingClass(randomPassword, randomGrades); // random key
            Integer value = random.nextInt(100); // random value
            myHashTable.put(test, value);
        }

        int counter = 0;
        for (int i = 0; i < myHashTable.getChainArray().length; i++) { // displaying number of elements in each bucket
            MyHashTable<MyTestingClass, Integer>.HashNode<MyTestingClass, Integer> current = myHashTable.getChainArray()[i];
            while (current != null) { // count elements in each bucket
                counter++;
                current = current.getNext();
            }
            System.out.println("Bucket number " + i + " contains " + counter + " elements");
            counter = 0;
        }

        // testing get method
        int[] testGrades = {4,5,5,3};
        MyTestingClass key = new MyTestingClass("12345", testGrades);
        myHashTable.put(key, 999);
        int testGet = myHashTable.get(key);
        System.out.println(testGet);

        // testing remove method
//        int testRemove = myHashTable.remove(key);
//        System.out.println(testRemove);

        // testing contains method
        boolean testContains = myHashTable.contains(999);
        System.out.println(testContains);

        // testing getKey method
        MyTestingClass returnedKey = myHashTable.getKey(999);
        System.out.println(returnedKey);
    }

    // method for generating random password
    // that consists of digits, letters and special symbols
    private static String generateRandomPassword() {
        Random random = new Random();
        int length = random.nextInt(4,  11);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length/2; i++) {
            char letter = (char)(random.nextInt(26) + 'a');
            char symbol = (char)(random.nextInt(15) + '!');
            int digit = random.nextInt(10);
            sb.append(letter);
            sb.append(symbol);
            sb.append(digit);
        }
        sb.setLength(length);
        return sb.toString();
    }

    // method for generating integer array with 4 random numbers;
    private static int[] generateRandomGrades() {
        Random random = new Random();
        int length = 4;
        int[] randomGrades = new int[length];
        for (int i = 0; i < length; i++) {
            randomGrades[i] = random.nextInt(1, 5);
        }
        return randomGrades;
    }
}
