// 	A Barnard 28430093
// 	Practical Assignment 2
// 	ITRW 222
import java.util.*;
import java.io.*;
public class CellUsers
{
	public static void main(String [] args)
	{
		CellUser [] cellUsers = new CellUser[5];
		cellUsers[0] = new Contract("John Smith","Bakers street 1","Bakersfield",1548,"AT&T","232-555-8484","24 July 2018","Uncapped","iPhone X",100,0);
		cellUsers[1] = new Contract("Jane Smith","Bakers street 1","Bakersfield",1584,"AT&T","232-555-8585","24 July 2018","Uncapped","iPhone X",100,0);
		cellUsers[2] = new Prepaid("John Doe","Celery Avenue 144","Salad way",5300,"Verizon","888-555-2648",0,120,150);
		cellUsers[3] = new Prepaid("Jane Doe","Celery Avenue 144","Salad way",5300,"Verizon","888-555-4568",0,120,150);
		cellUsers[4] = new Prepaid("Jimmy Jimmerson","Croxley Drive 12","Boxed crescent",2450,"Mobicell","444-555-3333",100,0,200);
		
		for (int k=0;k<5;k++)
		{
			System.out.println(cellUsers[k].toString(getName(),getCellNumber()));
		}
	}
}