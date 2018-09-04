import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class parser {
	
	private static String line = null;
	private static String[] headings = null;
	
	public static void main(String[] args) throws IOException{
		String fileName = "C:\\Users\\Sravan\\Downloads\\delta_export.tsv";
		String writeFile = "C:\\Users\\Sravan\\Downloads\\Delta Files\\MissingItems.xml";
		String tag = "Item";
		
		File xmlFile = new File(writeFile);
		xmlFile.delete();
		xmlFile.createNewFile();
		
		FileReader fr = new FileReader(fileName);
		FileWriter fw = new FileWriter(xmlFile.getAbsoluteFile());
		
		BufferedReader br = new BufferedReader(fr);
		BufferedWriter bw = new BufferedWriter(fw);
		
		boolean first = true;
		
		System.out.println("Let's Begin");
		int i =0;
		while( (line = br.readLine()) != null ){
			i++;
			if(first){
				first = false;
				headings = line.split("~~");
				bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<TCImport>\n");
				System.out.println("Started Writing");
			} else {
				String[] result = line.split("~~");
				
				String itemString = "<"+tag+" ";
				for(int i1=0;i1<headings.length;i1++){
					try{
					 itemString += headings[i1] +"=\""+ result[i1] +"\" ";
					} catch(Exception e){
						System.out.println(i);
					}
				}
				itemString += " ></" + tag + ">\n";
				bw.write(itemString);
			}
		}
		
		bw.write("</TCImport>");
		System.out.println("Done");
		try {
			if(bw != null){
				bw.close();
				fw.close();
				fr.close();
				br.close();
			}
		} catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
}
