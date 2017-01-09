package paul.graph.dataset.reader;

import java.net.URL;

import paul.graph.dataset.ExampleGraphReader;
import paul.graph.data.io.TXTReader;
import prefuse.data.Graph;
import prefuse.data.io.DataIOException;

public class SimpleNetworkReader {
	
	public static final String FILE_NAME = "simple_graph.txt";
	
	public static Graph getGraph() {
		URL fileURL = SimpleNetworkReader.class.getResource(ExampleGraphReader.EXAMPLE_FILES_PATH + FILE_NAME);
		
		Graph g = null;
		TXTReader r = new TXTReader();
		try {
			g = r.readGraph(fileURL);
		} catch (DataIOException e) {
			e.printStackTrace();
		}
		return g;
	}
	
	public static void main(String[] args) {
		Graph g = SimpleNetworkReader.getGraph();
		System.out.println("Simple network");
		System.out.println("\tNodes : " + g.getNodeCount());
		System.out.println("\tEdges : " + g.getEdgeCount());
	}

}
