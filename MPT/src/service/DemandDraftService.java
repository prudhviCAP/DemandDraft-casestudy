package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bean.DemandDraft;
import dao.DemandDraftDAO;
import dao.IDemandDraftDAO;
import exception.DemandDraftException;

public class DemandDraftService implements IDemandDraftService {

	IDemandDraftDAO iDemandDraftDAO = null;
	
	@Override
	public int addDemandDraftDetails(DemandDraft demandDraft) throws ClassNotFoundException, SQLException, IOException {
		int transactionId = 0;
		iDemandDraftDAO = new DemandDraftDAO();
		transactionId = iDemandDraftDAO.addDemandDraftDetails(demandDraft);
		return transactionId;
	}

	@Override
	public DemandDraft getDemandDraftDetails(int transactionId) throws ClassNotFoundException, SQLException, IOException {
		DemandDraft demandDraft = null;
		iDemandDraftDAO = new DemandDraftDAO();
		demandDraft = iDemandDraftDAO.getDemandDraftDetails(transactionId);
		return demandDraft;
	}
	
	public void validateDemandDraftDetails(DemandDraft demandDraft) throws DemandDraftException
	{
		List<String> list = new ArrayList<>();
		
		if(!(isValidName(demandDraft.getCustomerName())))
		{
			list.add(" customer name must be minimum 5 characters ...");
		}
		
		if(!(isValidPhoneNumber(demandDraft.getCustomerPhoneNumber())))
		{
			list.add(" phone number must start with 6,7,8,9 only and must be 10 numbers ");
		}
		
		if(!(isValidName(demandDraft.getInFavorOf())))
		{
			list.add(" In Favor of name must be minimum 5 characters ...");
		}
		
		if(!(isValidAmount(demandDraft.getDemandDraftAmount())))
		{
			list.add(" enter amount great than 0 ");
		}
		
		if(!(isValidRemark(demandDraft.getRemarks())))
		{
			list.add(" remark must be less than 100 ");
		}
		
		if(!list.isEmpty())
		{
			throw new DemandDraftException(list + " ");
		}
	//	return false;
	}
	
	public boolean isValidName(String customerName)
	{
		Pattern pat1 = Pattern.compile("^[A-Z][A-Za-z]{4,}$");
		Matcher mat1 = pat1.matcher(customerName);
		return mat1.matches();
	}
	
	public boolean isValidPhoneNumber(String phoneNumber)
	{
		Pattern pat2 = Pattern.compile("[6-9][0-9]{9}");
		Matcher mat2 = pat2.matcher(phoneNumber);
		return mat2.matches();
	}
	
	public boolean isValidAmount(Double amount)
	{
		return amount>0;
	}
	
	public boolean isValidRemark(String remark)
	{
		Pattern pat3 = Pattern.compile("[A-Za-z\\s]{0,}");
		Matcher mat3 = pat3.matcher(remark);
		return mat3.matches();
	}
	
	public boolean isValidId(int transactionId)
	{
		return transactionId>10000;
	}
	
}
