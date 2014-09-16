/**
 * 
 */
package edu.buffalo.cse.irf14;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;

import edu.buffalo.cse.irf14.document.Document;
import edu.buffalo.cse.irf14.document.FieldNames;
import edu.buffalo.cse.irf14.document.Parser;
import edu.buffalo.cse.irf14.document.ParserException;
import edu.buffalo.cse.irf14.index.IndexWriter;
import edu.buffalo.cse.irf14.index.IndexerException;

/**
 * @author nikhillo
 *
 */
public class Runner {

	/**
	 * 
	 */
	public Runner() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
//		String ipDir = args[0];
//		String indexDir = args[1];
		//more? idk!
		long startTime = System.currentTimeMillis();
		String ipDir="C:/Users/Festy/Desktop/IR Slides/training";
		PrintWriter writer = new  PrintWriter("C:/Users/Festy/Desktop/IR Slides/result.txt");
		int count=0;
		File ipDirectory = new File(ipDir);
		String[] catDirectories = ipDirectory.list();
		
		String[] files;
		File dir;
		
		Document d = null;
//		IndexWriter writer = new IndexWriter(indexDir);
		
		for (String cat : catDirectories) {
			dir = new File(ipDir+ File.separator+ cat);
			files = dir.list();
			
			if (files == null)
				continue;
			
			for (String f : files) {
				try {
					d = Parser.parse(dir.getAbsolutePath() + File.separator +f);
				writer.print(Arrays.toString(d.getField(FieldNames.FILEID)));
				writer.print(Arrays.toString(d.getField(FieldNames.CATEGORY)));
				writer.print(Arrays.toString(d.getField(FieldNames.TITLE)));
				writer.print(Arrays.toString(d.getField(FieldNames.PLACE)));
				writer.print(Arrays.toString(d.getField(FieldNames.NEWSDATE)));
				writer.print(Arrays.toString(d.getField(FieldNames.AUTHOR)));
				writer.print(Arrays.toString(d.getField(FieldNames.AUTHORORG)));
				writer.println();
				count++;
//						writer.addDocument(d);
				} catch (ParserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
			}
			
		}
		
		writer.close();
		System.out.println("Files scanned: "+count);
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println("Execution time:"+(double)elapsedTime/1000+"s");
	}

}
