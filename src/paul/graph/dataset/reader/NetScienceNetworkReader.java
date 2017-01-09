package paul.graph.dataset.reader;

import java.net.URL;
import java.util.Iterator;

import paul.graph.algorithm.WeakComponentDetector;
import paul.graph.dataset.data.ExampleNetworkPath;
import prefuse.data.Edge;
import prefuse.data.Graph;
import prefuse.data.io.DataIOException;
import prefuse.data.io.GraphMLReader;

/**
 * NetScience
 * 
 * Undirected & weighted network
 * 
 * M. E. J. Newman, Finding Community Structure in Networks using the Eigenvectors of Matrices,
 * Physical Review E, vol. 74, 036104, 2006.
 * 
 * @author Paul Kim
 *
 */
public class NetScienceNetworkReader {
	
public static final String FILE_NAME = "netscience.graphml";
	
	public static Graph getGraph() {
		
		URL fileURL = NetScienceNetworkReader.class.getResource(ExampleNetworkPath.NETSCIENCE_NETWORK_PATH);
		
		Graph g = null;
		GraphMLReader reader = new GraphMLReader();
		try {
			g = reader.readGraph(fileURL);
		} catch (DataIOException e) {
			e.printStackTrace();
		}
		
		g.isDirected(false);
		g.isWeighted(true);
	
		return g;
	}
	
	public static Graph getLargestComponent() {
		URL fileURL = NetScienceNetworkReader.class.getResource(ExampleNetworkPath.NETSCIENCE_LARGEST_COMPONENT_PATH);
		
		Graph g = null;
		GraphMLReader reader = new GraphMLReader();
		try {
			g = reader.readGraph(fileURL);
		} catch (DataIOException e) {
			e.printStackTrace();
		}
		
		g.isDirected(false);
		g.isWeighted(true);
		
		return g;
	}
	
	public static void main(String[] args) {
		Graph g = getGraph();
		System.out.println(FILE_NAME);
		System.out.println("\t" + g.getNodeCount());
		System.out.println("\t" + g.getEdgeCount());
		
		int i, j, selfLoopNodeNum = 0;
		Edge e;
		Iterator<?> edgeIter = g.edges();
		while ( edgeIter.hasNext() ) {
			e = (Edge)edgeIter.next();
			i = e.getSourceNode().getRow();
			j = e.getTargetNode().getRow();
			if ( i == j )
				selfLoopNodeNum++;
		}
		System.out.println("\t" + "Self-loop nodes : " + selfLoopNodeNum);		
		System.out.println("\t" + "Weak components : " + WeakComponentDetector.find(g).size());
		
		System.out.println();
		
		g = getLargestComponent();
		System.out.println("Largest component in NetScience network");
		System.out.println("\t" + g.getNodeCount());
		System.out.println("\t" + g.getEdgeCount());
		
		edgeIter = g.edges();
		while ( edgeIter.hasNext() ) {
			e = (Edge)edgeIter.next();
			i = e.getSourceNode().getRow();
			j = e.getTargetNode().getRow();
			if ( i == j )
				selfLoopNodeNum++;
		}
		System.out.println("\t" + "Self-loop nodes : " + selfLoopNodeNum);		
		System.out.println("\t" + "Weak components : " + WeakComponentDetector.find(g).size());
		
		System.out.println("\nAll done.");
	}

}
