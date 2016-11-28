import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class InputReader {
	public String read(String directory){
		File tr;	
		BufferedReader br;
		String text = "";
		
		try{
			tr = new File(directory);
			br = new BufferedReader(new FileReader(tr));
					
			String line;
			while((line = br.readLine()) != null){
				text += line + "\n";
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return text.trim();
	}
	
	public ArrayList<String[][]> process(String input){
		
		ArrayList<String[][]> cases = new ArrayList<>();
		String[] list = input.split("\n\n");
		String[][] singleCases;
		
		for(int i = 0; i < list.length; i++){
			singleCases = new String[list[i].split("\n").length][list[i].split("\n")[0].split(" ").length];
			String[] contents = list[i].split("\n");
			//System.out.println("Birthday ko ngayon!!");
				
			for(int j = 0; j < contents.length; j++){
				singleCases[j] = contents[j].split(" ");
			}
			cases.add(singleCases);
		}
		
		return cases;
	}
	
	public ArrayList<int[][]> StringToInt(ArrayList<String[][]> cases){

		ArrayList<int[][]> newList = new ArrayList<>();
		
		for(int i = 0; i < cases.size(); i++){
			String[][] output = cases.get(i);
			int[][] list = new int[output.length][output[0].length];
			for(int j = 0; j < output.length; j++){
				for(int k = 0; k < output[j].length; k++){
					list[j][k] = Integer.parseInt(output[j][k]);
				}
			}
			newList.add(list);
		}
		
		return newList; 
	}
}
