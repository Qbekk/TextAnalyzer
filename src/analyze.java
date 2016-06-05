import java.io.*;
import java.nio.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Date;
import java.lang.System;
public class analyze {
	static Scanner scan;
	static BufferedReader reader;
	static PrintWriter writer;
	public static void main(String[] args) {
		Date start,end;
		scan=new Scanner(System.in);
		File file;
		if(args.length>0)
			file=getFile(args[0]);
		else
			file=getFile();
		System.out.println("Working on file: "+file.getPath());
		System.out.print("\nReading file...");
		start=new Date();
		
		String content=getString(file);
		//create objects below
		
		Text text=new Text(content);
		end=new Date();
		
		System.out.print("Done! Elapased time:" +(end.getTime()-start.getTime())+" ms\n");
		Analyzer analysis=new Analyzer(text);
		analysis.run();
		
		displayResults(analysis);
		export(file,analysis);
		/*String current = new File(".").getCanonicalPath();
		System.out.println(current);
		*/
	}
	public static String askForPath(){
		
		System.out.println("Please provide an absolute or relative path to your text file");
		String input=scan.next();
		return input;
	}
	public static File getFile(String arg){//initial getter for args[]
		File file=null;
		try{
			file=new File(arg);
		}
		catch (Exception e)
		{
			System.out.println(e+" "+e.getMessage());
			
		}
		finally{
			if (file.isDirectory()||!file.exists()){
				System.out.println("Wrong path!(nonexistant or not a file)");
				file=getFile();
			}
		}
		return file;
	}
	public static File getFile(){//gets after first with no arg
		File file;
		boolean first=true;
		do{
			if (!first){
				System.out.println("Wrong path!(nonexistant or a directory)");
			}
			first=false;
			file=new File(askForPath());
			if (!file.exists()){
				file=new File(file.getPath()+".txt");
			}
		}while(!file.exists()||file.isDirectory());
		return file;
	}
	
	public static String getString(File file){
		String content="";
		String line="";
		try{
			reader=new BufferedReader(
				new InputStreamReader(
				new FileInputStream(file),"UTF-8"));
			while((line=reader.readLine())!=null)
				content+=line;
				reader.close();
			
			
		}
		catch(IOException e){
			System.out.println("IO exception " +e+ " occured with message "+ e.getMessage());
		}
		
		
		return content;
	}
	
	/*
	public static List<String> getString(File file){//extracts the contents of a file into a string
		List<String> result = new ArrayList<String>();
		try{
		result=Files.readAllLines(file.toPath(),java.nio.charset.StandardCharsets.UTF_8);	
		}
		catch(IOException e){
			
			System.err.println("I/O Error while reading file contents"+e.getMessage());
			//System.err.println("The current working directory is: "+System.getProperty("user.dir"));
		}
		finally{
					
		}
		return result;
	}
	*/
	public static void displayResults(Analyzer anal){
		System.out.println("\n\nFile statistics: ");
		System.out.println("Number of characters(without spaces): "+anal.getWordCharSum());
		System.out.println("Number of words: "+anal.getWordTotal());
		System.out.println("Number of sentences: "+anal.getSentenceTotal());
		System.out.println("Average sentence length: "+anal.getSentenceLenAvg());
		System.out.println("Average word length: "+anal.getWordLenAvg());
		System.out.println("Most common word length(0 if tied): "+anal.getWordDominant());
		System.out.println("Word length standard deviation "+anal.getWordLenStdDev());
		System.out.println("Words by length: ");
		int[] dist=anal.getWordDistribution();
		for(int i=0;i<dist.length-1;i++){
			if(dist[i]!=0){
				System.out.println(i+1+" letters: "+ dist[i]+" word(s)");
			}
		}
		
		
	}
	public static void export(File source,Analyzer anal){
		
		try{
		writer=new PrintWriter(
				new BufferedWriter(
					new OutputStreamWriter(
							new FileOutputStream(new File(source.getAbsolutePath()+"-stats.txt")),"UTF-8")	));
		writer.println("\n\nFile statistics: ");
		writer.println("Number of characters(without spaces): "+anal.getWordCharSum());
		writer.println("Number of words: "+anal.getWordTotal());
		writer.println("Number of sentences: "+anal.getSentenceTotal());
		writer.println("Average sentence length: "+anal.getSentenceLenAvg());
		writer.println("Average word length: "+anal.getWordLenAvg());
		writer.println("Most common word length(0 if tied): "+anal.getWordDominant());
		writer.println("Word length standard deviation "+anal.getWordLenStdDev());
		writer.println("Words by length: ");
		int[] dist=anal.getWordDistribution();
		for(int i=0;i<dist.length-1;i++){
			if(dist[i]!=0){
				writer.println(i+1+" letters: "+ dist[i]+" word(s)");
			}
		}
		
		
		}
		catch(Exception e){
			System.out.println("Exception " +e+ " occured with message "+ e.getMessage());
		}
		finally{
			writer.close();
		}
	}
}
