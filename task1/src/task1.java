import java.util.ArrayList;

public class task1 {
    public static void main(String[] args) {
        if (args.length < 2) {
            return;
        }

        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        int[] circularArray = new int[n];

        for (int i = 0; i < n; i++) {
            circularArray[i] = i + 1;
        }

        ArrayList<int[]> extraArrays = createExtraArrays(circularArray, m);

        for (int[] extraArray : extraArrays) {
            System.out.print(extraArray[0]);
        }
    }

    private static ArrayList<int[]> createExtraArrays(int[] circularArray, int m) {
        ArrayList<int[]> extraArrays = new ArrayList<>();
        int currentValue = 1;

        while (currentValue != 1 || extraArrays.isEmpty()) {
            int[] extraArray = new int[m];
            extraArray[0] = currentValue;

            for (int i = 1; i < m; i++) {
                extraArray[i] = (extraArray[i - 1] % circularArray.length) + 1;
            }

            extraArrays.add(extraArray);
            currentValue = extraArray[m - 1];
        }

        return extraArrays;
    }
}
