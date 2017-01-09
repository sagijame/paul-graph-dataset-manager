package paul.graph.dataset.reader;

import java.net.URL;

import paul.graph.dataset.ExampleGraphReader;
import prefuse.data.Graph;
import prefuse.data.io.DataIOException;
import prefuse.data.io.GraphMLReader;

/**
 * Friendship network of a UK university faculty
 * 
 * Directed & weighted network
 * 
 * The personal friendship network of a faculty of a UK university,
 * consisting of 81 vertices (individuals) and 817 directed and weighted
 * connections. The school affiliation of each individual is stored as a vertex attribute.
 * This dataset can serve as a testbed for community detection algorithms.
 * 
 * T. Nepusz, A. Petroczi, L. Negyessy, F. Bazso, Fuzzy communities and the concept of 
 * bridgeness in complex networks, Physical Review E, vol. 77, 016017, 2008.
 * 
 * @author Paul Kim
 *
 */
public class FriendshipNetworkReader {
	
public static final String FILE_NAME = "friendship.graphml";
	
	public static Graph getGraph() {
		
		URL fileURL = FriendshipNetworkReader.class.getResource(ExampleGraphReader.EXAMPLE_FILES_PATH + FILE_NAME);
		
		Graph g = null;
		GraphMLReader reader = new GraphMLReader();
		try {
			g = reader.readGraph(fileURL);
		} catch (DataIOException e) {
			e.printStackTrace();
		}
		
		g.isDirected(true);
		g.isWeighted(true);
			
		return g;
	}
	
	public static void main(String[] args) {
		Graph g = getGraph();
		System.out.println(FILE_NAME);
		System.out.println("\t" + g.getNodeCount());
		System.out.println("\t" + g.getEdgeCount());
	}

}
