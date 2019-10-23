// 	A Barnard 28430093
// 	Practical Assignment 2
// 	ITRW 222

public abstract class Users
{
	private String name;
	private String addressLine1;
	private String addressLine2;
	private int postalCode;
	private String serviceProvider;
	private String cellNumber;
	
	public abstract String toString(String Name, String cell);
	public abstract String showAccount();
	public Users()
	{
		this("","","",0000,"","");
	}
	public Users(String Names, String addr1, String addr2, int postC, String servProvid, String CellNum)
	{
		setName(Names);
		setAddressLine1(addr1);
		setAddressLine2(addr2);
		setPostCode(postC);
		setServiceProvider(servProvid);
		setCellNumber(CellNum);
	}
	public void setName(String nm)
	{
		name = nm;
	}
	public void setAddressLine1(String adL1)
	{
		addressLine1=adL1;
	}
	public void setAddressLine2(String adL2)
	{
		addressLine2 = adL2;
	}
	public void setPostCode(int post)
	{
		postalCode = post;
	}
	public void setServiceProvider(String serv)
	{
		serviceProvider= serv;
	}
	public void setCellNumber(String cel)
	{
		cellNumber = cel;
	}
	public String getName()
	{
		return name;
	}
	public String getAddressLine1()
	{
		return addressLine1;
	}
	public String getAddressLine2()
	{
		return addressLine2;
	}
	public int getPostalCode()
	{
		return postalCode;
	}
	public String getServiceProvider()
	{
		return serviceProvider;
	}
	public String getCellNumber()
	{
		return cellNumber;
	}
	
	
	
	
	
}	