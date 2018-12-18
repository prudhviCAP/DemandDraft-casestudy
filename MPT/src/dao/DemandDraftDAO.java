package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import bean.DemandDraft;
import exception.DemandDraftException;
import util.DBConnection;

public class DemandDraftDAO implements IDemandDraftDAO {
	
	Logger logger=Logger.getRootLogger();
	public DemandDraftDAO()
	{
		
	PropertyConfigurator.configure("resources//log4j.properties");
	
	}

	@Override
	public int addDemandDraftDetails(DemandDraft demandDraft) throws ClassNotFoundException, SQLException, IOException {
		Connection con = DBConnection.getConnection();
		PreparedStatement pst1 = null;
		PreparedStatement pst2 = null;
		ResultSet resultSet = null;
		int transactionId =0;
		int queryResult = 0;
		try 
		{
			double amount = demandDraft.getDemandDraftAmount();
			int commission=0;
			if(amount<=5000)
			{
				commission  = 10;
			}
			else if(amount>5000 && amount<=10000)
			{
				commission = 41;
			}
			else if(amount>10000 && amount<=100000)
			{
				commission = 51;
			}
			else if(amount>100000 && amount<=500000)
			{
				commission = 306;
			}
			
			pst1 = con.prepareStatement(QueryMapper.INSERT_DEMAND_DRAFT_QUERY);
			pst1.setString(1, demandDraft.getCustomerName());
			pst1.setString(2, demandDraft.getInFavorOf());
			pst1.setString(3, demandDraft.getCustomerPhoneNumber());
			pst1.setDouble(4, demandDraft.getDemandDraftAmount());
			pst1.setInt(5, commission);
			pst1.setString(6, demandDraft.getRemarks());
			pst1.executeUpdate();
			
			pst2 = con.prepareStatement(QueryMapper.GET_DETAILS_BY_ID_QUERY);
			queryResult++;
			pst2.setString(1, demandDraft.getCustomerName());
			resultSet = pst2.executeQuery();
			while(resultSet.next())
			{
				transactionId = resultSet.getInt(1);
			}
			if(queryResult==0)
			{
				logger.error("Insertion failed ");
				throw new DemandDraftException("Inserting donor details failed ");

			}
			else
			{
				logger.info("Donor details added successfully:");
				return transactionId;
			}
		}
		catch(Exception dde)
		{
			logger.info(dde.getMessage());
			System.err.println(dde.getMessage());
		}
		return 0;
	}

	@Override
	public DemandDraft getDemandDraftDetails(int transactionId) throws ClassNotFoundException, SQLException, IOException {
		Connection con = DBConnection.getConnection();
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		DemandDraft demandDraft = null;
		int queryResult = 0;
		try
		{
			pst = con.prepareStatement(QueryMapper.GET_DEMAND_QUERY);
			queryResult++;
			pst.setInt(1, transactionId);
			resultSet = pst.executeQuery();
			
			demandDraft = new DemandDraft();
			while(resultSet.next())
			{
				demandDraft.setCustomerName(resultSet.getString(2));
				demandDraft.setCustomerPhoneNumber(resultSet.getString(4));
				demandDraft.setInFavorOf(resultSet.getString(3));
				demandDraft.setDemandDraftAmount(resultSet.getDouble(6));
				demandDraft.setRemarks(resultSet.getString(8));
			}
			
			if(queryResult==0)
			{
				logger.error("Insertion failed ");
				throw new DemandDraftException("viewing draft details failed");

			}
			else
			{
				logger.info("Donor details added successfully:");
				return demandDraft;
			}
		}
		catch(Exception dde)
		{
			logger.error(dde);
			System.err.println(dde.getMessage());
		}
		
		return null;
	}


}
