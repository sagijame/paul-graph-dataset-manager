package paul.graph.dataset.reader;

import java.net.URL;

import paul.graph.dataset.data.ExampleNetworkPath;
import paul.graph.data.io.NETReader;
import prefuse.data.Graph;
import prefuse.data.io.DataIOException;

/**
 * Jazz musicians network.
 * List of edges of the network of Jazz musicians.
 * Unweighted, Undirected.
 * 
 * P. Gleiser and L. Danon, Community Structures in Jazz, Advances in Complex Systems, 6, 565, 2003.
 * 
 * @author Paul Kim
 */
public class JazzNetworkReader {
	
	public static final String FILE_NAME = "jazz.net";
		
	public static Graph getGraph() {
		
		URL fileURL = JazzNetworkReader.class.getResource(ExampleNetworkPath.JAZZ_NETWORK_PATH);
		
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

}
