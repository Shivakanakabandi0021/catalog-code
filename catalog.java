import java.util.*;
import java.math.BigInteger;

public class ShamirSecretSharing {

    // Function to decode a number from a given base to base 10
    public static int decode(String value, int base) {
        return new BigInteger(value, base).intValue();
    }

    // Function to calculate Lagrange interpolation at x = 0 (to find the constant term)
    public static double lagrangeInterpolation(List<int[]> points) {
        double result = 0.0;
        int n = points.size();
        
        for (int i = 0; i < n; i++) {
            double term = points.get(i)[1];
            
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    term *= (0 - points.get(j)[0]) * 1.0 / (points.get(i)[0] - points.get(j)[0]);
                }
            }
            result += term;
        }
        
        return result;
    }

    public static void main(String[] args) {
        // Sample JSON data (parsed manually)                         
        Map<Integer, int[]> data = new HashMap<>();
        data.put(1, new int[] {10, decode("4", 10)});
        data.put(2, new int[] {2, decode("111", 2)});
        data.put(3, new int[] {10, decode("12", 10)});
        data.put(6, new int[] {4, decode("213", 4)});
        
        List<int[]> points = new ArrayList<>();
        
        // Create list of points (x, y)
        for (Map.Entry<Integer, int[]> entry : data.entrySet()) {
            int x = entry.getKey();
            int y = entry.getValue()[1];
            points.add(new int[] {x, y});
        }
        
        // Calculate the constant term c using Lagrange interpolation
        double c = lagrangeInterpolation(points);
        System.out.println("The constant term (c) is: " + c);
    }
}
