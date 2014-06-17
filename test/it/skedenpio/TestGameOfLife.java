package it.skedenpio;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestGameOfLife 
{
	@Test
	public void TestCellAlive()
	{
		boolean[][] grid = new boolean[10][10];
		grid[3][3] = true;
		boolean result = Cell.isAlive(grid[3][3]);
		boolean expected = true;
		assertEquals(" la cella in posizione 3 3 è viva",expected,result);
	}
	
	@Test
	public void TestNeighboursAlive3()
	{ 
		boolean[][] grid = new boolean[10][10];
		int x = 2;
		int y = 2;
		grid[3][3] = true;
		grid[3][2] = true;
		grid[2][3] = true;
		int result = Cell.neighboursAlive(x,y,grid);
		int expected = 3;
		assertEquals(" la cella in posizione 3 3 è viva",expected,result);
	}
	
	@Test
	public void  TestDeadBackToLife()
	{
		boolean[][] grid = new boolean[10][10];
		int x = 2;
		int y = 2;
		grid[3][3] = true;
		grid[3][2] = true;
		grid[2][3] = true;
		boolean result = Cell.nextState(x,y,grid);
		boolean expected = true;
		assertEquals(" la cella in posizione 2 2  è morta con 3 vicini vivi quindi sarà viva",expected,result);
	}
	
	@Test
	public void  TestAlivekeepToLive()
	{
		boolean[][] grid = new boolean[10][10];
		int x = 2;
		int y = 2;
		grid[3][3] = true;
		grid[3][2] = true;
		grid[2][3] = true;
		boolean result = Cell.nextState(x,y,grid);
		boolean expected = true;
		assertEquals(" la cella in posizione 3 3 è viva con 3 vicini vivi quindi sarà viva",expected,result);
	}
	
	@Test
	public void  TestAliveDiesBecause1Neighbour()
	{
		boolean[][] grid = new boolean[10][10];
		int x = 2;
		int y = 2;
		grid[2][3] = true;
		boolean result = Cell.nextState(x,y,grid);
		boolean expected = false;
		assertEquals(" la cella in posizione 2 2 è viva con 1 vicino vivo quindi sarà morta",expected,result);
	}
	
	@Test
	public void  TestAliveDiesBecause4Neighbours()
	{
		boolean[][] grid = new boolean[10][10];
		int x = 2;
		int y = 2;
		grid[3][3] = true;
		grid[3][2] = true;
		grid[2][3] = true;
		grid[1][2] = true;
		boolean result = Cell.nextState(x,y,grid);
		boolean expected = false;
		assertEquals(" la cella in posizione 2 2 è viva con 4 vicini vivi quindi sarà morta",expected,result);
	}
	
	@Test
	public void  TestPresentGridVoidFutureGridVoid()
	{
		boolean[][] grid = new boolean[10][10];
		boolean[][] result = Cell.nextGrid(grid);
		boolean[][] expected = new boolean[10][10];
		assertArrayEquals(" la griglia futura sarà vuota come la precedente",expected,result);
	}
	
	@Test
	public void TestPresentGridNotVoidFutureGridVoid()
	{
		boolean[][] grid = new boolean[10][10];
		grid[2][2] = true;
		grid[5][5] = true;
		grid[7][2] = true;
		boolean[][] result = Cell.nextGrid(grid);
		boolean[][] expected = new boolean[10][10];
		assertArrayEquals(" la griglia presente ha solo celle isolate quindi la futura sarà vuota ",expected,result);
	}
	
	@Test
	public void TestPresentGridNotVoidFutureGridNotVoid()
	{
		boolean[][] grid = new boolean[10][10];
		grid[2][2] = true;
		grid[2][3] = true;
		grid[3][3] = true;
		grid[5][5] = true;
		grid[7][2] = true;
		boolean[][] result = Cell.nextGrid(grid);
		boolean[][] expected = new boolean[10][10];
		expected[2][2] = true;
		expected[2][3] = true;
		expected[3][3] = true;
		expected[3][2] = true;
		assertArrayEquals(" la griglia presente ha tre vicini vivi quindi la futura avrà quattro vicini vivi",expected,result);
	}
	
	
	
	

}
