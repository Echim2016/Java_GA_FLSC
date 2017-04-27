package FLSC;

import java.io.*;
import java.io.PrintWriter;
import java.io.File;


public class ReadFile{

	public static int oldman;
	public static int park;
	public static int facility;
	public static int scale = 5;
	public static int budget;

	public static void main(String []args)throws IOException {
		
		
		FileReader reader=new FileReader("test7.dat");
		BufferedReader br = new BufferedReader(reader);
		String line;

		line=br.readLine();
		oldman=Integer.parseInt(line.substring(line.lastIndexOf("Od")+2, line.length()-2));
		System.out.println(oldman);	
		
		
		line=br.readLine();
		park=Integer.parseInt(line.substring(line.lastIndexOf("Pl")+2, line.length()-2));
		System.out.println(park);
		
		
		line=br.readLine();
		facility=Integer.parseInt(line.substring(line.lastIndexOf("F")+1, line.length()-2));
		System.out.println(facility);

		line=br.readLine();
		PrintWriter pw1 = new PrintWriter(new File("ga/S.csv"));
		for(int i = 0; i<park; i++){
			line=br.readLine();
			String max_scale = line.substring(line.length()-3, line.length()-2);
			String[] sArray = new String [scale];
			String temp = " ";
			for(int j = 0; j<scale; j++){
				if(j < Integer.valueOf(max_scale)){
					sArray[j] = "1";
				}else{
					sArray[j] = "0" ;
				}
				if(j>0){
					temp = temp + "," + sArray[j];
				}else{
					temp = sArray[0];
				}

			}
			pw1.write(temp+"\n");

			//System.out.println(scale);
		}
		pw1.close();

		line=br.readLine();
		PrintWriter pw2 = new PrintWriter(new File("ga/T.csv"));
		for(int i = 0; i<park; i++){
			line=br.readLine();
			String max_facility = line.substring(line.length()-3, line.length()-2);
			String[] tArray = new String [facility];
			String temp = " ";
			for(int j = 0; j<facility; j++){
				if(j < Integer.valueOf(max_facility)){
					tArray[j] = "1";
				}else{
					tArray[j] = "0" ;
				}
				if(j>0){
					temp = temp + "," + tArray[j];
				}else{
					temp = tArray[0];
				}

			}
			pw2.write(temp+"\n");

			//System.out.println(scale);
		}
		pw2.close();


		while(true){
			line=br.readLine();
			if(line.equals("param f :")){
				PrintWriter pw = new PrintWriter(new File("ga/f.csv"));
				line=br.readLine();
				line=br.readLine();
				for(int i = 0; i<park; i++){
					line=br.readLine();
					String[] fArray = line.split("\t");

					if(fArray[1].equals(".")){
							fArray[1] = "0";
					}

					String temp = fArray[1];
					for(int j = 2; j<fArray.length; j++){
						if(fArray[j].equals(".")||fArray[j].equals(".;")) {
								fArray[j] = "0";
						}
						temp = temp+ "," + fArray[j];
						
					}
					if(i == park -1){
						if(!fArray[fArray.length-1].equals("0")){
							temp = temp.substring(0, temp.length()-1);
						}
					}
					temp += "\n";
					
					pw.write(temp);
				}
				
				pw.close();
				break;

			}
		}

		while(true){
			line=br.readLine();
			if(line.equals("param q :")){
				PrintWriter pw = new PrintWriter(new File("ga/q.csv"));
				line=br.readLine();
				line=br.readLine();
				for(int i = 0; i<park; i++){
					line=br.readLine();
					String[] qArray = line.split("\t");

					if(qArray[1].equals(".")){
							qArray[1] = "0";
					}

					String temp = qArray[1];
					for(int j = 2; j<qArray.length; j++){
						if(qArray[j].equals(".")||qArray[j].equals(".;")) {
								qArray[j] = "0";
						}
						temp = temp+ "," + qArray[j];
						
					}
					if(i == park -1){
						if(!qArray[qArray.length-1].equals("0")){
							temp = temp.substring(0, temp.length()-1);
						}
					}
					temp += "\n";
					
					pw.write(temp);
				}
				
				pw.close();
				break;

			}
		}

		while(true){
			line=br.readLine();
			if(line.equals("param c:=")){
				PrintWriter pw = new PrintWriter(new File("ga/c.csv"));
				line=br.readLine();
				for(int i = 0; i<facility; i++){
					line=br.readLine();
					String[] cArray = line.split("\t");
					String temp = cArray[1];
					for(int j = 2; j<cArray.length; j++){
						temp = temp+ "," + cArray[j];
						//System.out.println(fArray[i]);
					}
					if(i == facility-1){
						temp = temp.substring(0, temp.length()-1);
					}
					temp += "\n";
					
					pw.write(temp);
				}
				
				pw.close();
				break;

			}
		}

		line=br.readLine();
		line=br.readLine();

		budget = Integer.valueOf(line.substring(9, line.length()-1));
		System.out.println(budget);
				
		while(true){
			line=br.readLine();
			if(line.equals("param k:=")){
				PrintWriter pw = new PrintWriter(new File("ga/k.csv"));
				line=br.readLine();
				for(int i = 0; i<facility; i++){
					line=br.readLine();
					String[] kArray = line.split("\t");
					String temp = kArray[1];
					for(int j = 2; j<kArray.length; j++){
						temp = temp+ "," + kArray[j];
						
					}
					if(i == facility-1){
						temp = temp.substring(0, temp.length()-1);
					}
					temp += "\n";
					
					pw.write(temp);
				}
				
				pw.close();
				break;

			}
		}
		while(true){
			line=br.readLine();
			if(line.equals("param d:=")){
				PrintWriter pw = new PrintWriter(new File("ga/d.csv"));
				for(int i = 0; i<oldman; i++){
					line=br.readLine();
					String[] dArray = line.split("\t");
					String temp = dArray[1];
					for(int j = 2; j<dArray.length; j++){
						temp = temp+ "," + dArray[j];
					}
					if(i == oldman-1){
						temp = temp.substring(0, temp.length()-1);
					}
					temp += "\n";
					pw.write(temp);
				}
				
				pw.close();
				break;

			}
		}

		while(true){
			line=br.readLine();
			if(line.equals("param p:=")){
				PrintWriter pw = new PrintWriter(new File("ga/p.csv"));
				for(int i = 0; i<oldman; i++){
					line=br.readLine();
					line=br.readLine();
					if(i>0){
						line=br.readLine();
					}

					pw.write("\n");
					for(int j = 0; j<park; j++){
						line=br.readLine(); 
						String[] pArray = line.split("\t");
						if(pArray[3].equals(".")){
							pArray[3] = "-1";
						}
						String temp = pArray[3];
						for(int k = 4; k<pArray.length; k++){
							if(pArray[k].equals(".")||pArray[k].equals(".;")) {
								pArray[k] = "-1";
							}
							temp = temp+ "," + pArray[k];
							
						}
						if(i == oldman -1 && j == park-1){
							if(!pArray[pArray.length-1].equals("-1")){
								temp = temp.substring(0, temp.length()-1);
							}
							
						}
						temp += "\n";
						pw.write(temp);
					}
					
				}
				
				pw.close();
				break;

			}
		}	


	}
		
}