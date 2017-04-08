package file;
import java.io.*;



public class file {
	
	public static int MAN = 5;
	public static int PARK = 5;
	public static int FACILITY = 5;
	public static int SCALE = 5;

	public static void main(String args[]) throws IOException {

		double[] k = file1Input_double("ga/k_1.csv",FACILITY);
		int[] c = file1Input("ga/c_1.csv",FACILITY);
		int[] d = file1Input("ga/d_1.csv",MAN);
		int[][] S = file2Input("ga/S_1.csv",PARK,SCALE);
		int[][] T = file2Input("ga/T_1.csv",PARK,FACILITY);
		int[][] f = file2Input("ga/f_1.csv",PARK,SCALE);
		int[][] q = file2Input("ga/q_1.csv",PARK,SCALE);
		double[][][] p = file3Input("ga/p_1.csv",MAN,PARK,FACILITY);
		//print3D(p,MAN,PARK,FACILITY);
	}

//--------------------------Reading the file----------------------------------//
	public static int[] file1Input(String filePath,int x) throws IOException {
		FileReader in = null;
		String line;

		try {
			in = new FileReader(filePath);
			BufferedReader bufferedReader = new BufferedReader(in);
   			line = bufferedReader.readLine(); 
		}finally {
			if(in != null) {
				in.close();
			}
		}
		String[] arr = line.split(",");
		int[] intArr = new int[x];
		for(int i=0;i<x;i++) {
			intArr[i] = Integer.parseInt(arr[i]);
		}
		return intArr;
	}

	public static double[] file1Input_double(String filePath,int x) throws IOException {
		FileReader in = null;
		String line;

		try {
			in = new FileReader(filePath);
			BufferedReader bufferedReader = new BufferedReader(in);
   			line = bufferedReader.readLine(); 
		}finally {
			if(in != null) {
				in.close();
			}
		}
		String[] arr = line.split(",");
		double[] intArr = new double[x];
		for(int i=0;i<x;i++) {
			intArr[i] = Double.parseDouble(arr[i]);
		}
		return intArr;
	}

	public static int[][] file2Input(String filePath,int y,int x) throws IOException {
		FileReader in = null;
		String line;
		int[][] matrix = new int[y][];
		try {
			in = new FileReader(filePath);
			BufferedReader bufferedReader = new BufferedReader(in);
			for(int i=0;i<y;i++) {
				line = bufferedReader.readLine();
				String[] arr = line.split(",");
				matrix[i] = new int[x];
				for(int j=0;j<x;j++) {
					matrix[i][j] = Integer.parseInt(arr[j]);
				}
			}
		}finally {
			if(in != null) {
				in.close();
			}
		}
		return matrix;
	}

	public static double[][][] file3Input(String filePath,int z,int y,int x) throws IOException {
		FileReader in = null;
		String line;
		double[][][] matrix = new double[z][][];
		try {
			in = new FileReader(filePath);
			BufferedReader bufferedReader = new BufferedReader(in);

			for(int i=0;i<z;i++) {
				line = bufferedReader.readLine();
				matrix[i] = new double[y][];
				for(int j=0;j<y;j++) {
					line = bufferedReader.readLine();
					String[] arr = line.split(",");
					matrix[i][j] = new double[x];
					for(int k=0;k<x;k++) {
						matrix[i][j][k] = Double.parseDouble(arr[k]);
					}
				}
			}
		}finally {
			if(in != null) {
				in.close();
			}
		}
		return matrix;
	}

//-----------------------------Printing----------------------------------------//
	public static void print1D(int[] arr,int x) {
		for(int i=0;i<x;i++) {
			System.out.println(arr[i]);
		}
	}

	public static void print2D(int[][] arr,int y,int x) {
		for(int i=0;i<y;i++) {
			for(int j=0;j<x;j++) {
				System.out.println(arr[i][j]);
			}
		}
	}

	public static void print3D(double[][][] arr,int z,int y,int x) {
		for(int i=0;i<z;i++) {
			for(int j=0;j<y;j++) {
				for(int k=0;k<x;k++) {
					System.out.println(arr[i][j][k]);
				}
			}
		}
	}
}
