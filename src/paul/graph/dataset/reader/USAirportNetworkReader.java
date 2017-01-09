package paul.graph.dataset.reader;

import java.net.URL;

import paul.graph.dataset.ExampleGraphReader;
import paul.graph.data.io.TXTReader;
import prefuse.data.Graph;
import prefuse.data.io.DataIOException;

/**
 * US airport network
 * 
 * Directed & weighted.
 * 
 * This is the complete US airport network in 2010.
 * 
 * V. Colizza, R. Pastor-Satorras, A. Vespignani, Reaction-diffusion processes and metapopulation models 
 * in heterogeneous networks, Nature Physics, vol. 3, pp. 276-282, 2007.
 * 
 * @author Paul Kim
 *
 */
public class USAirportNetworkReader {
	
public static final String FILE_NAME = "usairport.txt";
	
	public static Graph getGraph() {
		
		URL fileURL = USAirportNetworkReader.class.getResource(ExampleGraphReader.EXAMPLE_FILES_PATH + FILE_NAME);
		
		Graph g = null;
		TXTReader reader = new TXTReader(true);
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
