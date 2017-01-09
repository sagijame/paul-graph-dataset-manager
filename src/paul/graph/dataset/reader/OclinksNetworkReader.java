package paul.graph.dataset.reader;

import java.net.URL;

import paul.graph.algorithm.WeakComponentDetector;
import paul.graph.dataset.data.ExampleNetworkPath;
import paul.util.GraphLib;
import prefuse.data.Graph;
import prefuse.data.io.DataIOException;
import prefuse.data.io.GraphMLReader;


/**
 * Oclinks
 * 
 * Directed and weighted network.
 * 
 * The Facebook-like Social Network originate from an online community for students at University of California, Irvine.
 * 
 * The dataset includes the users that sent or received at least one message (1,899). A total number of 59,835 online messages 
 * were set over 20,296 directed ties among these users. This dataset was the main dataset used in my Ph.D. thesis. 
 * This network has also been described in Patterns and Dynamics of Users¡¯ Behaviour and Interaction: 
 * Network Analysis of an Online Community and used in a number of articles including Prominence and control: 
 * The weighted rich-club effect and Clustering in weighted networks. Although this dataset contains many nodal attributes 
 * (e.g., gender, age, and course attended), these are not made available as it would be possible to reverse engineer 
 * the anonymisation procedure of users. Self-loops in the longitudinal edgelist signal the time that users registered on the site.
 * 
 * Opsahl, T., Panzarasa, P., 2009. Clustering in weighted networks. Social Networks 31 (2), 155-163.
 * 
 * @author Paul Kim
 *
 */
public class OclinksNetworkReader {
	
	public static final String FILE_NAME = "oclinks.graphml";
	
	public static Graph getGraph() {
		
		URL fileURL = OclinksNetworkReader.class.getResource(ExampleNetworkPath.OCLINKS_NETWORK_PATH);
		
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
	
	public static Graph getLargestComponent() {
		Graph g = getGraph();
		
		g = GraphLib.getLargestComponent(g);
		
		return g;
	}
	
	public static void main(String[] args) {
		Graph g = getLargestComponent();
		System.out.println(WeakComponentDetector.find(g).size());
	}
	
}
