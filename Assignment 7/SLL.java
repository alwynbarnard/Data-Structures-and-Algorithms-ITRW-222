public class SLL<T extends Comparable<? super T>>
{
	//FROM ASSIGNMENT: You may assume append, prepend, toString is in the class
	public Element<T> head;
	public Element<T> tail;
	
	private SLL myList = new SLL();
	public SLL()
	{
		head = null;
		tail = null;
	}
	
	public boolean isMember(SLL list)
	{
		Element ptr = head;
	   Element prevPtr = null;
	   while (ptr!= null&& ptr.data.compareTo(list)!= 0)
	   {
		   prevPtr=ptr;
		   ptr=ptr.next;
	   }
	   if (ptr == null)
		   return false;
	}
	public T missing(SLL listMissing)
	{
		Element ptrThis, ptrParam;
		ptrThis = head;
		ptrParam = listMissing.head;
		SLL list3 = new SLL();
		
		while((ptrThis != null) && (ptrParam != null))// continue up to end of one list
		{
			if (ptrThis.data.compareTo(ptrParam.data)!=0)
			{
				list3.append(ptrThis.data);
				ptrThis = ptrThis.next;
			}
			else
			{
				ptrParam = ptrParam.next;
			}
		}
	}
	public class Element<T1 extends Comparable<? super T>>
{
	public T1 data;
	public Element <T1> next;
	public Element(T1 param)
	{
		data = param;
	}
}
}