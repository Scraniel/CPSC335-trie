package cpsc335a3;

import java.util.ArrayList;

public class TrieNode {
	public char value;
	public ArrayList<TrieNode> children;

	private int depth;
	private boolean endNode;
	
	public TrieNode(int depth, boolean end)
	{
		this.depth = depth;
		endNode = true;
	}
	
	
//	public TrieNode search(String word)
//	{
//		if(word.length() == 0 || endNode)
//			return this;
//		
//		for(TrieNode child : children)
//		{
//			if(child.value == word.charAt(0))
//				return search(word.substring(0));
//		}
//
//		// No children fit the 
//	}
}
