// 	A Barnard 28430093
// 	Practical Assignment 1
// 	ITRW 222

public class CellUsers
{
	public static void main(String [] args)
	{
		Users [] clients = new Users[5];
		clients[0] = new Contract("John Smith","Bakers street 1","Bakersfield",1548,"AT&T","232-555-8484","24 July 2018","Uncapped","iPhone X",100,0);
		clients[1] = new Contract("Jane Smith","Bakers street 1","Bakersfield",1584,"AT&T","232-555-8585","24 July 2018","Uncapped","iPhone X",100,0);
		clients[2] = new Prepaid("John Doe","Celery Avenue 144","Salad way",5300,"Verizon","888-555-2648",0,120,150);
		clients[3] = new Prepaid("Jane Doe","Celery Avenue 144","Salad way",5300,"Verizon","888-555-4568",0,120,150);
		clients[4] = new Prepaid("Jimmy Jimmerson","Croxley Drive 12","Boxed crescent",2450,"Mobicell","444-555-3333",100,0,200);
		
		
	    System.out.println(" All Clients");
		
		for(int i = 0; i <5; i++)
		{
			System.out.println("\n");
			System.out.println(clients[i]);
			System.out.println("\n");
			System.out.println(clients[i].showAccount());
		}
	}
}