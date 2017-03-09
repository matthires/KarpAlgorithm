
/**
 * Works with cycles,finds critical cycles,and checks the robustness.
 * 
 */

public class CycleDetector {
	private static long INF = 100000; //no edge between 2 vertexes
	
	private Matrix lowerMatrix;	
	private Matrix upperMatrix;
	private int dim;
	private double eigenValue;
	private KarpAlgorithm kA;
	
	
	/**
	 * Constructor of the class, it automatically calculates the eigenvalue of the lower-value matrix.
	 * @param upperMatrix the upper-value matrix from the input
	 * @param lowerMatrix the lower-value matrix from the input
	 */	 
	public CycleDetector(Matrix upperMatrix, Matrix lowerMatrix){
		dim = upperMatrix.getDimension();
		this.upperMatrix = upperMatrix;
		this.lowerMatrix = lowerMatrix;
		kA = new KarpAlgorithm(lowerMatrix);
		this.eigenValue = kA.getEigenvalue(lowerMatrix);
	}
	
		
	
	/**
	 * Checks and prints out whether the given matrix is universally weakly robust or not.
	 * Checks for all the critical cycles by checking all the possible paths,if finds it,then the values of the vertexes of the 
	 * crit. cycles substitutes to the given position of the upper-value matrix and checks whether the eigenvalues of the lower-matrix 
	 * and the upper-value matrix are the same and if the found cycle is hamiltonian or not. If one of the critical cycles substituted 
	 * to the upper-value matrix returns different eigenvalue or there's no hamiltonian cycle found it will return false.
	 * The INF value means that there's no edge between 2 vertexes
	 * 
	 */
	public void isRobust(){
		Matrix cA = new Matrix(dim); //It will stand for the upper-value matrix with the substituted values of the critical cycles
		boolean isRob = false; //checks if all the possibilities are true
		boolean isHamiltonian= false; // checks if there's a a hamiltonian cycle in the graph
		cA = upperMatrix; //saves the upper value matrix to cA
		double weight = 0; //helping variable to save the weight of the found cycles
		
		//cycles of length 1
		for(int i=0;i<dim;i++){
			weight = lowerMatrix.getValueOf(i, i);
			if(isCritical(weight, 1)){
				cA.setEdge(i, i, lowerMatrix.getValueOf(i, i));
				if(isEqualCycle(cA)){
					isRob = true;
				}	
				else{
					isRob = false;
					return;
				}
			}
			weight = 0;
			cA = upperMatrix; //resetting the cA
		}
		
		//cycles of length 2
		if(dim >=2){
			for(int j=0;j<dim;j++){
				for(int k=0;k<dim;k++){
					if(j != k && lowerMatrix.getValueOf(j, k) != INF && lowerMatrix.getValueOf(k, j) != INF){
						weight = lowerMatrix.getValueOf(j, k) + lowerMatrix.getValueOf(k, j);
						if(dim == 2){
							isHamiltonian = true;
						}
					}
					if(isCritical(weight, 2)){
						cA.setEdge(j, k, lowerMatrix.getValueOf(j, k));
						cA.setEdge(k, j, lowerMatrix.getValueOf(k, j));
						if(isEqualCycle(cA)){
							isRob = true;
						}
						else{
							isRob = false;
							return;
						}
					}
					weight = 0;
					cA = upperMatrix; 
				}
			}
		}
			
		//cycles of length 3
		if(dim >=3){
			for(int j=0;j<dim;j++){
				for(int k=0;k<dim;k++){
					for(int l=0;l<dim;l++){
						if(j !=k && j != l && k != l && lowerMatrix.getValueOf(j, k) != INF && lowerMatrix.getValueOf(k, l) != INF && lowerMatrix.getValueOf(l, j) != INF ){
							weight = lowerMatrix.getValueOf(j, k) + lowerMatrix.getValueOf(k, l) + lowerMatrix.getValueOf(l, j);
							if(dim == 3){
								isHamiltonian = true;
							}
						}
						if(isCritical(weight, 3)){
							cA.setEdge(j, k, lowerMatrix.getValueOf(j, k));
							cA.setEdge(k, l, lowerMatrix.getValueOf(k, l));
							cA.setEdge(l, j, lowerMatrix.getValueOf(l, j));
							if(isEqualCycle(cA)){
								isRob = true;
							}
							else{
								isRob = false;
								return;
							}
						}
						weight = 0;
						cA = upperMatrix;
					}
				}
			}
		}
		
		//cycles of length 4
		if(dim >=4){
			for(int j=0;j<dim;j++){
				for(int k=0;k<dim;k++){
					for(int l=0;l<dim;l++){
						for(int m=0;m<dim;m++){
							if(j !=k && j != l && j != m && k != l && k != m && l != m && lowerMatrix.getValueOf(j, k) != INF && lowerMatrix.getValueOf(k, l) != INF && lowerMatrix.getValueOf(l, m) != INF && lowerMatrix.getValueOf(m, j) != INF ){
								weight = lowerMatrix.getValueOf(j, k) + lowerMatrix.getValueOf(k, l) + lowerMatrix.getValueOf(l, m) + lowerMatrix.getValueOf(m, j);
								if(dim == 4){
									isHamiltonian = true;
								}
							}
							if(isCritical(weight, 4)){
								cA.setEdge(j, k, lowerMatrix.getValueOf(j, k));
								cA.setEdge(k, l, lowerMatrix.getValueOf(k, l));
								cA.setEdge(l, m, lowerMatrix.getValueOf(l, m));
								cA.setEdge(m, j, lowerMatrix.getValueOf(m, j));
								if(isEqualCycle(cA)){
									isRob = true;
								}
								else{
									isRob = false;
									return;
								}
							}
							weight = 0;
							cA = upperMatrix;
						}
					}
				}
			}
		}
		
		//cycles of length 5
		if(dim >=5){			
			for(int i=0;i<dim;i++){
				for(int j=0;j<dim;j++){
					for(int k=0;k<dim;k++){
						for(int l=0;l<dim;l++){
							for(int m=0;m<dim;m++){
								if(i != j && i !=k && i != l && i != m && j != k && j != l && j != m && k != l && k != m && l != m && lowerMatrix.getValueOf(j, k) != INF && lowerMatrix.getValueOf(k, l) != INF && lowerMatrix.getValueOf(l, m) != INF && lowerMatrix.getValueOf(m, j) != INF ){
									weight = lowerMatrix.getValueOf(i, j) + lowerMatrix.getValueOf(j, k) + lowerMatrix.getValueOf(k, l) + lowerMatrix.getValueOf(l, m) + lowerMatrix.getValueOf(m, i);
									if(dim == 5){
										isHamiltonian = true;
									}
								}
								if(isCritical(weight, 5)){
									cA.setEdge(i, j, lowerMatrix.getValueOf(i, j));
									cA.setEdge(j, k, lowerMatrix.getValueOf(j, k));
									cA.setEdge(k, l, lowerMatrix.getValueOf(k, l));
									cA.setEdge(l, m, lowerMatrix.getValueOf(l, m));
									cA.setEdge(m, i, lowerMatrix.getValueOf(m, i));
									if(isEqualCycle(cA)){
										isRob = true;
									}
									else{
										isRob = false;
										return;
									}
								}
								weight = 0;
								cA = upperMatrix;
							}
						}
					}
				}
			}
		}
		
		if(isRob && isHamiltonian){
			System.out.println("The matrix is universally weakly robust!");
	        System.out.println("The eigenvalue of the matrix is: " + kA.getEigenvalue(lowerMatrix));
		}
		else{
			System.out.println("The matrix is NOT universally weakly robust!");
		}
	}
	
	/**
	 * Checks whether the given cycle is critical or not by comparing the cycles average weight to the eigenvalue
	 * @param weight the sum of weights of the given cycle
	 * @param length the length of the given cycle
	 * @return true if the cycle is critical, else false
	 */
    public boolean isCritical(double weight, double length){
        if(weight/length == eigenValue){
            return true; 
        }
        return false;
    }
    
    /**
     * Checks if the in given matrix has the same eigenvalue as the lower-value matrix
     * @param matrix matrix to check the eigenvalue of
     * @return true if the eigenvalues are equal, else false
     */
    public boolean isEqualCycle(Matrix matrix){
    	if(kA.getEigenvalue(matrix) == eigenValue){    		
			return true;
		}
    	else{
    		return false;
    	}		
    	
    }
}
