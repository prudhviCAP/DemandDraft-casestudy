package service;

import java.io.IOException;
import java.sql.SQLException;

import bean.DemandDraft;

public interface IDemandDraftService {
	
	int addDemandDraftDetails(DemandDraft demandDraft) throws ClassNotFoundException, SQLException, IOException;
	DemandDraft getDemandDraftDetails(int transactionId) throws ClassNotFoundException, SQLException, IOException;

}
