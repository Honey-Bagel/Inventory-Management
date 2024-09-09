/** A  linked implementation of the ADT list. */
public class LList<T> implements ListInterface<T>
{
	private Node<T> firstNode;
	private int numberOfEntries;

	/** No-arg or default constructor. */
	public LList()
	{
		initializeDataFields();
	} // end default constructor 
	
	/** Removes all entries from the list. */
	public void clear()
	{
		initializeDataFields();
	}

	/** Adds an entry to the list at a givenPosition.
	@param givenPosition The position you want to put the entry at.
	@param newEntry The entry you want to add. */
	public void add(int givenPosition, T newEntry)
	{
		if ((givenPosition >= 0) && (givenPosition < numberOfEntries + 1))
		{
			Node<T> newNode = new Node<T>(newEntry);
			if (givenPosition == 0)
			{
				newNode.setNextNode(firstNode);
				firstNode = newNode;
			}
			else 
			{
				Node<T> nodeBefore = getNodeAt(givenPosition - 1);
				Node<T> nodeAfter = nodeBefore.getNextNode();
				newNode.setNextNode(nodeAfter);
				nodeBefore.setNextNode(newNode);
			} // end if
			numberOfEntries++;
		}
		else
			throw new IndexOutOfBoundsException("Illegal position given to add operation.");
	} // end add

	/** Adds an entry to the end of the list.
	@param newEntry The entry to add. */
	public void add(T newEntry)
	{
		Node<T> newNode = new Node<T>(newEntry);
		if (isEmpty())
			firstNode = newNode;
		else
		{
			Node<T> lastNode = getNodeAt(numberOfEntries-1);
			lastNode.setNextNode(newNode); // make last node reference new node
		} // end if
		numberOfEntries++;
	} // end add

	/** Removes an entry at a given position.
	@param givenPosition The position of the entry to remove.
	@return The removed entry. */
	public T removePosition(int givenPosition)
	{
		T result = null;
		if ((givenPosition >= 0) && (givenPosition < numberOfEntries))
		{
			// Assertion: !isEmpty()
			if (givenPosition == 0)
			{
                result = (T)firstNode.getData();
				firstNode = firstNode.getNextNode();
			}
			else
			{
				Node<T> nodeBefore = getNodeAt(givenPosition - 1);
				Node<T> nodeToRemove = nodeBefore.getNextNode();
				result = (T)nodeToRemove.getData(); // save entry to be removed
				Node<T> nodeAfter = nodeToRemove.getNextNode();
				nodeBefore.setNextNode(nodeAfter); // remove entry
			} // end if
			numberOfEntries--;
			return result;
		}
		else
			throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
	} // end removePosition

	/** Removes a given entry.
	@param targetEntry The entry to remove.
	@return Returns true if the entry was successfully removed, false otherwise.*/
	public boolean remove(T targetEntry){
		int index = 0;
		Node<T> node = firstNode;
		while(index < numberOfEntries){
			if(node.getData().equals(targetEntry)){
				removePosition(index);
				return true;
			}
			else {
				index++;
				node = node.getNextNode();
			}
		}
		return false;
	}

	/** Returns this list as an array.
	@return An array representation of this list. */
	@SuppressWarnings("unchecked")
    public T[] toArray()
	{
		// The cast is safe because the new array contains null entries
		T[] result = (T[]) new Object[numberOfEntries];
		int index = 0;
		Node<T> currentNode = firstNode;
		while ((index < numberOfEntries) && (currentNode != null))
		{
			result[index] = (T)currentNode.getData();
			currentNode = currentNode.getNextNode();
			index++;
		} // end while
		return result;
	} // end toArray

	/** Returns whether or not this list contains a given entry.
	@param anEntry The entry to be searched for.
	@return True if the list contains the entry, false otherwise. */
	public boolean contains(T anEntry)
	{
		boolean found = false;
		Node<T> currentNode =  firstNode;
		while (!found && (currentNode != null))
		{
			if (anEntry.equals(currentNode.getData()))
				found = true;
			else
				currentNode = currentNode.getNextNode();
		} // end while
		return found;
	} // end contains

	/** Returns the entry at the given position.
	@param givenPosition The position of the entry to be returned.
	@return The entry. */
	public T getEntry(int givenPosition)
	{
		if ((givenPosition >= 0) && (givenPosition < numberOfEntries))
		{
			// Assertion: !isEmpty()
			return (T)getNodeAt(givenPosition).getData();
		}
		else
			throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
	} // end getEntry

	/** Replaces the entry at a given position with a given entry.
	@param givenPosition The position of the entry to be replaced.
	@param newEntry The entry that will replace the entry at givenPosition.
	@return The replaced entry. */
	public T replace(int givenPosition, T newEntry)
	{
		if ((givenPosition >= 0) && (givenPosition < numberOfEntries))
		{
			// Assertion: !isEmpty()
			Node<T> desiredNode = getNodeAt(givenPosition);
			T originalEntry = (T)desiredNode.getData();
			desiredNode.setData(newEntry);
			return originalEntry;
		}
		else
			throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
	} // end replace

	/** Initializes the class’s data fields to indicate an empty list. */
	private void initializeDataFields()
	{
		firstNode = null;
		numberOfEntries = 0;
	} // end initializeDataFields

	/** Gets a reference to the node at a given position.
	@param givenPosition The position of the node you want.
	@return A reference to the node you want. */
	private Node<T> getNodeAt(int givenPosition)
	{
		// Assertion: (firstNode != null) && (1 <= givenPosition) && (givenPosition <= numberOfEntries)
		Node<T> currentNode = firstNode;
		
		// Traverse the chain to locate the desired node (skipped if givenPosition is 0)
		for (int counter = 0; counter < givenPosition; counter++)
			currentNode = currentNode.getNextNode();
		
		// Assertion: currentNode != null
		return currentNode;
	} // end getNodeAt

    public int getLength(){
        return numberOfEntries;
    }

    public boolean isEmpty(){
        return numberOfEntries == 0;
    }
}
