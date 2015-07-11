import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * The graph is formed once the list of words are given.
 * @author ShyamVS
 *
 */
public class WordGraph {
	
	private static final char[] alphabets = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	
	private final Map<String,Node>  words2Nodes = new HashMap<>();
	private Set<Node>  wordNodes = new HashSet<>();
	private Set<String> words;
	
	public WordGraph(Set<String> words){
		this.words= words;
		long start = System.currentTimeMillis();
		createGraph(words);
		long end = System.currentTimeMillis();
		System.out.println("Time taken = "+((end - start)/1000)+" seconds");
	}
	
	private void createGraph(Set<String> words){
		words.forEach(word -> {Node n = new Node(word); wordNodes.add(n);words2Nodes.put(word, n);}); 
		wordNodes.stream().forEach(n -> addNeighbours(n)); 
	}
	
	private void addNeighbours(Node node){
		String word = node.getValue();
		for(char c : alphabets){
			//add char before
			String newWord = c+word;
			if(words.contains(newWord) && !word.equals(newWord))
				node.addNeighbour(words2Nodes.get(newWord));
			//add char after
			newWord = word+c;
			if(words.contains(newWord) && !word.equals(newWord))
				node.addNeighbour(words2Nodes.get(newWord));
		}
		for(int i = 0;i<word.length();i++){
			String prefix = word.substring(0,i);
			String suffix = word.substring(i+1);
			//remove char
			String newWord = prefix+suffix;
			if(words.contains(newWord) && !word.equals(newWord))
				node.addNeighbour(words2Nodes.get(newWord));
			//replace char
			for(char c : alphabets){
				newWord = prefix+c+suffix;
				if(words.contains(newWord) && !word.equals(newWord))
					node.addNeighbour(words2Nodes.get(newWord));
			}
		}
	}

	/**
	 * Prints the path from source to destination if one exists, else doesn't print anything.
	 * @param source
	 * @param destination
	 */
	public void transform(String source, String destination) {
		if(!(words.contains(source) && words.contains(destination)))
				return;
		Node sourceNode = words2Nodes.get(source);
		Queue<Node> queue = new LinkedList<>();
		Map<String,String> nodeToParent = new HashMap<>(); 
		boolean found = false;
		queue.add(sourceNode);
		Set<Node> visited = new HashSet<>();
		
		while(queue.size()>0 && !found){//exit when exhausted or found destination
			Node currentNode = queue.remove();
			for(Node node : currentNode.getAdjacents()){
				if(visited.contains(node))//avoid effort duplication
					continue;
				visited.add(node);
				if(node.getValue().equals(destination))
				{
					nodeToParent.put(node.getValue(), currentNode.getValue());
					found = true;
					break;
				}
				nodeToParent.put(node.getValue(), currentNode.getValue());
				queue.add(node);	
			}
		}
		if(!found)
			return;
		
		//backtrack to form the string path.
		String output = destination;
		String current = destination;
		while(!current.equals(source))
		{
			String parent = nodeToParent.get(current);
			output = parent + " -> " + output;
			current = parent;
		}
		System.out.println(output);	
	}
}
