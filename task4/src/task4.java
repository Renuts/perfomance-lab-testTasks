import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class task4 {
    public static void main(String[] args) {
        if (args.length != 1) {
            return;
        }

        String filePath = args[0];
        int[] array = readArrayFromFile(filePath);

        int moves = 0;
        int median = findMedian(array);

        for (int value : array) {
            moves += Math.abs(value - median);
        }

        System.out.println(moves);
    }

    private static int findMedian(int[] array) {
        int[] sortedArray = array.clone();
        Arrays.sort(sortedArray);

        if (sortedArray.length % 2 == 1) {
            return sortedArray[sortedArray.length / 2];
        } else {
            int mid1 = sortedArray[sortedArray.length / 2];
            int mid2 = sortedArray[sortedArray.length / 2 - 1];
            return (mid1 + mid2) / 2;
        }
    }

    private static int[] readArrayFromFile(String filePath) {
        ArrayList<Integer> numbers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                numbers.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int[] array = new int[numbers.size()];
        for (int i = 0; i < numbers.size(); i++) {
            array[i] = numbers.get(i);
        }
        return array;
    }
}
