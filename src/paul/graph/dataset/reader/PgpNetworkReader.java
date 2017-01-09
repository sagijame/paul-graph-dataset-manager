package paul.graph.dataset.reader;

import java.net.URL;

import paul.graph.dataset.data.ExampleNetworkPath;
import paul.graph.data.io.NETReader;
import prefuse.data.Graph;
import prefuse.data.io.DataIOException;

/**
 * 
 * PGP network
 * 
 * Undirected & unweighted network
 * 
 * Nodes : 10680
 * 
 * 
 * List of edges of the giant component of the network of users of the Pretty-Good-Privacy
 * algorithm for secure information interchange.
 * 
 * M. Boguna, R. Pastor-Satorras, A. Diaz-Guilera, and A. Arenas, Physical Review E, 
 * vol. 70, 054122, 2004.
 * 
 * @author Paul Kim
 * 
 */
public class PgpNetworkReader {
	
	public static final String FILE_NAME = "pgp.net";
	
	public static Graph getGraph() {
		
		URL fileURL = JazzNetworkReader.class.getResource(ExampleNetworkPath.PGP_NETWORK_PATH);
		
		Graph g = null;
		NETReader reader = new NETReader(false);
		try {
			g = reader.readGraph(fileURL);
		} catch (DataIOException e) {
			e.printStackTrace();
		}
		
		g.isDirected(false);
		g.isWeighted(false);
	
		return g;
	}
	
	public static void main(String[] args) {
		Graph g = getGraph();
		System.out.println(FILE_NAME);
		System.out.println("\t" + g.getNodeCount());
		System.out.println("\t" + g.getEdgeCount());
	}

}
