import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * args[0] = file path eg:c:/users/shyamvs/desktop/linuxwords.txt
 * args[1] = starting word
 * args[2] = ending word
 * 
 * Assumption: words are all lower case.
 * @author ShyamVS
 *
 */
public class TransformWord {

	public static void main(String[] args) throws IOException {
		String fileName = args[0];//"c:/users/shyamvs/desktop/linuxwords.txt";
		List<String> wordsList = Files.readAllLines(FileSystems.getDefault().getPath(fileName));
		
		Set<String> words = new HashSet<>(wordsList); 
		
		WordGraph graph = new WordGraph(words);
		
		long start = System.currentTimeMillis();
		graph.transform(args[1],args[2]);
		long end = System.currentTimeMillis();
		System.out.println("Time taken = "+((end - start))+" milliseconds");
	}

}
