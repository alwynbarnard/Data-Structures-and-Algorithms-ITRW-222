public class Driver
{
	public static void main(String[] args)
	{
		SLL myList = new SLL();
		System.out.println("Initial list");
		myList.showAll();
		
		System.out.println("isMember test");
		boolean ans = myList.isMember(myList);
		System.out.println("answer is: "+ans);
		System.out.println("missing test");
		SLL newList = new SLL();
		newList=myList.missing(subList); //From ASSIGNMENT. Makes no sense to me
		newList.showAll();
	}

}