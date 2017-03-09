import java.util.ArrayList;

/*
 * Computing the eigenvalue of the given matrix with column principal.
 */
 
public class KarpAlgorithm {
    private Matrix matrix;
    private int dim;
   
    public KarpAlgorithm(Matrix matrix){
        this.matrix = matrix;
        dim = matrix.getDimension();
    }
   
    /**
     * This function returns a list of first columns of the matrices A, A^2, A^3, ... A^dim+1;
     * @return list of the first columns of the powered matrices.
     */
    public ArrayList<ArrayList<Double>> getCols(){
        // list to save the first columns of the powered matrices
    	ArrayList<ArrayList<Double>> listOfCols = new ArrayList<ArrayList<Double>>();  
        for(int i=0;i<=dim;i++){
    		listOfCols.add(new ArrayList<Double>());
        	for(int j=0;j<dim;j++){
        		listOfCols.get(i).add((double) matrix.powMatrix(matrix).get(i).getValueOf(j, 0));
        	}
        }        
        return listOfCols;
    }   

      
     /**
      * Calculates the eigenvalue of the matrix via column principle 
      * @param matrix Matrix to get the eigenvalue of
      * @return the eigenvalue of the lower-value matrix from the input
      */
     public double getEigenvalue(Matrix matrix){
    	 ArrayList<Double> listOfMins = new ArrayList<Double>(); //list to save the minimum values 
    	 // temporary list to save the values to get the minimum of
    	 ArrayList<Double> temp = new ArrayList<Double>(); 
    	 //adds the needed values(column principle) to the list to get the max of them 
    	 for(int i=0;i<dim;i++){
    		 temp.clear(); 
			 int k = dim;    		 
    		 for(int j=0;j<dim;j++){
				 temp.add((getCols().get(dim).get(i) - getCols().get(j).get(i)) / (k));  
				 k--;
     		 }
    		 listOfMins.add(matrix.getMin(temp));    		 
    	 } 	    	 
		 return matrix.getMax(listOfMins);    	 
     }
     
}