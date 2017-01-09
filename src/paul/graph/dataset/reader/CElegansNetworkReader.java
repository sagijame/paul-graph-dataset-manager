package paul.graph.dataset.reader;

import java.net.URL;

import paul.graph.dataset.ExampleGraphReader;
import paul.graph.data.io.GMLReader;
import prefuse.data.Graph;
import prefuse.data.io.DataIOException;

/**
 * C. Elegans
 * 
 * A directed, weighted network representing the neural network of
 * C. Elegans. Data compiled by D. Watts and S. Strogartz
 * 
 * Paper
 * D. J. Watts and S. H. Strogatz, 
 * Collective dynamics of 'small-world' networks
 * Nature, vol. 393, pp. 440-442, 1998.
 * 
 * @author paul
 *
 */
public class CElegansNetworkReader {
	
	public static final String FILE_NAME = "celegans.gml";
		
	public static Graph getGraph() {
		
		URL fileURL = CElegansNetworkReader.class.getResource(ExampleGraphReader.EXAMPLE_FILES_PATH + FILE_NAME);
		
		Graph g = null;
		GMLReader reader = new GMLReader();
		
		try {
			g = reader.readGraph(fileURL);
		} catch (DataIOException e) {
			e.printStackTrace();
		}
		
		g.isDirected(true);
		g.isWeighted(true);
	
		return g;
	}
	
}
