package cpsc335a3;

import java.util.ArrayList;

public class TrieNode {
	public char value;
	public ArrayList<TrieNode> children;
	public int depth;
	public boolean endNode;
	
	public TrieNode(char value, int depth, boolean end)
	{
		this.value = value;
		this.depth = depth;
		endNode = end;
		
		children = new ArrayList<TrieNode>();
	}
	
	
	public TrieNode search(String word)
	{
		if(word.length() == 0)
			return this;
		
		TrieNode child = getChild(word.charAt(0));
		
		if(child != null)
			return child.search(word.substring(1));

		return this;
	}
	
	public void insert(String word)
	{
		if(word.length() == 0)
		{	
			endNode = true;
			return;
		}
		
		char firstChar = word.charAt(0);
		String substr = word.substring(1);
		
		TrieNode child = getChild(firstChar);
		
		
		if(child != null)
			child.insert(substr);
		else
		{
			TrieNode newChild = new TrieNode(firstChar, depth +1, false);
			children.add(newChild);
			newChild.insert(substr);			
		}
	}
	
	private TrieNode getChild(char first)
	{
		for(TrieNode child : children)
		{
			if(child.value == first)
				return child;
		}
		
		return null;
	}
}
