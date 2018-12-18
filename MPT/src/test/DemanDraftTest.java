package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bean.DemandDraft;
import dao.DemandDraftDAO;

public class DemanDraftTest {
	
	DemandDraft demandDraft = null;
	DemandDraftDAO demandDraftDAO = null;
	
	
	
	@Test
	public void testAddDraft() throws ClassNotFoundException, SQLException, IOException
	{
		
		demandDraft = new DemandDraft();
		demandDraftDAO = new DemandDraftDAO();
		
		demandDraft.setCustomerName("RamanaDJ");
		demandDraft.setCustomerPhoneNumber("7793996595");
		demandDraft.setInFavorOf("SufallDas");
		demandDraft.setDemandDraftAmount(20000);
		demandDraft.setRemarks("dowry");
		assertEquals(10006,demandDraftDAO.addDemandDraftDetails(demandDraft));
		System.out.println(" test case ");
	}
	
	
	@Test
	public void testGetDraftDetail() throws ClassNotFoundException, SQLException, IOException
	{
		demandDraftDAO = new DemandDraftDAO();
		assertNotNull(demandDraftDAO.getDemandDraftDetails(1003));
		System.out.println("runs after all tests ");
	}
	
	@BeforeEach
	public void testDemo1()
	{
		System.out.println(" run before every test ");
		//assertEquals(1,1);
	}

}
