package paul.graph.dataset.reader;

import java.net.URL;
import java.util.Iterator;

import paul.graph.algorithm.WeakComponentDetector;
import paul.graph.dataset.data.ExampleNetworkPath;
import paul.graph.data.io.TXTReader;
import paul.util.GraphLib;
import prefuse.data.Edge;
import prefuse.data.Graph;
import prefuse.data.Schema;
import prefuse.data.io.DataIOException;

/**
 * Autonomous Systems by Skitter
 * 
 * Undirected & unweighted
 * 
 * Internet topology graph. From traceroutes run daily in 2005 -http://www.caida.org/tools/measurement/skitter.
 * From several scattered sources to million destinations. 1.7 million nodes, 11 million edges.
 * 
 * J. Leskovec, J. Kleinberg and C. Faloutsos,
 * Graphs over time: Densification laws, Shrinking diameters and possible explanations,
 * ACM SIGKDD International Conference on Knowledge Discovery and Data Mining (KDD), 2005.
 * 
 * @author Paul
 */
public class SkitterNetworkReader {
	
	public static final String FILE_NAME = "skitter.txt";
	
	public static Graph getGraph() {
		
		URL fileURL = SkitterNetworkReader.class.getResource(ExampleNetworkPath.SKITTER_NETWORK_PATH);
		
		Graph g = null;
		TXTReader reader = new TXTReader(false, true);
		
		try {
			g = reader.readGraph(fileURL);
		} catch (DataIOException e) {
			e.printStackTrace();
		}
		
		g.isDirected(false);
		g.isWeighted(false);
		
		Iterator<?> eIter = g.edges();
		while ( eIter.hasNext() ) 
			((Edge)eIter.next()).setDouble(Schema.KEY_WEIGHT, 1.0);
	
		return g;
	}
	
	public static Graph getLargestComponent() {
		Graph g = GraphLib.getLargestComponent(getGraph());
		return g;
	}
	
	public static void main(String[] args) {
		Graph g = getGraph();
		System.out.println(FILE_NAME + "\n");
		System.out.println("\tNodes : " + g.getNodeCount());
		System.out.println("\tEdges : " + g.getEdgeCount());
		System.out.println("\tComponents : " + WeakComponentDetector.find(g).size());
		System.out.println();
	}

}
