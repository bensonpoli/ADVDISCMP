
public class CRTLogicWithSteps {
	public boolean areCoprime(int a, int b){
		return a%b == 0 || b%a == 0 ? false:true;
	}
	
	public boolean pairwise(int[][] pair){
		for(int i = 0; i < pair.length; i++){
			for(int j = i+1; j < pair.length; j++){
				System.out.print("\t" + pair[i][1] + " and " + pair[j][1]);
				if(!areCoprime(pair[i][1], pair[j][1])){
					System.out.println(" are NOT relatively prime.");
					System.out.println("\tStop Checking.");
					return false;
				}else{
					System.out.println(" are relatively prime.");
				}
			}
		}
		return true;
	}
	
	public boolean isNegative(int a){
		return a < 0 ? true: false;
	}
	
	public boolean hasNegativesInM(int[][] pair){
		for(int i = 0; i < pair.length; i++){
			if(isNegative(pair[i][0])){
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
	
	public String solve(int[][] pair){
		System.out.println("GIVEN:");
		for(int i = 0; i < pair.length; i++){
			System.out.println("\tx = " + pair[i][0] + " mod " + pair[i][1]);
		}
		
		if(hasNegativesInM(pair)){
			System.out.println("\nSince there is a detected input has a negative m [at x = m mod b], cleaning is done");
			pair = clean(pair);
			System.out.println("\nAFTER CLEANING:");
			for(int i = 0; i < pair.length; i++){
				System.out.println("\tx = " + pair[i][0] + " mod " + pair[i][1]);
			}
		}
		
		System.out.println("\nStep 1:");
		if(pairwise(pair)){
			System.out.println("\tAll pairs are relatively prime numbers");
			long answer = 0;
			long inverse[] = new long[pair.length];
			
			//Step 2a: Get the inverse
			System.out.println("\nStep 2:");
			System.out.print("\t");
			long product = 1;
			for(int i = 0; i < inverse.length; i++){
				product *= pair[i][1];  
				System.out.print(pair[i][1]);
				if(i != inverse.length-1){
					System.out.print(" * ");
				}else{
					System.out.println(" = " + product);
				}
			}
			
			for(int i = 0; i < inverse.length; i++){
				inverse[i] = product / pair[i][1];
				System.out.println("\tz[" + (i+1) + "] = " + product + " / " + pair[i][1] + " = " + inverse[i]);
			}
			
			//Step 3: Look for the value of x such that it will satisfy the condition "x = inverse % n"
			System.out.println("\nStep 3:");
			long modulo[] = new long[pair.length];
			for(int i = 0; i < pair.length; i++){
				long mod = 1;
				while((mod*inverse[i]) % pair[i][1] != 1){
					mod++;
				}
				modulo[i] = mod;
				System.out.print("\t" + "1" + " = " + "x[" + (i+1) + "] * " + (inverse[i]) + " mod " + pair[i][1]);
				if(inverse[i] > pair[i][1]){
					System.out.println(" --> " + "1" + " = " + "x[" + (i+1) + "] * " + (inverse[i]%pair[i][1]) + " mod " + pair[i][1]);
				}else{
					System.out.println();
				}
				System.out.println("\t\t" + "1" + " = " + ((inverse[i]%pair[i][1]) * modulo[i]) + " mod " + pair[i][1]);
				System.out.println("\t\tsince " + "x[" + (i+1) + "] * " + (inverse[i]%pair[i][1]) + " = " + ((inverse[i]%pair[i][1]) * modulo[i]) + "; "
						+ "x[" + (i+1) + "] = " + modulo[i]);
			}
			
			//Step 4: compute for the answer
			System.out.println("\nStep 4:");
			System.out.print("\t");
			for(int i = 0; i < pair.length; i++){
				answer += (pair[i][0] * inverse[i] * modulo[i]);
				System.out.print("(" + pair[i][0] + " * " + inverse[i] + " * " + modulo[i] + ")");
				if(i != inverse.length-1){
					System.out.print(" + ");
				}else{
					System.out.println(" = " + answer % product);
				}
			}
			return "" + answer % product;
		}
		//Step 2b: Return value denoting that given set isn't pairwise.
		return "not pairwise co-prime";
	}
}
