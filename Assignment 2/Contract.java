// 	A Barnard 28430093
// 	Practical Assignment 2
// 	ITRW 222

public class Contract extends Users
{
	public String contractStartDate;
	public String contractDescription;
	public String phoneDescription;
	public int freeMinLeft;
	public int freeSMSLeft;
	
	public Contract()
	{
		this("","","",0000,"","","","","",0,0);
	}
	public Contract(String Names, String addr1, String addr2, int postC, String servProvid, String CellNum,String contractStart, String contractDesc, String phoneDesc, int freeMin, int freeSMS)
	{
		super(Names,addr1,addr2,postC,servProvid,CellNum);
		setContractStart(contractStart);
		setContractDescrip(contractDesc);
		setPhoneDescrip(phoneDesc);
		setFreeMins(freeMin);
		setFreeSMS(freeSMS);
	}
	
	public void setContractStart(String contrStr)
	{
		contractStartDate = contrStr;
	}
	public void setContractDescrip(String contrDes)
	{
		contractDescription = contrDes;
	}
	public void setPhoneDescrip(String phoneD)
	{
		phoneDescription = phoneD;
	}
	public void setFreeMins(int freeMins)
	{
		freeMinLeft = freeMins;
	}
	public void setFreeSMS(int freeSMSS)
	{
		freeSMSLeft = freeSMSS;
	}
	
	public String getContractStart()
	{
		return contractStartDate;
	}
	public String getContractDescription()
	{
		return contractDescription;
	}
	public String getPhoneDescription()
	{
		return phoneDescription;
	}
	public int getFreeMins()
	{
		return freeMinLeft;
	}
	public int getFreeSMS()
	{
		return freeSMSLeft;
	}
	
	public String toString(String Name, String cell)
	{
		return Name + cell + phoneDescription;
	}
	public String showAccount()
	{
		return ("Name: "+ getName() + "Adress Line 1: "+ getAddressLine1() + 	"Adress Line 2: "+ getAddressLine2() + "Postal Code: "+ getPostalCode() + "Service Provider: " + getServiceProvider() + "Cell Number: " + getCellNumber() + "Contract Start Date: "+ getContractStart() + "Contract Description: "+getContractDescription() + "Phone Description: " + getPhoneDescription() +"Free minutes left: " +getFreeMins() + "Free SMSs left: "+ getFreeSMS());
	}
}