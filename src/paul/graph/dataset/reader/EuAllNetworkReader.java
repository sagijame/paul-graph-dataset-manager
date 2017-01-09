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
 * EU email communication network
 * 
 * Directed and unweighted network
 * 
 * The network was generated using email data from a large European research institution.
 * For a period from October 2003 to May 2005 (18 months) we have anonymized information
 * about all incoming and outgorin email of the research institution. For each sent or
 * received email message we know the time, the sender and the recipient of the email. Overall
 * we have 3,038,531 emails between 287,755 different email addresses. Note that we have 
 * a complete email graph for only 1,258 email addresses that come from the research institution.
 * Furthermore, there are 34,203 email addresses that both sent and received email within the 
 * span of our dataset. All other email addresses are either non-existing, mistyped or spam.
 * Given a set of email messages, each node corresponds to an email address. We create a directed 
 * edge between nodes i and j, if i sent at least one message to j.
 * 
 * Node : 265214
 * Edge : 420045
 * 
 * J. Leskovec, J. Kleinberg, and C. Faloutsos, Graph evolution: Densification and Shrinking diametwers,
 * ACM Transactions on Knwoledge Discovery from data, vol. 1, no. 1, 2007.
 * 
 * @author Paul
 */
public class EuAllNetworkReader {
	
	public static final String FILE_NAME = "euall.txt";
	
	public static Graph getGraph() {
		
		URL fileURL = EuAllNetworkReader.class.getResource(ExampleNetworkPath.EUALL_NETWORK_PATH);
		
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
		
		g = GraphLib.getLargestComponent(g);
		System.out.println("Largest component");
		System.out.println("\tNodes : " + g.getNodeCount());
		System.out.println("\tEdges : " + g.getEdgeCount());
		System.out.println("\tComponents : " + WeakComponentDetector.find(g).size()); 
		System.out.println();
		
		System.out.println("Done.");
	}

}
