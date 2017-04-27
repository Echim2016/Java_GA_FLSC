package FLSC;
import java.io.*;



public class file {
	
	public static int MAN = ReadFile.oldman;;
	public static int PARK = ReadFile.park;;
	public static int FACILITY = ReadFile.facility;;
	public static int SCALE = ReadFile.scale;
	public static int budget = ReadFile.budget;
	public static int[] k ;
	public static int[] c ;
	public static int[] d  ;
	public static int[][] S;
	public static int[][] T ;
	public static int[][] f ;
	public static int[][] q ;
	public static double[][][] p ;

	public static void main(String args[]) throws IOException {
		
		k = file1Input("ga/k.csv",FACILITY);
		c = file1Input("ga/c.csv",FACILITY);
		d = file1Input("ga/d.csv",MAN);
		S = file2Input("ga/S.csv",PARK,SCALE);
		T = file2Input("ga/T.csv",PARK,FACILITY);
		f = file2Input("ga/f.csv",PARK,SCALE);
		q = file2Input("ga/q.csv",PARK,SCALE);
		p = file3Input("ga/p.csv",MAN,PARK,FACILITY);
		
//		for(int i=0;i<MAN;i++) {
//			for(int j=0;j<PARK;j++) {
//				for(int k=0;k<FACILITY;k++) {
//					System.out.println(p[i][j][k]);
//				}
//			}
//		}
	//--------------------------------------------//	
//		FileReader in = null;
//		String line;
//		try {
//			in = new FileReader(args[8]);
//			BufferedReader bufferedReader = new BufferedReader(in);
//			line = bufferedReader.readLine();
//		}finally {
//			if(in != null) {
//				in.close();
//			}
//		}
//		String[] arr = line.split(",");
//		MAN = Integer.parseInt(arr[0]);
//		PARK = Integer.parseInt(arr[1]);
//		FACILITY = Integer.parseInt(arr[2]);
//		SCALE = Integer.parseInt(arr[3]);
//		budget = Integer.parseInt(arr[4])
	}
	

//--------------------------Reading the file----------------------------------//
	
	public static int[] file1Input(String filePath,int x) throws IOException {
		FileReader in = null;
		String line;
		int[] intArr = new int[x];

		try {
			in = new FileReader(filePath);
			BufferedReader bufferedReader = new BufferedReader(in);
			for(int i=0;i<x;i++) {
	   			line = bufferedReader.readLine(); 
	   			intArr[i] = Integer.parseInt(line);
			}
		}finally {
			if(in != null) {
				in.close();
			}
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
		double[] numArr = new double[x];
		for(int i=0;i<x;i++) {
			numArr[i] = Double.parseDouble(arr[i]);
//			System.out.println(arr[i]);
		}
		return numArr;
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
	
//----------------------------Getter-------------------------------
	public static int[][] getS() throws IOException{
		S = file2Input("ga/S_1.csv",PARK,SCALE);
		return S;
	}
	
	public static int[] getD() throws IOException {
		d = file1Input("ga/d_1.csv",MAN);
		return d;
	}
	
	public static int[][] getQ() throws IOException {
		q = file2Input("ga/q_1.csv",PARK,SCALE);
		return q;
	}
	
	public static int[][] getT() throws IOException {
		T = file2Input("ga/T_1.csv",PARK,FACILITY);
		return T;
	}
	
	public static int[] getC() throws IOException {
		c = file1Input("ga/c_1.csv",FACILITY);
		return c;
	}
	
	public static int[][] getF() throws IOException {
		f = file2Input("ga/f_1.csv",PARK,SCALE);
		return f;
	}
	
//	public static double[] getK() throws IOException {
//		k = file1Input_double("ga/k_1.csv",FACILITY);
//		return k;
//	}
	
	public static double[][][] getP() throws IOException {
		p = file3Input("ga/p.csv",MAN,PARK,FACILITY);
		return p;
	}
}

