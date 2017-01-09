package paul.graph.dataset.reader;

import java.net.URL;

import paul.graph.algorithm.WeakComponentDetector;
import paul.graph.dataset.data.ExampleNetworkPath;
import prefuse.data.Graph;
import prefuse.data.io.DataIOException;
import prefuse.data.io.GraphMLReader;

/**
 * Contextphone network
 * 
 * Directed & weighted.
 * 
 * N. Eagle, A. Pentland, D. Lazer, Inferring friendship network structure by using
 * mobile phone data, Proc. Natl. Acad. Sci. USA, vo. 106, pp. 15274-15278, 2009.
 * 
 * @author Paul Kim
 *
 */
public class ContextphoneNetworkReader {
	
	public static final String FILE_NAME = "contextphone.graphml";
	
	public static Graph getGraph() {
		
		URL fileURL = ContextphoneNetworkReader.class.getResource(ExampleNetworkPath.CONTEXTPHONE_NETWORK_PATH);
		
		Graph g = null;
		GraphMLReader reader = new GraphMLReader();
		try {
			g = reader.readGraph(fileURL);
		} catch (DataIOException e) {
			e.printStackTrace();
		}
		
		g.isDirected(true);
		g.isWeighted(false);
		
		return g;
	}
	
	public static void main(String[] args) {
		Graph g = getGraph();
		System.out.println(FILE_NAME);
		System.out.println("\t" + g.getNodeCount());
		System.out.println("\t" + g.getEdgeCount());
		System.out.println("\t" + WeakComponentDetector.find(g).size());
	}

}
