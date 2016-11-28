import static org.junit.Assert.*;

import org.junit.Test;

public class CRTChecker {

	@Test
	public void inputExample1() {
		CRTLogic solver = new CRTLogic();
		int[][] toSolve = {{2,3},{3,5},{2,7}};
		long answer = solver.solve(toSolve);
		System.out.println(answer);
		assertTrue(answer == 23);
	}
	
	@Test
	public void inputExample2() {
		CRTLogic solver = new CRTLogic();
		int[][] toSolve = {{1,5},{2,6},{3,7},{4,11}};
		long answer = solver.solve(toSolve);
		System.out.println(answer);
		assertTrue(answer == 1676);
	}
	
	@Test
	public void inputExample3() {
		CRTLogic solver = new CRTLogic();
		int[][] toSolve = {{1,2},{2,3},{3,4}};
		long answer = solver.solve(toSolve);
		System.out.println(answer);
		assertTrue(answer == -1);
	}

}
