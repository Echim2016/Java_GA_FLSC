package file;

public class chromosome {
	
	public int[] scale;
	public int[][] locationFacility;
	public int numOfExercise;

	public chromosome(){
		scale = new int [file.FACILITY];
		locationFacility = new int [file.PARK][];
	        for(int i = 0; i < file.PARK; i++){
	            scale[i] = 1;
	            locationFacility[i] = new int[file.FACILITY];
	            for(int j = 0; j< file.FACILITY; j++){
	                locationFacility[i][j] = 1;
	            }
	        }
	        numOfExercise = 0;
	}



}
