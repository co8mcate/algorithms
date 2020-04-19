package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.HashMap;

/*
Objective 1: report if a cycle exists in the graph (there will either be 1 cycle or no cycles).
Objective 2: Starting from Node 1, use BFS to tell me how many layers exist in the given graph.
*/

/*
In a directed graph or cycle, each pair of consecutive nodes has
the property that (v_i, v_i+1) is an edge. 
*/

/* 
Let G be an undirected graph on n nodes.  Any two of the following
statements implies the third:
(i) G is connected
(ii) G does not contain a cycle
(iii) G has n - 1 edges
*/

/*
A '0' means no connection to a node and '1' means connection to a node 
*/

// Node to store vertex and its parent info in BFS
class Node {
    int v, parent;

    Node(int v, int parent) {
        this.v = v;
        this.parent = parent;
    }
};

public class App {
    // Perform BFS on graph starting from vertex src and
    // returns true of cycle is found in the graph
    public static boolean BFS(Map<Integer, ArrayList<Integer>> graph, int src, int N) {
        // array to store level of each node
        int level[] = new int[N];
        // stores vertex is discovered or not
        boolean[] discovered = new boolean[N];

        // mark source vertex as discovered
        discovered[src] = true;

        // create a queue used to do BFS and
        // push source vertex into the queue
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(src, -1));

        // run till queue is not empty
        while (!q.isEmpty()) {
            // pop front node from queue and print it
            Node node = q.poll();

            // do for every edge (v -> u)
            for (int u : graph.get(node.v)) {
                if (!discovered[u]) {

                    // mark it discovered
                    discovered[u] = true;

                    // construct the queue node containing info
                    // about vertex and push it into the queue
                    q.add(new Node(u, node.v));
                }

                // u is discovered and u is not a parent
                else if (u != node.parent) {
                    // we found a cross-edge ie. cycle is found
                    return true;
                }
            }
        }

        // No cross-edges found in the graph
        return false;
    }

    public static void main(final String[] args) throws FileNotFoundException {

        System.out.println("\n===================================\n");
        System.out.println("\tGraph Representation\n");
        System.out.println("===================================\n");

        try {

            String filePath = "input1.txt";

            // Create an Object for getting a file
            File myFile = new File(filePath);
            Scanner scan = new Scanner(myFile);

            // Validate that you have a file
            System.out.println("Found your file!");

            System.out.println();

            /*
             * R will consists of nodes to which s has a path Initially R = {s} While there
             * is an edge (u,v) where u in R and v is not in R Add v to R Endwhile
             */

            ArrayList<Integer> vertices = new ArrayList<>();
            List<Integer> edges = new LinkedList();
            Map<Integer, ArrayList<Integer>> graph = new HashMap();

            ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();

            // Parse data and store in data structures for adjacency matrix - O(n^2)
            while (scan.hasNextLine()) {
                Scanner colReader = new Scanner(scan.nextLine());
                ArrayList col = new ArrayList();
                while (colReader.hasNextInt()) {
                    col.add(colReader.nextInt());
                }
                adjList.add(col);
            }

            // Print a adjacency matrix of a graph representation - O(n)
            System.out.println("Adjacency Matrix:");
            for (ArrayList<Integer> rows : adjList) {
                System.out.println(rows);
            }
            System.out.println();

            // Create an adjacency list for connections - O(n^2)
            // row
            for (int i = 0; i < adjList.size(); i++) {
                // column
                ArrayList<Integer> arr = new ArrayList<>();
                for (int j = 0; j < adjList.get(i).size(); j++) {
                    if (adjList.get(i).get(j) == 1) {
                        arr.add(j);
                    }
                }
                graph.put(i, arr);
            }

            // Print an adjacent list of the graph representation
            System.out.println("Adjacency List:");
            System.out.println(graph);
            System.out.println();

            int numVertices = graph.size();

            if (BFS(graph, 1, numVertices)) {
                System.out.println("Does cycle exist? Yes");
            } else
                System.out.println("Does cycle exist? No");

            // call levels function with source as 0
            printLevels(graph, numVertices, 0);

            scan.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // function to determine level of each node starting
    // from x using BFS
    static void printLevels(Map<Integer, ArrayList<Integer>> graph, int V, int x) {
        // array to store level of each node
        int level[] = new int[V];
        boolean marked[] = new boolean[V];

        // create a queue
        Queue<Integer> que = new LinkedList<Integer>();

        // enqueue element x
        que.add(x);

        // initialize level of source node to 0
        level[x] = 0;

        // marked it as visited
        marked[x] = true;

        // do until queue is empty
        while (que.size() > 0) {

            // get the first element of queue
            x = que.peek();

            // dequeue element
            que.remove();

            // traverse neighbors of node x
            for (int i = 0; i < graph.get(x).size(); i++) {
                // b is neighbor of node x
                int b = graph.get(x).get(i);

                // if b is not marked already
                if (!marked[b]) {

                    // enqueue b in queue
                    que.add(b);

                    // level of b is level of x + 1
                    level[b] = level[x] + 1;

                    // mark b
                    marked[b] = true;
                }
            }
        }

        // display all nodes and their levels
        System.out.println("Nodes" + " " + "Level");
        for (int i = 0; i < V; i++)
            System.out.println(" " + i + " --> " + level[i]);
    }
}