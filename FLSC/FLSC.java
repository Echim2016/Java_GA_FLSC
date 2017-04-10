package FLSC;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.PriorityQueue;  

//import file.chromosome;
//import file.file;
import org.javatuples.*;

public class FLSC {
	
	private int costIteration = 2;
	
	private static int totalBudget;
	private static int manNum = file.MAN;
	private static int parkNum = file.PARK;
	private static int facilityNum = file.FACILITY;
	private int totalCost;
	private static int[][] parent_pool;
	private int[][] optimal_facility_area;
	private static int[][] kid_pool;
	private static int poolLength = 20;
	ArrayList<chromosome> chromosomeArray = new ArrayList<chromosome>();
	static Random rand = new Random();

	public static void main(String args[]) throws IOException{

		FLSC test = new FLSC(manNum,parkNum,facilityNum,totalBudget);
		test.GA();
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
    	    chromosome temp = new chromosome();
    	    chromosomeArray.add(temp);
    	}
    }

    public static void original_gene(int[][] S) throws IOException {
    	int scale = 0;
    	int[][] myS = file.getS();
    	
    	for(int i=0; i<poolLength; i++){
        	for(int j=0; j< parkNum; j++){
        		while(true){
			    	scale = rand.nextInt(6);
			    	if(scale != 0 && file.S[j][scale-1] == 1){
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

    	    for (int j = 0; j < parkNum/2; j++){
    	        kid_pool[2*i-1][j] = parent_pool[2*i-1][j];
    	    }
	
    	    for (int j = parkNum/2; j < parkNum; j++){
    	        kid_pool[2*i-1][j] = parent_pool[2*i-2][j];
    	    }
    	    
    	}
    	/*
    	for (int i = 0; i < parkNum; i++){       
    	    kid_pool[poolLength-1][i] = parent_pool[poolLength-1][i];
    	}
	
    	for (int i = parkNum/2; i < parkNum; i++){       
    	    kid_pool[poolLength-1][i]=parent_pool[poolLength-2][i];
    	}
    	*/
	
    	for (int i = poolLength ; i < 2*poolLength; i++) {
    	    for (int j = 0; j < parkNum; j++) {
    	        kid_pool[i][j] = parent_pool[i-poolLength][j];
    	    }
    	}

    }

    public int[][] randomZ(int[][] q,int[][] T,int num_of_chromosome) {
    	int js_area = 0;	
		int is_zero = 0;

		//Initialize
		int[][] facility_floor_area = new int[parkNum][];
		for (int i = 0; i< parkNum; i++) {
    	    facility_floor_area[i] = new int[facilityNum];
    	    for (int j = 0; j < facilityNum; j++){
    	        facility_floor_area[i][j]=0;
    	    }        
    	}

    	for(int i = 0; i < parkNum; i++){

			if(kid_pool[num_of_chromosome][i] != 0){
				js_area = q[i][kid_pool[num_of_chromosome][i]-1];
				is_zero = 0;
			}else{
				is_zero = 1;
				js_area = 0;
			}
			int sum =0;
			while(is_zero==0 || sum>=js_area){
				System.out.println("FUCK");
				for(int j = 0; j < facilityNum; j++){
					if(js_area != 0 && T[i][j] != 0){
						if(is_zero == 0){
							
							facility_floor_area[i][j] = rand.nextInt(js_area);
						}else{
							facility_floor_area[i][j] = 0;
						}
						
					}else{
						facility_floor_area[i][j] = 0;
					}				
				}
//				sum = 0;
				if(is_zero == 1){
					break;
				}else{
					for(int j =0; j<facilityNum; j++){
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

    public int cost(int[][] f, int[] c, int[][] facility_floor_area, int num_of_chromosome) {
    	int f_cost = 0;
		int c_cost = 0;
    	int total_cost = 0;
	
		for(int i = 0; i < parkNum; i++){
			if(kid_pool[num_of_chromosome][i] != 0){
				f_cost += f[i][kid_pool[num_of_chromosome][i]-1];
			}
	
		}
		for(int i = 0; i < parkNum; i++){
			for(int j = 0; j < facilityNum; j++){
				c_cost += c[j] * facility_floor_area[i][j];
			}
		}
	
		total_cost = f_cost + c_cost;
		return total_cost;
    }

    public double fitness(int num_of_chromosome) throws IOException {
		int[] myD = file.getD();
		int[][] myQ = file.getQ();
		int[][] myT = file.getT();
		int[] myC = file.getC();
		int[][] myF = file.getF();
				
    	boolean the_same = false;
    	int equalCount;
    	int index = 0;
    	for(int i=0;i<poolLength;i++) {
    		equalCount = 10;
    		the_same = false;

    		for(int j=0;j<parkNum;j++) {
    			System.out.print("*"+kid_pool[num_of_chromosome][j]+" ");
    		}
    		System.out.println();

    		for(int j=0;j<parkNum;j++) {
    			System.out.print("#"+chromosomeArray.get(i).scale[j]+" ");
    		}
    		System.out.println();

    		for(int j=0;j<parkNum;j++) {
    			if(chromosomeArray.get(i).scale[j] != kid_pool[num_of_chromosome][j]) {
    				equalCount--;
    				System.out.println(equalCount+"~~");
    				break;
    			}
    		}
    		System.out.println();
    		if(equalCount == parkNum) {
    			the_same = true;
    			index = i;
    			break;
    		}
    	}
    	
    	if(the_same == true) {
    		System.out.println("SAME!!!!!");
    		return chromosomeArray.get(index).numOfExercise;
    	}
    	else {
    		int[][] availableDistribution = new int[parkNum][];
    		int[][] ff_area = new int[parkNum][];
    		int[][][] exerciseLocation = new int[manNum][][];
    		int[] currentElders = new int[manNum];
    		int tempMax = 0;
    		Triplet<Double,Integer,Integer> pairPreference;
    		ArrayList< ArrayList< Triplet< Double, Integer,Integer > > > vectorPreference = null;
    	
    		for(int i=0;i<manNum;i++) {
    			currentElders[i] = file.d[i];
            	exerciseLocation[i] = new int[parkNum][];
            	for(int j = 0; j< parkNum; j++){
                	exerciseLocation[i][j] = new int[facilityNum];
                	for (int k = 0; k < facilityNum; k++){
                    	exerciseLocation[i][j][k] = 0;
                	}
            	}
    		}   
    		
        	for (int i = 0; i< parkNum; i++) {
                ff_area[i] = new int[facilityNum];
                availableDistribution[i] = new int[facilityNum];
                for (int j = 0; j < facilityNum; j++){
                    ff_area[i][j]=0;
                }
            }
        	
        	for(int iteration=0;iteration<costIteration;iteration++) {
                tempMax=0;
                int tempPeople=0;
                ff_area = randomZ(file.q, file.T, num_of_chromosome);
                // .__    .   .     ___
                // |  \   |   |    /
                // |__/   |   |   |    __
                // |  \   |   |   |     /
                // |__/    \_/     \___/ 
                while(cost(file.f, file.c, ff_area, num_of_chromosome) > totalBudget){
                    ff_area = randomZ(file.q, file.T, num_of_chromosome);
                }
                for (int i = 0; i< parkNum; i++) {
                    for (int j = 0; j < facilityNum; j++){
                        availableDistribution[i][j]=(int) (ff_area[i][j]*(file.k[j]));
                    }
                }
                
                while(true) {
            		boolean negative = true;
            		for(int i=0;i<manNum;i++) {
            			for(int j = 0; j < parkNum; j++){
                        	ArrayList<Triplet<Double, Integer, Integer>> temp = null;
                            for(int k = 0; k < facilityNum; k++){
                            	pairPreference = Triplet.with(file.p[i][j][k],j,k);
                            	temp.add(pairPreference);
                            }
                        	vectorPreference.set(j, temp);
                        }
            			
            			PriorityQueue<Triplet<Double,Integer,Integer>> queuePreference = new PriorityQueue<Triplet<Double,Integer,Integer>>();
            			
            			for(int j = 0; j < parkNum; j++){
                            for(int k = 0; k < facilityNum; k++){
                                queuePreference.add(vectorPreference.get(j).get(k));
                            }
                        }
            			
            			while(availableDistribution[queuePreference.peek().getValue1()][queuePreference.peek().getValue2()]==0 && !queuePreference.isEmpty()) {
            				file.p[i][queuePreference.peek().getValue1()][queuePreference.peek().getValue2()] = -1;
            				queuePreference.poll();
            			}
            			
            			int index1 = queuePreference.peek().getValue1();
            			int index2 = queuePreference.peek().getValue2();
            			if(currentElders[i] <= availableDistribution[index1][index2]) {
            				exerciseLocation[i][index1][index2] += currentElders[i];
            				availableDistribution[index1][index2] -= currentElders[i];
            				currentElders[i] = 0;
            				file.p[i][index1][index2] = -1;
            			}else {
            				currentElders[i] -= availableDistribution[index1][index2];
            				exerciseLocation[i][index1][index2] += availableDistribution[index1][index2];
            				availableDistribution[index1][index2] = 0;
            				file.p[i][index1][index2] = -1;
            			}
            		}
            		
            		for(int i=0;i<manNum;i++) {
            			for(int j = 0; j < parkNum; j++){
                            for(int k = 0; k < facilityNum; k++){
                                if(file.p[i][j][k] != -1){
                                    negative = false;
                                }
                            }
                        }
            		}
            		
            		if(negative == true){
                        System.out.print("NNNNNNN");
                        break;
                    }
            	}
                
                for(int i = 0; i < manNum; i++){
                    for(int j = 0; j < parkNum; j++){
                        for(int k = 0; k < facilityNum; k++){
                            tempPeople += exerciseLocation[i][j][k];
                        }
                    }
                }
                
                if (tempPeople>=tempMax){
                    System.out.print("~~~~~~~~~~~");
                    tempMax = tempPeople;
                    optimal_facility_area = ff_area;   
                }
        	}
        	
        	chromosome tempChromosome = new chromosome();
            tempChromosome.numOfExercise = tempMax;


            for(int i = 0; i < parkNum; i++){
                tempChromosome.scale[i] = kid_pool[num_of_chromosome][i];
            }
            
            tempChromosome.locationFacility = new int[parkNum][];
            for(int i = 0; i < parkNum; i++){
                tempChromosome.locationFacility[i] = new int[facilityNum];
            }
            for(int i = 0; i < parkNum; i++){

                for(int j = 0; j < facilityNum; j++){
                    tempChromosome.locationFacility[i][j] = optimal_facility_area[i][j];
                }
                System.out.println();
            }

            chromosomeArray.add(tempChromosome);
            return tempMax;
    	}
    }
    
 
    
    
    public void selection() throws IOException{
    	
    	ArrayList < Pair <Double, Integer> > vector_result = null;
        Pair < Double, Integer > result = null;
//        System.out.println("TRY1");
        for(int i = 0; i<poolLength*2; i++){
            result.setAt0(fitness(i));
            result.setAt1(i);
            vector_result.add(result);
            
        }
//        System.out.println("TRY2");
        
        PriorityQueue < Pair <Double, Integer> > select = null;

        for(int i = 0; i < poolLength*2; i++){
            select.add(vector_result.get(i));
        }
//        System.out.println("TRY3");

        for(int i = 0; i < poolLength; i++){
            parent_pool[i] = kid_pool[select.peek().getValue1()];
            select.poll(); 
        }
//        System.out.println("TRY4");
//        sort(chromosomeArray.begin(), chromosomeArray.end(), comparison);
        Collections.sort(chromosomeArray,new Comparator<chromosome>() {
        	public int compare(chromosome a, chromosome b) {
        		return a.numOfExercise - b.numOfExercise;
        	}
        });
//        System.out.println("TRY5");
        
        for(int i = 0; i < poolLength; i++){
            chromosomeArray.remove(chromosomeArray.size()-1);
        }

        System.out.println("Selection!~!\n");
    	
    }
    
    
    
    
    
    public void mutation(){
    	Random rand = new Random();
    	final int mutationPosibility = 100;

        for (int i = 0; i < poolLength; i++){
            int r = rand.nextInt(mutationPosibility);
            if(r == 0) {
                int pos = rand.nextInt(parkNum);
                int changedNum = rand.nextInt(6);
                kid_pool[i][pos] = changedNum;
            }
        }
    }
    
    
    public static void display_parent(){
    	System.out.println("\nparent_pool~~~~~~~~~~~~\n");
    	for(int i=0;i<20;i++) {
    		for(int j=0;j<file.PARK;j++){
    			System.out.print(parent_pool[i][j]+" ");
    		}
    		System.out.println();
		}
    }
    
    public void display_kid(){
    	System.out.println("\nkid_pool~~~~~~~~~~~~\n");
    	for(int i=0;i<2*poolLength;i++) {
    		for(int j=0;j<parkNum;j++){
    			System.out.print(kid_pool[i][j]+" ");
    		}
    		System.out.println();
		}
    }
    public void display_S(){
    	System.out.println("\nS~~~~~~~~~~~~\n");
    	for(int i=0;i<parkNum;i++) {
    		for(int j=0;j<file.SCALE;j++){
    			System.out.print(file.S[i][j]+" ");
    		}
    		System.out.println();
		}
    }
    
    public void display_cost(){
    	System.out.println("\ntotalCost~~~~~~~~~~~\n");
    	System.out.println("$");
    	System.out.print(this.totalCost);
    }
    
    public void GA() throws IOException {
		
        original_gene(file.S);
        display_parent();
//        crossover();
//        mutation();
//        selection();
        

        for(int iteration=0;iteration<10;iteration++) {
        	System.out.println("iteration: "+iteration);
            crossover();
            mutation();
            System.out.println("yoooo\n");
            selection();
        }
        System.out.println("Finish!");

    }
    
    

}

