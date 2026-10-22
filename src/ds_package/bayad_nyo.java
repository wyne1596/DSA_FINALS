package ds_package;

//Thank you Claude
interface OwnList<T>{
	void add(T item);
	void add(int index, T item);
	T get(int index);
	T set(int index, T item);
	T remove(int index);
	int size();
	boolean isEmpty();
}

//Replacement to para sa java.util.ArrayList
class OwnArrayList<T> implements OwnList<T>{
	private Object[] data = new Object[10];
	private int size = 0;
	
	public void add(T item) {
		add(size, item);
	}
	
	public void add(int index, T item) {
		if(index < 0 || index > size) throw new IndexOutOfBoundsException();
		if(size == data.length) grow();
		for(int i = size; i > index; i--) data[i] = data[i - 1];
		data[index] = item;
		size++;
	}	
	
	@SuppressWarnings("unchecked")
	public T get(int index) {
		check(index);
		return (T) data[index];
	}
	
	public T set(int index, T item) {
		check(index);
		T old = get(index);
		data[index] = item;
		return old;
	}
	
	public T remove(int index) {
		check(index);
		T old = get(index);
		for(int i = index; i < size - 1; i++) data[i] = data[i+1];
		data[--size] = null;
		return old;
	}
	
	public int size() {return size;}
	public boolean isEmpty() {return size == 0;}
	
	private void check(int index) {
		if(index < 0 || index >= size) throw new IndexOutOfBoundsException();
	}
	
	private void grow() {
		Object[] bigger = new Object[data.length * 2];
		for(int i=0; i < size ; i++) bigger[i] = data[i];
		data = bigger;
	}
	
}
//Thank you Claude

//this is ung data for Tenant
//nandito yung PersoDetails, balance, and payment and bill history
class Tenant {
	private String tenantID;	//unique ID to na gamit din sa HASH TABLE key naka read only lang to so unchangeable
	private String tenantName;
	private String unitNo;		//read only lang din to
	private double monthlyRent;
	private double currentBalance;	// eto yung amount na di pa nila nababayaran
	private OwnList<Payment> payments;
	private OwnList<UtilityBill> utilityBills;
	
	public String getTenantID() {return tenantID;}
	
	public String getTenantName() {return tenantName;}
	
	public void setTenantName(String tenantName) {this.tenantName = tenantName;}
	
	public String getUnitNo() {return unitNo;}
	
	public double getMonthlyRent() {return monthlyRent;}
	
	public void setMonthlyRent(double monthlyRent) {this.monthlyRent = monthlyRent;}
	
	public double getCurrentBalance() {return currentBalance;}
	
	public void setCurrentBalance(double currentBalance) {this.currentBalance = currentBalance;}
	
	// eto yung responsible sa pag create ng tenant na naka 
	// 0 yung balance and wala pang payment made and yung list ng bill nila
	public Tenant(String tenantID, String tenantName, String unitNo, double monthlyRent) {
		this.tenantID = tenantID;
		this.tenantName = tenantName;
		this.unitNo = unitNo;
		this.monthlyRent = monthlyRent;
		
		this.currentBalance = 0.0;
		
		this.payments = new OwnArrayList<>();
		this.utilityBills = new OwnArrayList<>();
	}
	
	public OwnList<Payment> getPayments(){return payments;}
	
	public OwnList<UtilityBill> getUtilityBills(){return utilityBills;}
	
	// eto yung nag rerecord ng payment ni tenant, like history
	public void addPayment(Payment payment) {this.payments.add(payment);}
	
	// same nag rerecord
    public void addUtilityBill(UtilityBill bill) {this.utilityBills.add(bill);}
    
    // eto yung responsible dun sa print. 
    // pinapakita lng din pala sa payment kung ilang beses na sila nag bayad
    // hindi yung full history ng bayad nila
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

// eto yung utility bill na ichacharge sa tenant
class UtilityBill{
	private String billID;
	private String utilityType;		//example yung Water and Electricity
	private double amount;
	private String billingMonth;	// YYYY-MM format dapat to
	private boolean paid;			// naka false to sya until sa masettle ni tenant ung bill nya
	
	public String getBillID() {return billID;}

    public String getUtilityType() {return utilityType;}

    public double getAmount() {return amount;}

    public String getBillingMonth() {return billingMonth;}

    public boolean isPaid() {return paid;}
    
    // eto yung responsible/ nag mamark na nabayaran na ni tenant lahat ng bill nya
    public void markAsPaid() {this.paid = true;}
    
    // eto yung gumagawa sa bill. Lahat ng bagong bill is unpaid start
    public UtilityBill(String billID, String utilityType, double amount, String billingMonth) {
        this.billID = billID;
        this.utilityType = utilityType;
        this.amount = amount;
        this.billingMonth = billingMonth;
        this.paid = false; 
    }
    
    // eto rin yung print, yung status nya is Paid or Unpaid para di True or False yng maprint
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

// eto yung Payment ni tenant. Lahat naka final na para di mabago yung payment
class Payment{
	private final String paymentID;
    private final String date; 		// format neto: YYYY-MM-DD
    private final double amount;
    private final String appliedTo; // dito yung if nagbayad sila for Rent or sa Utilities

    public String getPaymentID() {return paymentID;}

    public String getDate() {return date;}

    public double getAmount() {return amount;}

    public String getAppliedTo() {return appliedTo;}
    
    // dito nagagawa yung payment record
    public Payment(String paymentID, String date, double amount, String appliedTo) {
        this.paymentID = paymentID;
        this.date = date;
        this.amount = amount;
        this.appliedTo = appliedTo;
    }
    
    //print ulet ng payment history
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


/* 
 									PAKI STUDY NG HASH TABLE
*/

//di pa sure dito sa hash table
class TenantHashTable{
	private static final int INITIAL_CAPACITY = 11;				// eto yung bucket ng hashtable	
	private static final double LOAD_FACTOR_THRESHOLD = 0.75;	//
	
	private Node[] table;	//
	private int size;		// kung ilan na yung tenants na naka store
	
	// responsible sa paggawa ng empty table, yung initial capacity lang din slots nya
	public TenantHashTable() {
		this.table = new Node[INITIAL_CAPACITY];
		this.size = 0;
	}
	
	// di kopa gets pano to gumagana but
	// eto yung nag coconvert sa key
	private int hash(String key) {
		int rawHashCode = key.hashCode();
		int positiveHashCode = rawHashCode & 0x7fffffff;
		return positiveHashCode % this.table.length;
	}
	
	// eto yung entry sa chain ng buckets
	private static class Node {
		private final String key;	// dito yung tenant ID as the key, di na nababago if nalagay na
		private Tenant value;
		private Node next;
		
		public Node(String key, Tenant value) {
			this.key = key;
			this.value = value;
			this.next = null;
		}
	}
	
	// eto yung nag aadd ng tenant sa buong table. True return if bago yung key. False return if nireplaced lang yung existing tenant
	// eto din pala yung nag reresize if yung load factor is exceeded na
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
	    
	    // this, igogrow nya ung table if full na
	    if((double) size / table.length > LOAD_FACTOR_THRESHOLD) {
	    	resize();
	    }
	    return true;    
	}
	
	// eto yung rereturn nya yung tenant/ mga values ng tenant if cinall ung key. Null naman if wala yng key na cinall
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
	
	// dito yung gano na karaming tenant naka stored sa table
	public int size() {return this.size;}
	
	// true to sya if walang tenant na naka stored
	public boolean isEmpty() {return this.size == 0;}
	
	// true to sya if yung tenant with the same key exists sa table
	public boolean containsKey(String key) {
		if(get(key) != null) return true;
		else return false;
	}
	
	// eto yung responsible sa pag remove ng tenant. mag rereturn to ng true if naremoved yung tenant, and false if wala yung tenant na yon sa table
	public boolean remove(String key) {
		Node previous = null;
		int index = this.hash(key);
		Node current = this.table[index];
		
		while(current != null) {
			if(current.key.equals(key)) {
				if(previous == null) {
					table[index] = current.next;
				}
				else {
					previous.next = current.next;
				}
				size--;
				return true;
			}
			previous = current;
			current = current.next;
		}	
		return false;
	}
	
	// eto yung nag rereturn ng mga tenant as a list, hindi sya in order since hindi naman insertion tong HashTable
	public OwnList<Tenant> getAll(){
		OwnList<Tenant> result = new OwnArrayList<>();
		
		for(int i=0; i < table.length; i++) {
			Node current = table[i];
			
			while(current != null) {
				result.add(current.value);
				current = current.next;
			}
		}
		return result;
	}
	
	// eto nag dodouble sa capacity and nag momove sa lahat ng nasa table to thei new bucket
	// di ko masyadong gets to
	private void resize() {
		Node[] oldTable = table;
		int newCapacity = oldTable.length * 2 +1;
		this.table = new Node[newCapacity];
		
		for(int i=0; i < oldTable.length; i++) {
			Node current = oldTable[i];
			
			while(current != null) {
				Node nextNode = current.next;
				int index = hash(current.key);
				current.next = table[index];
				table[index] = current;
				current = nextNode;
			}
		}
	}
}

public class bayad_nyo {
	
	static void check(String label, boolean condition){
		System.out.println((condition ? "PASS":"FAIL" + label));
	}

	public static void main(String[] args) {

// 	PAKI TEST NALANG NG PAYMENT, UTILITYBILL, and TENANTHASHTABLE
		
		/*	example code format:
				check("No. #: Ano yung tinitest", object.method() == something) 			if numbers
				check("No. #: Ano yung tinitest", object.method().equals("something")) 	if String
				check("No. #: Ano yung tinitest", object.method()) 						if boolean (true/false) | !object.method() if expecting false
				check("No. #: Ano yung tinitest", object.method() == null) 				if expecting null
				
			expected ouput:
				PASS: Ano yung tinitest
				FAIL: Ano yung tinitest

			PEDE gumamit ng AI or magtanong sa programmer if nahihirapan
		*/
		
		Tenant tenant = new Tenant("T001", "Juan Tamad", "A02", 2500.00);
		//DITO START TEST SA Tenant class
		
		
		UtilityBill bill = new UtilityBill("B001", "Electricity", 350.00, "2026-10");
		//DITO START TEST SA UtilityBill class
		
		  
		Payment payment = new Payment("P001", "2026-10-05", 2000.00, "RENT");
		//DITO START TEST SA Payment class
		
		
		TenantHashTable table = new TenantHashTable();
		//DITO START TEST SA TenantHashTable class
		
		
	}

}
