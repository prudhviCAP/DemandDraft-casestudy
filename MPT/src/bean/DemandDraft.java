package bean;

public class DemandDraft {
	
	private String customerName;
	private String customerPhoneNumber;
	private String inFavorOf;
	private double demandDraftAmount;
	private String remarks;
	
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}
	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}
	public String getInFavorOf() {
		return inFavorOf;
	}
	public void setInFavorOf(String inFavorOf) {
		this.inFavorOf = inFavorOf;
	}
	public double getDemandDraftAmount() {
		return demandDraftAmount;
	}
	public void setDemandDraftAmount(double demandDraftAmount) {
		this.demandDraftAmount = demandDraftAmount;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	@Override
	public String toString() {
		return "DemandDraft [customerName=" + customerName + ", customerPhoneNumber=" + customerPhoneNumber
				+ ", inFavorOf=" + inFavorOf + ", demandDraftAmount=" + demandDraftAmount + ", remarks=" + remarks
				+ "]";
	}
	

}
