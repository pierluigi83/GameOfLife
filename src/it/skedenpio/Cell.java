package it.skedenpio;

public class Cell 
{

	public static boolean isAlive(boolean b) 
	{
		
		return b;
	}

	public static int neighboursAlive(int x, int y, boolean[][] grid)
	{
		int count = 0;
		{
			for (int i = x - 1; i <  x + 2 ; i++)
			{ 
				for (int j = y - 1; j < y + 2; j++)
				{
					if (isValid(i,j,grid) && isAlive(grid[i][j]))
						count++;
				}
			}
		}
		if (isAlive(grid[x][y]))
			count--;
		return count;
	}

	public static boolean nextState(int x, int y, boolean[][] grid) 
	{
		boolean res = false;
		if (!isAlive(grid[x][y]) && neighboursAlive(x, y, grid) == 3)
			res = true;
		if (isAlive(grid[x][y]) && (neighboursAlive(x, y, grid) == 3 || neighboursAlive(x, y, grid) == 2))
			res = true;
		
		return res;
	}

	public static boolean[][] nextGrid( boolean[][] grid)
	{
		boolean[][] temp = new boolean[grid.length+2][grid[0].length+2];
		boolean[][] res = new boolean[grid.length+2][grid[0].length+2];
		System.out.println("created");
		temp = expandedGrid(grid);
		System.out.println("grid lenghth = " + grid.length );
		System.out.println("temp length = "  + temp.length);
		for (int i = 0; i < temp.length  ; i++)
		{
			for (int j = 0; j < temp[0].length  ; j++)
			{
				res[i][j] = nextState(i,j,temp);
				System.out.println("nextstate res " + i + " " + j + "= " + res[i][j]);
			}
		}
		int x = gridxSize(res);
		int y = gridySize(res);
		System.out.println(x);
		System.out.println(y);
		return reducedGrid(res, x, y);
	}
	
	public static int gridxSize(boolean[][] grid)
	{
		int res = grid.length;
		if (zeroLine(grid[0]))
			res -= 1;
		if (zeroLine(grid[grid.length-1]))
			res -= 1;
		return res;
	}
	
	public static int gridySize(boolean[][] grid)
	{
		int res = grid[0].length;
		System.out.println("gridysize, initial res " + res);
		if (zeroColumn(grid,0))
		{
			res -= 1;
			System.out.println("first col is null so res = " + res);
		}
		if (zeroColumn(grid,grid[0].length-1))
		{
			res -= 1;
			System.out.println("second col is null so res = " + res);
		}
		return res;
	}
	
	
	public static boolean isValid(int x, int y,boolean[][] grid)
	{
		boolean res = false;
		if (x >= 0 && x < grid.length && y>= 0 && y < grid.length)
			res = true;
		return res;
		
	}
	
	public static boolean[][] expandedGrid(boolean[][] grid)
	{
		boolean[][] res = new boolean[grid.length + 2][grid[0].length + 2];
		for (int x = 1; x < res.length - 1; x++)
		{
			for (int y = 1; y < res[0].length - 1; y++)
			{
				res[x][y] = grid[x-1][y-1];
			}
		}
		return res;
	}
	
	
	
	public static boolean[][] reducedGrid(boolean[][] grid,int x, int y)
	{
		int offsetx = 0; 
		int offsety =  0; 
		if (zeroLine(grid[0]))
			offsetx = 1;
		if (zeroColumn(grid,0))
			offsety = 1;
		int ox = offsetx;
		int oy = offsety;
		System.out.println("offsetx = " + ox);
		System.out.println("offsety = " + oy);
		boolean[][] res = new boolean[x][y];
		for (int i = 0; i < x; i++)
		{
			for (int j = 0; j < y; j++)
			{
				res[i][j] = grid[i+ox][j+oy];
				System.out.println(" res " + i + " " + j + "= " + res[i][j]);
			}
		}
		return res;
	}
	
	public static boolean zeroColumn(boolean[][] grid,int c)
	{
		boolean res = true;
		for (int i = 0; i < grid.length; i++)
		{
			if (grid[i][c] == true)
				res = false;
		}
		return res;
	}

	public static boolean zeroLine(boolean[] line)
	{
		boolean res = true;
		for (int i = 0; i < line.length; i++)
		{
			if (line[i] == true)
				res = false;
		}
		return res;
		
	}
}
