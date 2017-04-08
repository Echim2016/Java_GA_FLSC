package FLSC;
import java.io.*;
import java.util.Random;

public class FLSC {
	private int totalBudget;
	private int manNum;
	private int parkNum;
	private int facilityNum;
	private int totalCost;
	private int[][] parent_pool;
	private int[][] optimal_facility_area;
	private int[][] kid_pool;
	private 
		ArrayList<chromosome> chromosomeArray = new ArrayList<chromosome>;

	public static void main(String args[]) throws IOException {
		Random rand = new Random();
	}

	public FLSC(int man,int park,int facility,int budget) {
		manNum = man;
		parkNum = park;
		facilityNum = facility;
		totalBudget = budget;

		totalCost = 0;

		parent_pool = new int[poolLength][];
		kid_pool = new int[poolLength*2][];

		for(int i=0;i<poolLength;i++) {
			parent_pool[i] = new int[parkNum];
			for(int j=0;j<parkNum;j++) {
				parent_pool[i][j] = 0;
			}
		}

		for(int i = 0; i<poolLength*2; i++) {
        	kid_pool[i] = new int[parkNum];
        	for (int j = 0; j < parkNum; j++){
        	    kid_pool[i][j]=0;
        	}
        }

        optimal_facility_area = new int[parkNum][];

        for (int i = 0; i< parkNum; i++) {
    		optimal_facility_area[i] = new int[facilityNum];
    	}
    	for (int i = 0; i< parkNum; i++) {
    		for (int j = 0; j < facilityNum; j++){
    			optimal_facility_area[i][j]=0;
    		}
    	}
    	for (int i = 0; i < poolLength*2; i++){
    	    chromosome temp;
    	    chromosomeArray.add(temp);
    	}
    }

    public static void original_gene(int[][] S) {
    	int scale = 0;
    	for(int i=0; i<poolLength; i++){
        	for(int j=0; j< parkNum; j++){
        		while(1){
			    	scale = rand.nextInt(6);
			    	if(scale != 0 && S[j][scale-1] == 1){
			    		parent_pool[i][j] = scale;
			    		break;
			    	}else if(scale == 0){
			    		parent_pool[i][j] = 0;
			    		break;
			    	}      		
        		}          
        	}
    	}
    }

    public static void crossover() {

		for (int i = 0; i < poolLength/2; i++){
    	    for (int j = 0; j < parkNum/2; j++){
    	        kid_pool[2*i][j] = parent_pool[2*i][j];
    	    }
			for (int j = parkNum/2; j < parkNum; j++){
    	        kid_pool[2*i][j] = parent_pool[2*i+1][j];
    	    }
    	}
	
	
    	for (int i = 1; i < poolLength/2; i++){
    	    for (int j = 0; j < poolLength/2; j++){
    	        kid_pool[2*i-1][j] = parent_pool[2*i-1][j];
    	    }
	
    	    for (int j = parkNum/2; j < parkNum; j++){
    	        kid_pool[2*i-1][j] = parent_pool[2*i-2][j];
    	    }
    	    
    	}
	
    	for (int i = 0; i < parkNum; i++){       
    	    kid_pool[poolLength-1][i] = parent_pool[poolLength-1][i];
    	}
	
    	for (int i = parkNum/2; i < parkNum; i++){       
    	    kid_pool[poolLength-1][i]=parent_pool[poolLength-2][i];
    	}
	
    	for (int i = poolLength ; i < 2*poolLength; i++) {
    	    for (int j = 0; j < parkNum; j++) {
    	        kid_pool[i][j] = parent_pool[i-poolLength][j];
    	    }
    	}

    }

    public static int[][] randomZ(int[][] q,int[][] T,int num_of_chromosome) {
    	int js_area = 0;	
		int is_zero = 0;

		int[][] optimal_facility_area = new int[parkNum][];

		for (int i = 0; i< parkNum; i++) {
    	    facility_floor_area[i] = new int[facilityNum];
    	    for (int j = 0; j < facilityNum; j++){
    	        facility_floor_area[i][j]=0;
    	    }        
    	}

    	for(int i = 0; i < PARK; i++){

			if(kid_pool[num_of_chromosome][i] != 0){
				js_area = q[i][kid_pool[num_of_chromosome][i]-1];
				is_zero = 0;
			}else{
				is_zero = 1;
				js_area = 0;
			}
	
			while(1){
						
				for(int j = 0; j < FACILITY; j++){
					if(js_area != 0 && T[i][j] != 0){
						if(is_zero == 0){
							
							facility_floor_area[i][j] = rand() % js_area;
						}else{
							facility_floor_area[i][j] = 0;
						}
						
					}else{
						facility_floor_area[i][j] = 0;
					}				
				}
				int sum = 0;
				if(is_zero == 1){
					break;
				}else{
					for(int j =0; j<FACILITY; j++){
						sum = sum + facility_floor_area[i][j];
					}
				}
				if(sum < js_area){
					break;
				}
				
			}
		}
		return facility_floor_area;
    }

    public static int cost(int[][] f, int[] c, int[][] facility_floor_area, int num_of_chromosome) {
    	int f_cost = 0;
		int c_cost = 0;
    	int total_cost = 0;
	
		for(int i = 0; i < PARK; i++){
			if(kid_pool[num_of_chromosome][i] != 0){
				f_cost += f[i][kid_pool[num_of_chromosome][i]-1];
			}
	
		}
		for(int i = 0; i < PARK; i++){
			for(int j = 0; j < FACILITY; j++){
				c_cost += c[j] * facility_floor_area[i][j];
			}
		}
	
		total_cost = f_cost + c_cost;
		return total_cost;
    }

    public static double fitness(int num_of_chromosome) {
    	boolean the_same;
    	int equalCount;
    	int index;
    	for(int i=0;i<poolLength;i++) {
    		equalCount = 10;
    		the_same = 0;
    		System.out.println("lala");

    		for(int j=0;j<parkNum;j++) {
    			System.out.print("*"+kid_pool[num_of_chromosome][j]+" ");
    		}
    		System.out.println();

    		for(int j=0;j<parkNum;j++) {
    			System.out.print("#"+chromosomeArray[i].scale[j]+" ");
    		}
    		System.out.println();

    		for(int j=0;j<parkNum;j++) {
    			if(chromosomeArray[i].scale[j] != kid_pool[num_of_chromosome][j]) {
    				equalCount--;
    				System.out.println(equalCount+"~~");
    				break;
    			}
    		}
    	}
    }
}
