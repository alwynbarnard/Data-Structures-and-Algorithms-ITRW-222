// 	A Barnard 28430093
// 	Practical Assignment 1
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
		super();
		contractStartDate = "";
		contractDescription = "";
		phoneDescription = "";
		freeMinLeft = 0;
		freeSMSLeft = 0;
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
	
	public String toString()
	{
		return super.toString() + " Phone Description: " + getPhoneDescription();
	}
	public String showAccount()
	{
		return ("Name: "+ getName() + "\n Address Line 1: "+ getAddressLine1() + 	"\n Address Line 2: "+ getAddressLine2() + "\n Postal Code: "+ getPostalCode() + "\n Service Provider: " + getServiceProvider() + "\n Cell Number: " + getCellNumber() + "\n Contract Start Date: "+ getContractStart() + "\n Contract Description: "+getContractDescription() + "\n Phone Description: " + getPhoneDescription() +"\n Free minutes left: " +getFreeMins() + "\n Free SMSs left: "+ getFreeSMS());
	}
}