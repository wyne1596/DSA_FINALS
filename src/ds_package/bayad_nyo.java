package ds_package;
import java.util.List;
import java.util.ArrayList;

class Tenant {
	private String tenantID;
	private String tenantName;
	private String unitNo;
	private double monthlyRent;
	private double currentBalance;
	private List<Payment> payments;
	private List<UtilityBill> utilityBills;
	
	
	public String getTenantID() {return tenantID;}
	
	public String getTenantName() {return tenantName;}
	
	public void setTenantName(String tenantName) {this.tenantName = tenantName;}
	
	public String getUnitNo() {return unitNo;}
	
	public double getMonthlyRent() {return monthlyRent;}
	
	public void setMonthlyRent(double monthlyRent) {this.monthlyRent = monthlyRent;}
	
	public double getCurrentBalance() {return currentBalance;}
	
	public void setCurrentBalance(double currentBalance) {this.currentBalance = currentBalance;}
	
	public Tenant(String tenantID, String tenantName, String unitNo, double monthlyRent) {
		this.tenantID = tenantID;
		this.tenantName = tenantName;
		this.unitNo = unitNo;
		this.monthlyRent = monthlyRent;
		
		this.currentBalance = 0.0;
		
		this.payments = new ArrayList<>();
		this.utilityBills = new ArrayList<>();
	}
	
	public List<Payment> getPayments(){return payments;}
	
	public List<UtilityBill> getUtilityBills(){return utilityBills;}
	
	public void addPayment(Payment payment) {this.payments.add(payment);}

    public void addUtilityBill(UtilityBill bill) {this.utilityBills.add(bill);}
    
    @Override
    public String toString() {
    	int totalPayments = this.payments.size();
    	
    	return String.format(
    			"Tenant [ID: %s | Name: %s | Unit: %s | Monthly Rent: ₱%.2f | Balance: ₱%.2f | Payments: %d]",
				this.tenantID,
				this.tenantName,
				this.unitNo,
				this.monthlyRent,
				this.currentBalance,
				totalPayments		
    	);
    }
}

class UtilityBill{
	private String billID;
	private String utilityType;
	private double amount;
	private String billingMonth;
	private boolean paid;
	
	public String getBillID() {return billID;}

    public String getUtilityType() {return utilityType;}

    public double getAmount() {return amount;}

    public String getBillingMonth() {return billingMonth;}

    public boolean isPaid() {return paid;}
    
    public void markAsPaid() {this.paid = true;}
    
    public UtilityBill(String billID, String utilityType, double amount, String billingMonth) {
        this.billID = billID;
        this.utilityType = utilityType;
        this.amount = amount;
        this.billingMonth = billingMonth;
        this.paid = false; 
    }
    
    @Override
    public String toString() {
        String status = this.paid ? "Paid" : "Unpaid";

        return String.format(
            "UtilityBill [ID: %s | Type: %s | Month: %s | Amount: ₱%.2f | Status: %s]",
            this.billID,
            this.utilityType,
            this.billingMonth,
            this.amount,
            status
        );
    }
}

class Payment{
	private final String paymentID;
    private final String date; 
    private final double amount;
    private final String appliedTo; 

    public String getPaymentID() {return paymentID;}

    public String getDate() {return date;}

    public double getAmount() {return amount;}

    public String getAppliedTo() {return appliedTo;}
    
    public Payment(String paymentID, String date, double amount, String appliedTo) {
        this.paymentID = paymentID;
        this.date = date;
        this.amount = amount;
        this.appliedTo = appliedTo;
    }
    
    @Override
    public String toString() {
        return String.format(
            "Payment [ID: %s | Date: %s | Amount: ₱%.2f | Applied To: %s]",
            this.paymentID,
            this.date,
            this.amount,
            this.appliedTo
        );
    }
}

//di pa sure dito sa hash table
class TenantHashTable{
	private static final int INITIAL_CAPACITY = 11;
	private static final double LOAD_FACTOR_THRESHOLD = 0.75;
	
	private Node[] table;
	private int size;
	
	public TenantHashTable() {
		this.table = new Node[INITIAL_CAPACITY];
		this.size = 0;
	}
	
	private int hash(String key) {
		int rawHashCode = key.hashCode();
		int positiveHashCode = rawHashCode & 0x7fffffff;
		return positiveHashCode % this.table.length;
	}
	
	private static class Node {
		private final String key;
		private Tenant value;
		private Node next;
		
		public Node(String key, Tenant value) {
			this.key = key;
			this.value = value;
			this.next = null;
		}
	}
	
	public boolean put(String key, Tenant value) {
		int index = this.hash(key);

	    Node current = this.table[index];
	    while (current != null) {
	        if (current.key.equals(key)) {
	            current.value = value; 
	            return false;         
	        }
	        current = current.next;
	    }
	    
	    Node newNode = new Node(key, value);
	    newNode.next = this.table[index]; 
	    this.table[index] = newNode; 
	    
	    this.size++;
	    
	    return true;    
	}
	
	public Tenant get(String key) {
	    int index = this.hash(key);
	    
	    Node current = this.table[index];
	    while (current != null) {
	        if (current.key.equals(key)) {
	            return current.value; 
	        }
	        current = current.next; 
	    }
	    
	    return null; 
	}
	
	public int size() {
	    return this.size;
	}
	
	public boolean isEmpty() {
	    return this.size == 0;
	}
}

public class bayad_nyo {

	public static void main(String[] args) {

	}

}
