import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Operates with square matrices. The matrices are represented as 2D arrays.
 */

public class Matrix {

	private int[][] matrix;
	private int dimension;
	private Scanner input;

	/**
	 * Creates a (dimension x dimension) null-matrix as a 2D array.     
	 * @param dimension  dimension of the matrix       
	 */
	public Matrix(int dimension) {
		this.setDimension(dimension);
		matrix = new int[dimension][dimension];
		for (int row = 0; row < dimension; row++) {
			for (int col = 0; col < dimension; col++) {
				matrix[row][col] = 0;
			}
		}
	}

	/**
	 * Access to the dimension of the matrix.
	 * @return the dimension of the matrix.
	 */
	public int getDimension() {
		return this.dimension;

	}

	/**
	 * Scans a (dimension x dimension) matrix. It takes integer values from the input delimited with spaces. 
	 */
	public void scanMatrix() {
		input = new Scanner(System.in);
		int temp; // temporary value of scanned integer.
		for (int row = 0; row < getDimension(); row++) {
			for (int col = 0; col < getDimension(); col++) {
				temp = input.nextInt();
				setEdge(row, col, temp);
			}
		}
	}

	/**
	 * Sets the value of the edge directed from vertex1 to vertex2
	 * @param vertex1 start vertex
	 * @param vertex2 end vertex
	 * @param value - value to be set.
	 */
	protected void setEdge(int vertex1, int vertex2, double value) {
		matrix[vertex1][vertex2] = (int) value;
	}

	/**
	 * Returns any needed value of the edge directed from vertex1 to vertex2
	 * @param vertex1 start vertex
	 * @param vertex2 end vertex
	 * @return The weight of the edge from vertex1 to vertex2
	 */
	public double getValueOf(int vertex1, int vertex2) {
		return matrix[vertex1][vertex2];
	}
	
    /**
     * Gets the minimal number from a set of numbers
     * @param list - list of numbers to get the minimum value of
     * @return the minimal value from the set
     */
    public double getMin(ArrayList<Double> list) {
	   	double min; //variable to save the minimal value to 
		min = list.get(0);
		for(int i=1;i<list.size();i++){
	   		if (min > list.get(i)) {
	   			min = list.get(i);
	   		}
	   	 }    
		 return min;
	 }
    
    /**
     * Gets the maximal number from a set of numbers .It ignores the INF value as it represents only the no-edge.
     * @param list  list of numbers to get the maximum value of
     * @return the maximal value from the set
     */
    public double getMax(ArrayList<Double> list) {
    	double max= 0; //variable to save the maximal value to 
   		 
	   	for(int i=0;i<list.size();i++){
	   		if (max < list.get(i) && list.get(i) < 10000) {
	   			max = list.get(i);
   			}    		  	 	 
	   	}
		return max;
	}
	
	/**
     * Multiplies 2 matrices from the input.
     * @param m1 the multiplicand matrix 
     * @param2 m2 the multiplier matrix
     * @return Matrix  m1*m2
     */
     public Matrix mulMatrix(Matrix m1 , Matrix m2){
    	 int dim = m1.getDimension(); //the dimension of the given matrices
    	 Matrix matrix = new Matrix(dim);  //a matrix to save the powered matrix to   
    	 /*Makes the row*column multiplications. As it is max-plus algebra, the multiplication is replaced with + 
    	 and the sum is replaced with max */
    	 ArrayList<Double> temp = new ArrayList<Double>(); // temporary list to save the set of numbers we need the maximum of
    	 for (int i=0;i<dim;i++){
             for (int j=0;j<dim;j++){   
                for (int k=0;k<dim;k++){
                   temp.add(m1.getValueOf(i, k)+m2.getValueOf(k, j));
                }  
                matrix.setEdge(i, j, getMax(temp));
                temp.clear(); //resetting the list
             }
          }
		return matrix; 
     }

     
     /**
      * Takes a matrix, creates a list of matrices and adds the powered matrices to it.It computes matrix^2, matrix^3, ..., matrix^dim+1;
      * @param matrix matrix to get the powers of
      * @return ArrayList<Matrix> listOfMatrices - returns the list of matrices A, A^2, A^3, ..., A^dim+1;
      */
     public ArrayList<Matrix> powMatrix(Matrix matrix){
    	ArrayList<Matrix> listOfMatrices = new ArrayList<Matrix>(); //the list to save the powered matrices 
    	listOfMatrices.add(matrix); //adds the given matrix also as we also need its first orbit
    	Matrix temp = new Matrix(dimension);
    	//temporary matrix to save the A, A^2, A^3, ... A^dim matrices to;
    	//takes the temp matrix and adds a new matrix-the powered temp matrix to the list
		temp = mulMatrix(matrix, matrix);
    	for(int i=0;i<dimension+1;i++){    		
    		listOfMatrices.add(temp);
    		temp = mulMatrix(temp, matrix);
    	}
		return listOfMatrices;    	 
     }
     

	/**
	 * Prints out the matrix as a 2D array.
	 */
	public void printMatrix() {
		for (int[] row : matrix) {
			System.out.print("\t");
			System.out.println(Arrays.toString(row));
		}
	}


	private void setDimension(int dimension) {
		this.dimension = dimension;
	}

}
