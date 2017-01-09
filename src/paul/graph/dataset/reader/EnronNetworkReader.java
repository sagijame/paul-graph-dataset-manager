package paul.graph.dataset.reader;

import java.net.URL;
import java.util.Iterator;

import paul.graph.algorithm.WeakComponentDetector;
import paul.graph.dataset.ExampleGraphReader;
import paul.graph.data.io.TXTReader;
import paul.util.GraphLib;
import prefuse.data.Edge;
import prefuse.data.Graph;
import prefuse.data.Schema;
import prefuse.data.io.DataIOException;
import prefuse.data.io.GraphMLWriter;

/**
 * Enron Email Network
 * 
 * Undirected and unweighted network
 * 
 * Enron email communication network covers all the email communication with a dataset of around half million emails.
 * This data was originally made public, and posted to the web, by the Federal Energy Regulatory Commission during its
 * investigation. Nodes of the network are email addresses and if an address i sent at least one email to address j,
 * the graph contains an undirected edge from i to j. Note that non-Enron email addresses act as sinks and sources in 
 * the network as we only observe their communication with the Enron email addresses.
 * 
 * Node : 36692
 * Edge : 183831
 * 
 * J. Leskovec, K. Lang, A. Dasgupta, M. Mahoney, Community Structure in Large Networks: Natural Cluster Sizes and
 * the Absence of Large Well-Defined Clusters, Internet Mathematics, vol. 6, no. 1, pp. 29-123, 2009.
 * 
 * @author Paul
 */
public class EnronNetworkReader {
	
	public static final String FILE_NAME = "enron.txt";
	
	public static Graph getGraph() {
		
		URL fileURL = EnronNetworkReader.class.getResource(ExampleGraphReader.EXAMPLE_FILES_PATH + FILE_NAME);
		
		Graph g = null;
		TXTReader reader = new TXTReader(false);
		
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
	
	public static void saveGraphMLFile(String path) {
		Graph g = getGraph();
		
		GraphMLWriter w = new GraphMLWriter();
		try {
			w.writeGraph(g, path);
		} catch (DataIOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Enron network\n");
		Graph g = getGraph();		
		System.out.println("\tNodes : " + g.getNodeCount());
		System.out.println("\tEdges : " + g.getEdgeCount());
		System.out.println("\tComponents : " + WeakComponentDetector.find(g).size());
		System.out.println();
		
		g = GraphLib.getLargestComponent(g);
		System.out.println("\tNodes : " + g.getNodeCount());
		System.out.println("\tEdges : " + g.getEdgeCount());
		System.out.println("\tComponents : " + WeakComponentDetector.find(g).size());
		System.out.println();
		
		//saveGraphMLFile("D:\\enron.graphml");
		System.out.println("Done.");
	}

}
