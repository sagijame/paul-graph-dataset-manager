package paul.graph.dataset.reader;

import java.net.URL;
import java.util.Iterator;

import paul.graph.algorithm.WeakComponentDetector;
import paul.graph.dataset.data.ExampleNetworkPath;
import paul.graph.data.io.TXTReader;
import prefuse.data.Edge;
import prefuse.data.Graph;
import prefuse.data.Schema;
import prefuse.data.io.DataIOException;

/**
 * Web network of university of Notre Dame
 * 
 * Directed & unweighted.
 * 
 * Nodes represent pages from University of Notre Dame (domain nd.edu) and directed edges represent
 * hyperlinks between them. The data was collected in 1999 by Albert, Jeong and Barabasi.
 * 
 * R. Albert, H. Jeong, A. Barabasi, Diameter of the World-Wide Web, Nature, 1999.
 * 
 * @author Paul Kim
 *
 */
public class NotreDameNetworkReader {
	
public static final String FILE_NAME = "notredame.txt";
	
	public static Graph getGraph() {
		
		URL fileURL = NotreDameNetworkReader.class.getResource(ExampleNetworkPath.NOTREDAME_NETWORK_PATH);
		
		Graph g = null;
		TXTReader reader = new TXTReader(true, false);
		try {
			g = reader.readGraph(fileURL);
		} catch (DataIOException e) {
			e.printStackTrace();
		}
		
		g.isDirected(true);
		g.isWeighted(false);
		
		Iterator<?> edges = g.edges();
		while ( edges.hasNext() )
			((Edge)edges.next()).setDouble(Schema.KEY_WEIGHT, 1.0);		
	
		return g;
	}
	
	public static Graph getUndirectedGraph() {
		URL fileURL = NotreDameNetworkReader.class.getResource(ExampleNetworkPath.NOTREDAME_NETWORK_PATH);
		
		Graph g = null;
		TXTReader reader = new TXTReader(false, false);
		try {
			g = reader.readGraph(fileURL);
		} catch (DataIOException e) {
			e.printStackTrace();
		}
		
		g.isDirected(false);
		g.isWeighted(false);
		
		Iterator<?> edges = g.edges();
		while ( edges.hasNext() )
			((Edge)edges.next()).setDouble(Schema.KEY_WEIGHT, 1.0);		
	
		return g;
	}
	
	public static void main(String[] args) {
		Graph g = getGraph();
		System.out.println(FILE_NAME + "\n");
		System.out.println("\t" + g.getNodeCount());
		System.out.println("\t" + g.getEdgeCount());
		System.out.println("\t" + WeakComponentDetector.find(g).size());
	}

}
