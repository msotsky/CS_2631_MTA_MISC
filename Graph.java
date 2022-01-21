//Matthew Noah Leger and Max Sotsky
//Lab 5
//COMP 2631

import java.util.*;
import java.io.*;


public class Graph {
    private int numNodes;
    int[][] adj;

    public Graph(File file) throws FileNotFoundException
    {

        Scanner sc = new Scanner(file);
        numNodes = sc.nextInt();
        adj = new int[numNodes][numNodes];

        for (int i = 0; i < numNodes; i++)
        {
            for (int j = 0; j < numNodes; j++)
            {
                adj[i][j] = sc.nextInt();
            }
        }

        if (!this.checkMatrix()) {
            throw new IllegalArgumentException("Invalid Matrix.");
        }

    }

    private boolean checkMatrix()
    {
        for (int i = 0; i < numNodes; i++)
        {
            if (adj[i][i] == 1)
            {
                return false;
            }

            for (int j = 0; j < numNodes; j++)
            {
                if (adj[i][j] != adj[j][i])
                {
                    return false;
                }
            }
        }
        return true;
    }

    public int countConnectedComponents()
    {
        int counter = 0;
        boolean[] visited = new boolean[numNodes];

        for (int i = 0; i < numNodes; i++)
        {
            if (!visited[i])
            {
                counter++;
                dfs(i, visited);
            }
        }
        return counter;
    }

    private void dfs(int index, boolean[] visited)
    {
        visited[index] = true;
        for (int j = 0; j < numNodes; j++)
        {
            if (adj[index][j] == 1 && !visited[j])
            {
                dfs(j, visited);
            }
        }
    }

}
