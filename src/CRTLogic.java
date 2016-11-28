
public class CRTLogic {
	public boolean areCoprime(int a, int b){
		return a%b == 0 || b%a == 0 ? false:true;
	}
	
	public boolean pairwise(int[][] pair){
		for(int i = 0; i < pair.length; i++){
			for(int j = i+1; j < pair.length; j++){
				if(!areCoprime(pair[i][1], pair[j][1])){
					return false;
				}
			}
		}
		return true;
	}

	
	public long solve(int[][] pair){
		if(pairwise(pair)){
			long answer = 0;
			long inverse[] = new long[pair.length];
			
			//Step 1: Get the inverse
			long product = 1;
			for(int i = 0; i < inverse.length; i++){
				product *= pair[i][1];  
			}
			for(int i = 0; i < inverse.length; i++){
				inverse[i] = product / pair[i][1];
			}
			
			//Step 2: Look for the value of x such that it will satisfy the condition "x = inverse % n"
			long modulo[] = new long[pair.length];
			for(int i = 0; i < pair.length; i++){
				long mod = 1;
				while((mod*inverse[i]) % pair[i][1] != 1){
					mod++;
				}
				modulo[i] = mod;
			}
			
			//Step 3: compute for the answer
			for(int i = 0; i < pair.length; i++){
				answer += (pair[i][0] * inverse[i] * modulo[i]);
			}
			return answer % product;
		}
		return -1;
	}
}
