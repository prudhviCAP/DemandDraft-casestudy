package ui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import bean.DemandDraft;
import exception.DemandDraftException;
import service.DemandDraftService;
import service.IDemandDraftService;

public class Client
{
	static IDemandDraftService iDemandDraftService = null;
	static DemandDraftService demandDraftService = null;
	static DemandDraft demandDraft =null;
	static Scanner in= new Scanner(System.in);
	
	static Logger logger = Logger.getRootLogger();
	public static void main(String[] args) throws DemandDraftException, ClassNotFoundException, SQLException, IOException
	{
		PropertyConfigurator.configure("resources//log4j.properties");
		while(true)
		{
			System.out.println("*---*---*---*---*---*---*---*---*---*");
			System.out.println("-------*---Menu---*--------");
			System.out.println("*---*---*---*---*---*---*---*---*---*");
			System.out.println(" 1. Enter Demand Draft Details ");
			System.out.println(" 2. Get Demand Draft Details  ");
			System.out.println(" 3. Exit ");
			
			int option;
			System.out.println(" enter option from above menu  ");
			option = in.nextInt();
			switch(option)
			{
				case 1:
					demandDraft = populateDemandDraft();
					demandDraftService = new DemandDraftService();
					iDemandDraftService = new DemandDraftService();
					
						try
						{
							int transactionId;
							transactionId = iDemandDraftService.addDemandDraftDetails(demandDraft);
							System.out.println(" Your Demand Draft request has been successfully registered along with "+transactionId);	
						}
						catch(Exception dde)
						{
							System.out.println(dde.getMessage());
						}
					break;
				case 2 :
					demandDraftService = new DemandDraftService();
					iDemandDraftService = new DemandDraftService();
					demandDraft = new DemandDraft();
					int transactionId = 0;
					System.out.println("enter transaction id to get details ");
					transactionId = in.nextInt();
					
					if(demandDraftService.isValidId(transactionId))
					{
						demandDraft = iDemandDraftService.getDemandDraftDetails(transactionId);
						System.out.println(demandDraft);
						logger.info(demandDraft);
					}
					else
					{
	
						System.err.println(" wrong implementation ");
					}
					break;
				default :
					in.close();
					System.out.println("enter valid option");
						System.exit(0);
			}
		}
		
		
	}
	
	static public DemandDraft populateDemandDraft() throws DemandDraftException
	{
		demandDraft = new DemandDraft();
		
		System.out.println("***------enter demand draft details-----***");
		System.out.println("enter customer name : ");
		demandDraft.setCustomerName(in.next());
		
		System.out.println(" enter customer phone number : ");
		demandDraft.setCustomerPhoneNumber(in.next());
		
		System.out.println(" enter reciever name : ");
		demandDraft.setInFavorOf(in.next());
		
		System.out.println(" enter amount in demand draft : ");
		demandDraft.setDemandDraftAmount(in.nextDouble());
		
		System.out.println(" enter remarks in draft : ");
		demandDraft.setRemarks(in.next());
		
		demandDraftService = new DemandDraftService();
		try
		{
		demandDraftService.validateDemandDraftDetails(demandDraft);
		return demandDraft;
		}
		catch(DemandDraftException dde)
		{
			logger.error(dde);
			System.err.println(dde.getMessage());
		}
		return demandDraft;
	}
}
