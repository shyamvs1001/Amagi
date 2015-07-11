import java.util.HashSet;
import java.util.Set;


public class Node {
	
	private String value;
	
	private final Set<Node> adjacents = new HashSet<>();
	
	public Set<Node> getAdjacents() {
		return adjacents;
	}

	public Node(String value){
		this.value = value;
	}
	
	public void addNeighbour(Node node){
		adjacents.add(node);
	}

	public String getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		String output = value+"->";
		for(Node n : adjacents){
			output = output+n.getValue()+",";
		}
		output = output.substring(0, output.length()-1);
		return output;
	}

}
