
public class Driver
{
	public static void main(String [] args)
	{
	 
	 SLL<Integer> myList = new SLL<Integer>();
	 SLL<Integer> testList = new SLL<Integer>();
	 SLL<Integer> returnList = new SLL<Integer>();
	 
	 // test is member  with empty list
	 if (myList.isMember(new Integer (4))) // test empty list
		 System.out.println("\n 4 is found in the list");
	 else
		System.out.println("\n 4 is NOT found");
	System.out.println("\nInitial list: ");
	System.out.printf("\nmylist %s",myList);
	// test both empty lists for missing
	 returnList=myList.missing(testList);
	 System.out.printf("\nMylist %s",myList);
	 System.out.printf("\nTestList %s",testList);
	 System.out.printf("\nReturnList: %s",returnList);
	 System.out.printf("\nMylist %s",myList);
	 
	 System.out.println("\nAdd items: ");// Add item to Empty list
	 myList.append(new Integer(2));
	 myList.append(new Integer(4));
	 myList.append(new Integer(6));
	 myList.append(new Integer(1));
	 System.out.printf("\nmylist %s",myList);
	 
	 // test missing with empty parameter list
	 returnList=myList.missing(testList);
	 System.out.printf("\nMylist %s",myList);
	 System.out.printf("\nTestList %s",testList);
	 System.out.printf("\nReturnList: %s",returnList);
	 System.out.printf("\nMylist %s",myList);
	 
	 	 
	 
	 if (myList.isMember(new Integer (4)))
		 System.out.println("\n 4 is found in the list");
	 else
		System.out.println("\n 4 is NOT found");
	 if (myList.isMember(new Integer (8)))
		 System.out.println("\n 8 is found in the list");
	 else
		System.out.println("\n 8 is NOT found ");
	
	 testList.append(new Integer(1));
	 testList.append(new Integer(3));
	 testList.append(new Integer(2));
	 testList.append(new Integer(8));
	 returnList=myList.missing(testList);
	 System.out.printf("\nMylist %s",myList);
	 System.out.printf("\nTestList %s",testList);
	 System.out.printf("\nReturnList: %s",returnList);
	 System.out.printf("\nMylist %s",myList);
	}
}