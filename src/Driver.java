import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Driver {
	public static void main(String[] args){
		CRTLogicWithSteps solver = new CRTLogicWithSteps();
		InputReader ir = new InputReader();
		ArrayList<int[][]> testCase = ir.StringToInt(ir.process(ir.read("input.txt")));
		
		for(int i = 0; i < testCase.size(); i++){
			String answer = solver.solve(testCase.get(i));
			System.out.println("\n\nANSWER: " + answer + "\n\n\n");
		}
	}
}
