package paul.graph.dataset.reader;

import java.net.URL;
import java.util.Iterator;

import paul.graph.dataset.data.ExampleNetworkPath;
import paul.graph.data.io.GMLReader;
import prefuse.data.Edge;
import prefuse.data.Graph;
import prefuse.data.Schema;
import prefuse.data.io.DataIOException;

/**
 * Karate Network
 * 
 * Undirected.
 * Unweighted.
 * 
 * Social network of friendships between 34 members of a karate club at a US university in the 1970.
 * 
 * W. W. Zachary, An information flow model for conflict and fission in small groups,
 * Journal of Anthropological Research, vol. 33, pp. 452-473, 1977.
 * 
 * @author Paul Kim
 */
public class KarateNetworkReader {
	
	public static final String FILE_NAME = "karate.gml";
	
	public static Graph getGraph() {
		
		URL fileURL = KarateNetworkReader.class.getResource(ExampleNetworkPath.KARATE_NETWORK_PATH);
		
		Graph g = null;
		GMLReader reader = new GMLReader();
		try {
			g = reader.readGraph(fileURL);
		} catch (DataIOException e) {
			e.printStackTrace();
		}
		
		g.isDirected(false);
		g.isWeighted(false);
		Iterator<?> edgeIter = g.edges();
		while ( edgeIter.hasNext() )
			((Edge)edgeIter.next()).setDouble(Schema.KEY_WEIGHT, 1.0);
				
		return g;
	}

}
