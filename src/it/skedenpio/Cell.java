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
					if (isAlive(grid[i][j]))
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
		boolean[][] res = new boolean[grid.length][grid[0].length];
		for (int i = 1; i < grid.length - 1 ; i++)
		{
			for (int j = 1; j < grid[0].length - 1 ; j++)
			{
				res[i][j] = nextState(i,j,grid);
			}
		}
		return res;
			
				
	}
	

}
