import java.util.Scanner;
/**
 * MAX-PLUS ALGEBRA!
 * The program takes an interval-matrix from input ( as 2 matrices : one stands for the upper-values the other one for lower-values )
 * and checks whether it is universally weakly robust or not. It is implemented to work only with matrices of max dimension 5.
 * 
 * NOTE!
 * Whether there's no edge between two vertexes represent it with the value 100000!
 * Enter the integer values of the matrices delimited with spaces or enter, press ENTER when done.
 * 
 * @author matthires
 *
 */

public class Main {
    private static Scanner input;

	public static void main(String[] args){
        input = new Scanner(System.in);
        System.out.println("Enter the dimension of the matrix");
        int dimension = input.nextInt(); // dimension of the matrix we want to check
        assert dimension < 5 && dimension > 0 : "The dimension of the matrix must be greater than 0, but less than 6";
        System.out.println("Enter upper values of the matrix");
        Matrix  upperValueMatrix = new Matrix(dimension); // matrix to save the upper values of the given interval-matrix
        upperValueMatrix.scanMatrix();	
        upperValueMatrix.printMatrix();
        System.out.println("Enter lower values of the matrix");
        Matrix lowerValueMatrix = new Matrix(dimension); // matrix to save the lower values of the given interval-matrix
        lowerValueMatrix.scanMatrix();
        lowerValueMatrix.printMatrix();        
        CycleDetector cd = new CycleDetector(upperValueMatrix, lowerValueMatrix);
        cd.isRobust();
        
	}
}

