import java.io.*;
import java.util.Scanner;

public class task2 {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java Task2 <file1> <file2>");
            return;
        }

        int pointCount = 0; // Переменная для подсчета точек

        float[] x_points = new float[100];
        float[] y_points = new float[100];

        try {
            FileInputStream file1Input = new FileInputStream(new File(args[0]));
            Scanner file1Scanner = new Scanner(file1Input);

            float x_center = file1Scanner.nextFloat();
            float y_center = file1Scanner.nextFloat();
            float r_circle = file1Scanner.nextFloat();

            FileInputStream file2Input = new FileInputStream(new File(args[1]));
            Scanner file2Scanner = new Scanner(file2Input);

            while (file2Scanner.hasNext()) {
                float x_point = file2Scanner.nextFloat();
                float y_point = file2Scanner.nextFloat();

                x_points[pointCount] = x_point;
                y_points[pointCount] = y_point;

                pointCount++; // Увеличиваем счетчик точек


            }

            if (pointCount == 0){
                System.out.println("There are 0 points");
            }

            for (int i = 0; i < pointCount; i++) {
                float distance = (float) Math.sqrt(Math.pow(x_points[i] - x_center, 2) + Math.pow(y_points[i] - y_center, 2));

                int result;
                if (distance < r_circle) {
                    result = 1; // Точка внутри окружности
                } else if (distance == r_circle) {
                    result = 0; // Точка лежит на окружности
                } else {
                    result = 2; // Точка снаружи окружности
                }

                System.out.println(result);
            }

            file1Scanner.close();
            file2Scanner.close();

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
