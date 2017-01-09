package paul.graph.dataset.reader;

import java.net.URL;
import java.util.Iterator;

import paul.graph.dataset.ExampleGraphReader;
import paul.graph.data.io.TXTReader;
import prefuse.data.Edge;
import prefuse.data.Graph;
import prefuse.data.Schema;
import prefuse.data.io.DataIOException;
import prefuse.data.io.GraphMLWriter;

/**
 * Google web graph
 * 
 * Directed & unweighted
 * 
 * Nodes represent web pages and directed edges represent hyperlinks between them.
 * The data wab released in 2002 by Google as a part of Google Programming Contest
 * 
 * J. Leskovec, K. Lang, A. Dasgupta, M. Mahoney,
 * Community structure in large networks: Natural cluster sizes and the absence of 
 * large well-defined clusters,
 * Internet Mathematics 6(1), 29-12, 2009.
 * 
 * @author Paul
 */
public class GoogleNetworkReader {
	
	public static final String FILE_NAME = "google.txt";
	
	public static Graph getGraph() {
		
		URL fileURL = GoogleNetworkReader.class.getResource(ExampleGraphReader.EXAMPLE_FILES_PATH + FILE_NAME);
		
		Graph g = null;
		TXTReader reader = new TXTReader(true, true);
		
		try {
			g = reader.readGraph(fileURL);
		} catch (DataIOException e) {
			e.printStackTrace();
		}
		
		g.isDirected(true);
		g.isWeighted(false);
		
		Iterator<?> eIter = g.edges();
		while ( eIter.hasNext() ) 
			((Edge)eIter.next()).setDouble(Schema.KEY_WEIGHT, 1.0);
		
		return g;
	}
	
	public static void main(String[] args) {
		Graph g = getGraph();
		System.out.println(FILE_NAME);
		System.out.println("\tNodes : " + g.getNodeCount());
		System.out.println("\tEdges : " + g.getEdgeCount());
		
		try {
			GraphMLWriter w = new GraphMLWriter();
			w.writeGraph(g, "google.graphml");
		} catch (DataIOException e) {			
			e.printStackTrace();
		}
		
		System.out.println("all done.");
	}

}
