package cpsc335a3;

import java.util.ArrayList;

public class Trie {
	
	private ArrayList<TrieNode> heads;
	private int numKeys;
	private int numSuccessQueries;
	private double averageSuccessDepth;
	
	public Trie()
	{
		heads = new ArrayList<TrieNode>();
		numKeys = 0;
		numSuccessQueries = 0;
		averageSuccessDepth = 0;
	}
	
	public boolean search(String word)
	{
		if(word.length() == 0)
			return true;
		
		TrieNode current = findHead(word.charAt(0));
		
		if(current == null)
			return false;
		
		current = current.search(word.substring(1));
		
		if(current.depth == word.length() && current.endNode)
		{
			double totalDepth = averageSuccessDepth * numSuccessQueries;
			totalDepth += current.depth;
			numSuccessQueries++;	
			averageSuccessDepth = totalDepth / (double)numSuccessQueries;
			
			return true;
		}
		
		return false;
	}
	
	public void insert(String word)
	{
		if(word.length() == 0)
			return;
		
		char firstChar = word.charAt(0);
		
		TrieNode head = findHead(firstChar);
		
		if(head == null)
		{
			head = new TrieNode(firstChar, 1, false);
			heads.add(head);
		}
		
		head.insert(word.substring(1));
		
		numKeys++;
	}
	
	public int getNumKeys()
	{
		return numKeys;
	}
	
	public double getAverageSuccessDepth()
	{
		return averageSuccessDepth;
	}
	
	private TrieNode findHead(char firstChar)
	{
		if(heads.isEmpty())
			return null;
		
		for(TrieNode n : heads)
		{
			if(n.value == firstChar)
				return n;
		}
		
		return null;
	}

}
