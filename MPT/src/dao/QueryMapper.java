package dao;

public interface QueryMapper {
	
	String INSERT_DEMAND_DRAFT_QUERY = "insert into demand_draft values(transaction_id_seq.nextval,?,?,?,sysdate,?,?,?)";
	String GET_DETAILS_BY_ID_QUERY = "select transaction_id from demand_draft where customer_name = ?";
	
	//query for get demand draft by transaction id
	String GET_DEMAND_QUERY = "select * from demand_draft where transaction_id = ?";

}
