package paul.graph.dataset;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import paul.graph.algorithm.WeakComponentDetector;
import paul.graph.dataset.reader.CElegansNetworkReader;
import paul.graph.dataset.reader.ContextphoneNetworkReader;
import paul.graph.dataset.reader.EnronNetworkReader;
import paul.graph.dataset.reader.EuAllNetworkReader;
import paul.graph.dataset.reader.FriendshipNetworkReader;
import paul.graph.dataset.reader.GoogleNetworkReader;
import paul.graph.dataset.reader.JazzNetworkReader;
import paul.graph.dataset.reader.KarateNetworkReader;
import paul.graph.dataset.reader.NetScienceNetworkReader;
import paul.graph.dataset.reader.NotreDameNetworkReader;
import paul.graph.dataset.reader.OclinksNetworkReader;
import paul.graph.dataset.reader.PgpNetworkReader;
import paul.graph.dataset.reader.SkitterNetworkReader;
import paul.graph.dataset.reader.USAirportNetworkReader;
import prefuse.data.Edge;
import prefuse.data.Graph;

public class ExampleGraphReader {
	
	public static void main(String[] args) {
		
		Graph g = getKarateNetwork();
		printAndCheckNetwork("Karate club network", g);
		
		g = getJazzNetwork();
		printAndCheckNetwork("Jazz musicion network", g);
		
		g = getOclinksNetwork();
		printAndCheckNetwork("Oclinks", g);
		
		g = getEnronNetwork();
		printAndCheckNetwork("Enron email network", g);
		
		g = getEuAllNetwork();
		printAndCheckNetwork("EuAll network", g);
		
		g = getPgpNetwork();
		printAndCheckNetwork("PGP network", g);
		
		g = getNetScienceNetwork();
		printAndCheckNetwork("NetScience", g);
	}
	
	public static final String CELEGANS_NETWORK = "celegans";
	public static final String CONTEXTPHONE_NETWORK = "contextphone";
	public static final String ENRON_NETWORK = "enron";
	public static final String EUALL_NETWORK = "euall";
	public static final String FRIENDSHIP_NETWORK = "friendship";
	public static final String GOOGLE_NETWORK = "google";
	public static final String JAZZ_NETWORK = "jazz";
	public static final String KARATE_NETWORK = "karate";
	public static final String NETSCIENCE_NETWORK = "netscience";
	public static final String NOTREDAME_NETWORK = "notredame";
	public static final String OCLINKS_NETWORK = "oclinks";
	public static final String PGP_NETWORK = "pgp";
	public static final String SKITTER_NETWORK = "skitter";
	public static final String USAIRPORT_NETWORK = "usairport";
	
	private static void printAndCheckNetwork(String name, Graph g) {		
		System.out.println(name);
		System.out.println("\tNodes : " + g.getNodeCount());
		System.out.println("\tEdges : " + g.getEdgeCount());
		System.out.println("\tComponents : " + WeakComponentDetector.find(g).size());
		System.out.println("\tChecking... ");
		Set<String> edgeMap = new HashSet<String>();
		Iterator<?> iter = g.edges();
		while ( iter.hasNext() ) {
			Edge e = (Edge)iter.next();
			int snRow = e.getSourceNode().getRow();
			int tnRow = e.getTargetNode().getRow();
			if ( snRow == tnRow ) 
				System.out.println("\tError : Self-loop!");
			String str = (snRow<tnRow) ? ""+snRow+"_"+tnRow : ""+tnRow+"_"+snRow;
			if ( !edgeMap.contains(edgeMap) )
				edgeMap.add(str);
			else
				System.out.println("\tError : Edge duplicated!");
		}
		System.out.println("\tDone.");
		System.out.println();
	}
	
	public static Graph getCelegansNetwork() {
		return CElegansNetworkReader.getGraph();
	}
	
	public static Graph getContextphoneNetwork() {
		return ContextphoneNetworkReader.getGraph();
	}
	
	public static Graph getEnronNetwork() {
		return EnronNetworkReader.getGraph();
	}
	
	public static Graph getEuAllNetwork() {
		return EuAllNetworkReader.getGraph();
	}
	
	public static Graph getFriendshipNetwork() {
		return FriendshipNetworkReader.getGraph();
	}
	
	public static Graph getGoogleNetwork() {
		return GoogleNetworkReader.getGraph();
	}
	
	public static Graph getJazzNetwork() {
		return JazzNetworkReader.getGraph();
	}
	
	public static Graph getKarateNetwork() {
		return KarateNetworkReader.getGraph();
	}
	
	public static Graph getNetScienceNetwork() {
		return NetScienceNetworkReader.getGraph();
	}
	
	public static Graph getNotredameNetwork() {
		return NotreDameNetworkReader.getGraph();
	}
	
	public static Graph getOclinksNetwork() {
		return OclinksNetworkReader.getGraph();
	}	
	
	public static Graph getPgpNetwork() { 
		return PgpNetworkReader.getGraph();
	}
	
	public static Graph getSkitterNetwork() {
		return SkitterNetworkReader.getGraph();
	}
	
	public static Graph getUSAirportNetwork() {
		return USAirportNetworkReader.getGraph();
	}
	
	public static Graph getExampleNetwork(String name) {
		Graph g = null;
		
		if ( name.equals(CELEGANS_NETWORK) )
			return getCelegansNetwork();
		else if ( name.equals(CONTEXTPHONE_NETWORK) )
			return getContextphoneNetwork();
		else if ( name.equals(ENRON_NETWORK) ) 
			return getEnronNetwork();
		else if ( name.equals(EUALL_NETWORK) ) 
			return getEuAllNetwork();
		else if ( name.equals(FRIENDSHIP_NETWORK) ) 
			return getFriendshipNetwork();
		else if ( name.equals(GOOGLE_NETWORK) ) 
			return getGoogleNetwork();
		else if ( name.equals(JAZZ_NETWORK) ) 
			return getJazzNetwork();
		else if ( name.equals(KARATE_NETWORK) ) 
			return getKarateNetwork();
		else if ( name.equals(NETSCIENCE_NETWORK) )
			return getNetScienceNetwork();
		else if ( name.equals(NOTREDAME_NETWORK) ) 
			return getNotredameNetwork();
		else if ( name.equals(OCLINKS_NETWORK) ) 
			return getOclinksNetwork();
		else if ( name.equals(PGP_NETWORK) ) 
			return getPgpNetwork();
		else if ( name.equals(SKITTER_NETWORK) ) 
			return getSkitterNetwork();
		else if ( name.equals(USAIRPORT_NETWORK) )
			return getUSAirportNetwork();
		
		return g;
	}
	

}
