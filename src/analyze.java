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
	public static void main(String[] args) {
		Date start,end;
		scan=new Scanner(System.in);
		File file;
		if(args.length>0)
			file=getFile(args[0]);
		else
			file=getFile();
		System.out.println("Working on file: "+file.getPath());
		List<String> fileContent=getString(file);
		//create objects below
		System.out.print("\nReading file...");
		start=new Date();
		String content="";
		for(String line: fileContent)
			content+=line;
		Text text=new Text(content);
		end=new Date();
		
		System.out.print("Done! Elapased time:" +(end.getTime()-start.getTime())+" ms\n");
		Analyzer analysis=new Analyzer(text);
		analysis.run();
		for(Sentence sent:text.getSentences()){
			for(Word word:sent.getWords()){
				System.out.println(word.getContent()+" ");
			}
			System.out.print(".");
		}
		System.out.println("\nWord Count: "+analysis.getWordTotal());
		System.out.println("Avg word length: "+analysis.getWordLenAvg());
		
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
	
	public static void initAnal(){
		
	}
}
