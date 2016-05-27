import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.lang.System;
public class analyze {
	public static void main(String[] args) {
		File file;
		if(args.length>0)
			file=getFile(args[0]);
		else
			file=getFile(".");
		System.out.println(file.getPath());
		List<String> fileContent=getString(file);
		for(String line:fileContent)
			System.out.print(line);
		
		/*String current = new File(".").getCanonicalPath();
		System.out.println(current);
		*/
	}
	public static String askForPath(){
		Scanner scan=new Scanner(System.in);
		System.out.println("Please provide an absolute or relative path to your text file");
		return scan.next();
	}
	public static File getFile(String arg){
		//reliably handle file input
		
		File file=null;
		try{
			file=new File(arg);
		}
		catch (Exception e)
		{
			System.out.println(e);
			
		}
		finally{
			if (file.isDirectory()){
				System.out.println("You pointed to a directory, only text files allowed!");
				file=new File(askForPath());
			}
		}
		return file;
	}
	public static List<String> getString(File file){
		List<String> result=new ArrayList<String>();
		try{
		result=Files.readAllLines(file.toPath());
		}
		catch(IOException e) {
			System.err.println("I/O Error while reading file contents"+e.getMessage());
			System.err.println("The current working directory is: "+System.getProperty("user.dir"));
		}
		return result;
	}
	
	public static void initAnal(){
		
	}
}
