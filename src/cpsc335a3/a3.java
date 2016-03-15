package cpsc335a3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class a3 {

	public static void main(String[] args) {
		if(args.length != 2)
		{
			System.err.println("Usage: a3 <dictionary_file> <query_file>");
			return;
		}
		
		Trie trie = new Trie();
		
        String dictionaryFileName = args[0];
        String queryFileName = args[1];
        String fileName = dictionaryFileName;

        String line = null;

        try {

        	// Reading dictionary
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            
            while((line = bufferedReader.readLine()) != null)
                trie.insert(line);
 
            bufferedReader.close();
            
            System.out.println("*********************\n** TRIE STATISTICS **\n*********************");
            System.out.println("There are a total of " + trie.getNumKeys() + " keys stored in the trie from the file " + fileName+ ".\n");
            
            // Querying
            fileName = queryFileName;
            bufferedReader = new BufferedReader(new FileReader(fileName));
            
            double totalTimeSuccess = 0;
            double totalTimeFailure = 0;
            int queries = 0;
            
            while((line = bufferedReader.readLine()) != null)
            {
            	String words[] = line.split(" ");
            	
            	for(String word : words)
            	{
	            	double startTime = System.nanoTime();
	            	boolean result = trie.search(word);
	            	double endTime = System.nanoTime();
	
	            	double durationNS = (endTime - startTime); //divide by 1000000 to get milliseconds.
	            	
	            	if(result)
	            		totalTimeSuccess += durationNS;
	            	else
	            		totalTimeFailure += durationNS;
	            	
	            	queries++;
            	}
            }
            
            System.out.println("*********************\n*** QUERY RESULTS ***\n*********************");
            System.out.println("A total of " + queries + " words were searched from the file " + fileName + ".");
            System.out.printf("The average lookup time when a word exists in the dictionary is %1.3f ns.\n", totalTimeSuccess/(double)queries);
            System.out.printf("The average lookup time when a word does NOT exist in the dictionary is %1.3f ns.\n", totalTimeFailure/(double)queries);
            System.out.printf("The average depth of a successful lookup is %1.3f. \n", trie.getAverageSuccessDepth());
        }
        catch(FileNotFoundException e) 
        {
            System.out.println(
                "Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) 
        {
            System.out.println(
                "Error reading file '"+ fileName + "'");
        }
	}

}
