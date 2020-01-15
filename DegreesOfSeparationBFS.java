import edu.princeton.cs.algs4.SymbolGraph;
import edu.princeton.cs.algs4.BreadthFirstPaths;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.In;

public class DegreesOfSeparationBFS {

    private DegreesOfSeparationBFS() {
    }

    public static void main(String[] args) {
        String filename = args[0];
        String delimiter = args[1];
        String source = args[2];
	String destination = args[3];

	In in = new In(filename);

        SymbolGraph sg = new SymbolGraph(filename, delimiter);
        Graph G = sg.G();
        if (!sg.contains(source)) {
            StdOut.println(source + " not in database.");
            return;
        }
	if (!sg.contains(destination)) {
	    StdOut.println(destination + " not in database.");
	}

        int s = sg.index(source);
        BreadthFirstPaths bfs = new BreadthFirstPaths(G, s);

	Stack<String> vertices = new Stack<String>();
	String sink = destination;
	if (sg.contains(sink)) {
	    int t = sg.index(sink);
	    if (bfs.hasPathTo(t)) {
		StdOut.println(destination + " has a bacon number of " + (bfs.distTo(t)/2) + ".");
		for (int v : bfs.pathTo(t)) {
		    vertices.push(sg.name(v));
		}
		while (vertices.size() >= 2) {
		    StdOut.println(vertices.pop() + " was in the movie " + vertices.pop() + " with " + vertices.peek());
		}
	    }
	    else {
		StdOut.println("Not connected");
	    }
	}
	else {
	    StdOut.println("   Not in database.");
	}
    }
}
