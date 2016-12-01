import java.util.ArrayList;

public class CRTLogicWithSteps {
	public boolean areCoprime(int a, int b){
		return a%b == 0 || b%a == 0 ? false:true;
	}
	
	public boolean pairwise(int[][] pair, ArrayList<String> list){
		for(int i = 0; i < pair.length; i++){
			for(int j = i+1; j < pair.length; j++){
				String line = "";
				line += ("\t" + pair[i][1] + " and " + pair[j][1]);
				if(!areCoprime(pair[i][1], pair[j][1])){
					line += (" are NOT relatively prime.");
					list.add(line);
					list.add("\tStop Checking.");
					return false;
				}else{
					line += (" are relatively prime.");
					list.add(line);
				}
			}
		}
		return true;
	}
	
	public boolean isNegative(int a){
		return a < 0 ? true: false;
	}
	
	public boolean hasNegativesInB(int[][] pair){
		for(int i = 0; i < pair.length; i++){
			if(isNegative(pair[i][0])){
				return true;
			}
		}
		return false;
	}
	
	public boolean hasNonPositiveIntegersInM(int[][] pair){
		for(int i = 0; i < pair.length; i++){
			if(pair[i][1] < 1){
				return true;
			}
		}
		return false;
	}
	
	public int[][] clean(int[][] pair){
		for(int i = 0; i < pair.length; i++){
			if(isNegative(pair[i][0])){
				pair[i][0] += pair[i][1] * ((Math.abs(pair[i][0]) / pair[i][1]) + 1);
			}
		}
		return pair;
	}
	
	public ArrayList<String>[] solve(int[][] pair){
		ArrayList<String>[] step = (ArrayList<String>[])new ArrayList[7];
		for(int i = 0; i < step.length; i++){
			step[i] = new ArrayList<String>();
		}
		
		step[0].add("GIVEN:");
		for(int i = 0; i < pair.length; i++){
			step[0].add("\tx = " + pair[i][0] + " mod " + pair[i][1]);
		}
		if(hasNonPositiveIntegersInM(pair)){
			step[1].add("\tANSWER: INVALID. All m [at x = b mod m] should be positive integers");
			step[2].add("================================================================");
		}else{
			if(hasNegativesInB(pair)){
				step[0].add("\nSince there is a detected input has a negative b [at x = b mod m], cleaning is done");
				pair = clean(pair);
				step[0].add("\nAFTER CLEANING:");
				for(int i = 0; i < pair.length; i++){
					step[0].add("\tx = " + pair[i][0] + " mod " + pair[i][1]);
				}
			}
			
			step[1].add("Step 1: Check if relatively prime.");
			if(!pairwise(pair, step[1])){
				step[1].add("\tANSWER: not pairwise co-prime");
				step[2].add("================================================================");
			}else{
				step[1].add("\tAll pairs are relatively prime numbers");
				long answer = 0;
				long inverse[] = new long[pair.length];
				
				//Step 2a: Get the inverse
				step[2].add("Step 2: Get the M[i]");
				String toAddStep2 = "\tM = ";
				long product = 1;			
				for(int i = 0; i < inverse.length; i++){
					
					product *= pair[i][1];  
					toAddStep2 += (pair[i][1]);
					if(i != inverse.length-1){
						toAddStep2 += (" * ");
					}else{
						toAddStep2 += (" = " + product);
						step[2].add(toAddStep2);
					}
				}
				
				step[2].add("\tM[i] = M / m[i]");
				
				for(int i = 0; i < inverse.length; i++){
					inverse[i] = product / pair[i][1];
					step[2].add("\tM[" + (i+1) + "] = " + product + " / " + pair[i][1] + " = " + inverse[i]);
				}
				
				//Step 3: Look for the value of x such that it will satisfy the condition "x = inverse % n"
				step[3].add("Step 3: Get the M'[i]");
				String toAddStep3 = "";
				long modulo[] = new long[pair.length];
				for(int i = 0; i < pair.length; i++){
					toAddStep3 = "";
					long mod = 1;
					while((mod*inverse[i]) % pair[i][1] != 1){
						mod++;
					}
					modulo[i] = mod;
					toAddStep3 += ("\t" + "1" + " = " + "M'[" + (i+1) + "] * " + (inverse[i]) + " mod " + pair[i][1]);
					if(inverse[i] > pair[i][1]){
						toAddStep3 += (" --> " + "1" + " = " + "M'[" + (i+1) + "] * " + (inverse[i]%pair[i][1]) + " mod " + pair[i][1]);
					}else{
						toAddStep3 += ("\n");
					}
					toAddStep3 += ("\n");
					toAddStep3 += ("\t\t" + "1" + " = " + ((inverse[i]%pair[i][1]) * modulo[i]) + " mod " + pair[i][1]);
					
					toAddStep3 += ("\n");
					toAddStep3 += ("\t\tsince " + "M'[" + (i+1) + "] * " + (inverse[i]%pair[i][1]) + " = " + ((inverse[i]%pair[i][1]) * modulo[i]) + "; ");
					
					toAddStep3 += ("\n");
					toAddStep3 += ("\t\tM'[" + (i+1) + "] = " + modulo[i]);
					
					step[3].add(toAddStep3);
				}
				
				//Step 4: compute for the answer
				step[4].add("Step 4: Get the summation of the product of r[i], M[i], & M'[i]");
				String toAddStep4 = "\t";
				for(int i = 0; i < pair.length; i++){
					answer += (pair[i][0] * inverse[i] * modulo[i]);
					toAddStep4 += ("(" + pair[i][0] + " * " + inverse[i] + " * " + modulo[i] + ")");
					if(i != inverse.length-1){
						toAddStep4 += (" + ");
					}else{
						toAddStep4 += (" = " + answer);
					}
				}
				step[4].add(toAddStep4);
				
				//Step 5: Get the smallest non negative value congruent to computed X
				step[5].add("Step 5: Get the smallest non negative value congruent to computed X");
				if(answer > product){
					step[5].add("\tx = " + answer + " % " + product);
				}
				step[5].add("ANSWER: \tx = " + (answer%product));
				step[6].add("================================================================");
				return step;
			}
		}
		//Step 2b: Return value denoting that given set isn't pairwise.
		return step;
	}
}
