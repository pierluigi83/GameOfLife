package it.skedenpio;

import static org.junit.Assert.*;

import org.junit.Ignore;
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
	
	@Ignore
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
	
	@Ignore
	public void TestBoarderCellsAliveFutureGridExpanded()
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
	
	@Test
	public void TestGridChangeSize()
	{
		boolean[][] grid = new boolean[10][10];
		grid[0][2] = true;
		grid[0][3] = true;
		grid[0][4] = true;
		//grid[5][5] = true;
		//grid[5][6] = true;
		boolean[][] result = Cell.nextGrid(grid);
		boolean[][] expected = new boolean[11][10];
		expected[0][3] = true;
		expected[1][3] = true;
		expected[2][3] = true;
		assertArrayEquals(" la griglia presente ha tre vicini vivi sul bordo quindi la futura avrà una cella viva sul nuovo bordo",expected,result);
	}
	
	@Test
	public void TestGridNotChangeSize()
	{
		boolean[][] grid = new boolean[10][10];
		grid[2][2] = true;
		grid[2][3] = true;
		grid[2][4] = true;
		//grid[5][5] = true;
		//grid[5][6] = true;
		boolean[][] result = Cell.nextGrid(grid);
		boolean[][] expected = new boolean[10][10];
		expected[1][3] = true;
		expected[2][3] = true;
		expected[3][3] = true;
		assertArrayEquals(" la griglia presente ha tre vicini vivi sul bordo quindi la futura avrà una cella viva sul nuovo bordo",expected,result);
	}
	
	@Test
	public void TestGridNumberOfNeighbours()
	{
		boolean[][] grid = new boolean[10][10];
		grid[0][2] = true;
		grid[0][3] = true;
		grid[0][4] = true;
		//grid[5][5] = true;
		//grid[5][6] = true;
		int result = Cell.neighboursAlive(0, 3, grid);
		int expected = 2;
		assertEquals(" la griglia presente ha tre vicini vivi sul bordo il vicino centrale ha due vicini",expected,result);
	}
	
	
	
	@Test
	public void TestExpandedGrid()
	{
		boolean[][] grid = new boolean[10][10];
		boolean[][] result = Cell.expandedGrid(grid);
		boolean[][] expected = new boolean[12][12];
		assertArrayEquals(" la griglia presente è vuota, quella futura expanded avrà 2 righe e 2 colonne vuote in più",expected,result);
	}
	
	@Test
	public void TestExpandedGridNotVoid()
	{
		boolean[][] grid = new boolean[10][10];
		grid[2][2] = true;
		grid[3][2] = true;
		boolean[][] result = Cell.expandedGrid(grid);
		boolean[][] expected = new boolean[12][12];
		expected[3][3] = true;
		expected[4][3] = true;
		assertArrayEquals(" la griglia presente non è vuota, quella futura expanded avrà 2 righe e 2 colonne vuote in più",expected,result);
	}
	
	@Test
	public void TestGridxSize()
	{
		boolean[][] grid = new boolean[10][10];
		grid[2][2] = true;
		int result = Cell.gridxSize(grid);
		int expected = 8;
		assertEquals(" la griglia presente ha le righe di bordo vuote, quindi quella futura ha due righe in meno",expected,result);
	}
	
	@Test
	public void TestGridySize()
	{
		boolean[][] grid = new boolean[10][10];
		grid[2][2] = true;
		int result = Cell.gridySize(grid);
		int expected = 8;
		assertEquals(" la griglia presente ha le colonne di bordo vuote, quindi quella futura ha due colonne in meno",expected,result);
	}
	
	
	

}
