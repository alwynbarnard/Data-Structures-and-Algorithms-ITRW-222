// 	A Barnard 28430093
// 	Practical Assignment 2
// 	ITRW 222

public class Prepaid extends Users
{
	public int dataBalance;
	public int minBalance;
	public int amountBalance;
	public Prepaid()
	{
		this("","","",0000,"","",0,0,0);
	}
	public Prepaid(String Names, String addr1, String addr2, int postC, String servProvid, String CellNum,int data, int minutes, int amount)
	{
		super(Names,addr1,addr2,postC,servProvid,CellNum);
		setDatabalance(data);
		setMinBalance(minutes);
		setAmountBalance(amount);
	}
	public String toString(String Name, String cell)
	{
		return Name + cell + amountBalance;
	}
	public String showAccount()
	{
		return ("Name: "+ getName() + "Adress Line 1: "+ getAddressLine1() + 	"Adress Line 2: "+ getAddressLine2() + "Postal Code: "+ getPostalCode() + "Service Provider: " + getServiceProvider() + "Cell Number: " + getCellNumber() + "Data Balance: "+ getDatabalance() + "Minutes Balance: " +getMinBalance() + "Amount Balance: "+ getAmountBalance());
	}
	public void setDatabalance(int Data)
	{
		dataBalance = Data;
	}
	public void setMinBalance(int minu)
	{
		minBalance = minu;
	}
	public void setAmountBalance(int amoun)
	{
		amountBalance = amoun;
	}
	public int getDatabalance()
	{
		return dataBalance;
	}
	public int getMinBalance()
	{
		return minBalance;
	}
	public int getAmountBalance()
	{
		return amountBalance;
	}

}