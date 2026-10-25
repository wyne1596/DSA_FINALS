package ds_package;
import ds_package.OwnList;
import ds_package.OwnArrayList;
import ds_package.Tenant;
import ds_package.UtilityBill;
import ds_package.Payment; 
import ds_package.TenantHashTable;
import ds_package.TrackerService; 

class ValidationHelper{
	
	public boolean isNonEmpty(String text) {
		if(text == null || text.trim().length() == 0) return false;
		return true;
	}
	
	public boolean isPositiveAmount(String text) {
		if(text == null) return false;
		try {
			double value = Double.parseDouble(text);
			if(Double.isNaN(value) || Double.isInfinite(value)) return false;
			return value > 0;
		}
		catch(NumberFormatException ex) {return false;}
	}
	
	public boolean isValidMonth(String text) {
		if(text == null) return false;
		String trimmed = text.trim();
		if(!trimmed.matches("\\d{4}-\\d{2}")) return false;
		int month = Integer.parseInt(trimmed.substring(5, 7));
		return month >= 1 && month <= 12; 
	}
}



public class TestingArea {
	static void check(int num, String label, boolean condition){
		System.out.println((condition ? "PASS: ":"FAIL: ") + "No. " +num+ " - "+label);
	}
	
	public static void println(Object obj) {
		System.out.println(obj);
	}
	public static void print(Object obj) {
		System.out.print(obj);
	}
	
	public static void main(String[] args) {
		TenantHashTable table = new TenantHashTable();
		
		table.printTable();
		
		//	1
//		check(1, "ID T001", tenant.getTenantID().equals("T001"));
//		check(1, "Name Juan Tamad", tenant.getTenantName().equals("Juan Tamad"));
//		check(1, "Unit A02", tenant.getUnitNo().equals("A02"));
//		check(1, "Rent 2500.00", tenant.getMonthlyRent() == 2500.00);
//		check(1, "Balance 0.00", tenant.getCurrentBalance() == 0.00);

		//	2
//		tenant.setTenantName("Maria Santos");
//		tenant.setMonthlyRent(3000.00);
//		tenant.setCurrentBalance(500.00);
//		check(2, "Name changed", tenant.getTenantName().equals("Maria Santos"));
//		check(2, "Rent changed", tenant.getMonthlyRent() == 3000.00);
//		check(2, "Balance changed", tenant.getCurrentBalance() == 500.00);

		//	3
//		check(3, "Payment is 0", tenant.getPayments().size() == 0);
//		tenant.addPayment(payment);
//		check(3, "size is 1", tenant.getPayments().size() == 1);
//		check(3, "first item is payment", tenant.getPayments().get(0).equals(payment)); 

		//	4
//		check(4, "utility bill size is 0", tenant.getUtilityBills().size() == 0);
//		tenant.addUtilityBill(bill);
//		check(4, "utility bill size is 1", tenant.getUtilityBills().size() == 1);
//		check(4, "utility bill 0 is bill", tenant.getUtilityBills().get(0).equals(bill));
		
		//	5
//		tenant.addPayment(payment);
//		tenant.addPayment(new Payment("P002", "2026-10-06", 500.00, "UTILITY"));
//		tenant.addUtilityBill(bill);
//		tenant.addUtilityBill(new UtilityBill("B002", "WATER", 200.00, "2026-10"));
//		check(5, "payment 0 is P001", tenant.getPayments().get(0).getPaymentID().equals("P001"));
//		check(5, "payment 1 is P002", tenant.getPayments().get(1).getPaymentID().equals("P002"));
//		check(5, "bill 0 is B001", tenant.getUtilityBills().get(0).getBillID().equals("B001"));
//		check(5, "bill 1 is B002", tenant.getUtilityBills().get(1).getBillID().equals("B002"));

		//	6
//		check(6, "String format is correct", tenant.toString().equals("Tenant [ID: T001 | Name: Juan Tamad | Unit: A02 | Monthly Rent: ₱2500.00 | Balance: ₱0.00 | Payments: 0]"));
//		tenant.addPayment(payment);
//		check(6, "Payment now has 1", tenant.toString().contains("Payments: 1"));
		
		//	7
//		check(7, "ID matched", bill.getBillID().equals("B001"));
//		check(7, "Type matched", bill.getUtilityType().equals("Electricity"));
//		check(7, "Amount matched", bill.getAmount() == 350.00);
//		check(7, "Month matched", bill.getBillingMonth().equals("2026-10"));
		
		//	8
//		bill.isPaid();
//		check(8, "bill paid is FALSE", !bill.isPaid());
		
		//	9
//		bill.markAsPaid();
//		check(8, "bill paid is TRUE", bill.isPaid());
		
		//	10
//		check(10, "Contains Unpaid", bill.toString().contains("Status: Unpaid"));
//		bill.markAsPaid();
//		check(10, "Contains Paid", bill.toString().contains("Status: Paid"));
		
		//	11
//		check(11, "ID matched", payment.getPaymentID().equals("P001"));
//		check(11, "DATE matched", payment.getDate().equals("2026-10-05"));
//		check(11, "Amount matched", payment.getAmount() == 2000.00);
//		check(11, "APPLIED matched", payment.getAppliedTo().equals("RENT"));
		
		//	12	
//		check(12,"format matched", payment.toString().equals("Payment [ID: P001 | Date: 2026-10-05 | Amount: ₱2000.00 | Applied To: RENT]"));
		
		//	13
//		check(13, "Size is 0", table.size() == 0);
//		check(13, "put() is true", table.put("T001", tenant));
//		check(13, "Size now 1", table.size() == 1);
		
		//	14
//		table.put("T001", tenant);
//		check(14, "Return should be false", !table.put("T001", new Tenant("T001", "Ana Santos", "A02", 2500.00)) );
//		check(14, "Check name if changed", table.get("T001").getTenantName().equals("Ana Santos"));
//		check(14, "size still 1", table.size() == 1);
		
		//	15
//		table.put("T001", tenant);
//		check(15, "Found T001", table.get("T001") != null);
		
		//	16
//		table.put("T001", tenant);
//		check(16, "No T999", table.get("T999") == null);
		
		//	17
//		table.put("T001", tenant);
//		table.put("T013", new Tenant("T013","Cara Lim", "A03", 3000.00));
//		table.put("T025", new Tenant("T025","Ben Reyes", "A04", 2800.00));
//		check(17, "Found T001", table.get("T001") != null);
//		check(17, "Found T013", table.get("T013") != null);
//		check(17, "Found T025", table.get("T025") != null);
		
		//	18
//		table.put("T001", tenant);
//		check(18, "contains T001", table.containsKey("T001"));
//		check(18, "contains T999", !table.containsKey("T999"));
		
		//	19
//		table.put("T001", tenant);
//		table.put("T013", new Tenant("T013","Cara Lim", "A03", 3000.00));
//		table.put("T025", new Tenant("T025","Ben Reyes", "A04", 2800.00));
//		check(19, "T025 Removed", table.remove("T025"));
//		check(19, "Size is 2", table.size() == 2);
//		check(19, "T001 is here", table.get("T001") != null);	
//		check(19, "T013 is here", table.get("T013") != null);	
		
		//	20
//		table.put("T001", tenant);
//		table.put("T013", new Tenant("T013","Cara Lim", "A03", 3000.00));
//		table.put("T025", new Tenant("T025","Ben Reyes", "A04", 2800.00));
//		check(20, "Removed T013 and size is now 2", table.remove("T013") && table.size() == 2);
//		check(20, "Removed T001 and size is now 1", table.remove("T001") && table.size() == 1);
//		check(20, "T025 is here", table.get("T025") != null);
		
		//	21
//		table.put("T001", tenant);
//		int size = table.size();
//		check(21, "Nothing is removed", !table.remove("T999"));
//		check(21, "Still the same size", table.size() == size);
		
		//	22
//		table.put("T001", tenant);
//		table.put("T011", new Tenant("T011", "Lea Ong", "A05", 2600.00));
//		OwnList<Tenant> all = table.getAll();
//		check(22, "Size is = table size", all.size() == table.size());
//		boolean found = false;
//		for(int i=0; i < all.size(); i++) {
//			if(all.get(i).getTenantID().equals("T011")) found = true;
//		}
//		check(22, "T011 is in the list", found);
		
		//	23
//		check(23, "Table is Empty and size 0", table.isEmpty() && table.getAll().size() == 0);
//		table.put("T001", tenant);
//		check(23, "Table is NOT Empty and size 1", !table.isEmpty() && table.getAll().size() == 1);
//		table.remove("T001");
//		check(23, "Table is Empty and size 0", table.isEmpty() && table.getAll().size() == 0);
		
		//	24
//		for(int i=1; i<=9; i++) {
//			String id = "R00"+i;
//			table.put(id, new Tenant(id, "Test", "UI", 4000.00)) ;
//		}
//		for(int i=1; i<=9; i++) {
//			check(24, "R00"+i+" is here", table.get("R00"+1) != null);
//		}
//		check(24, "Size is 9", table.size() == 9);
		
		//	25
//		table.put("T001", tenant);
//		table.put("polygenelubricants", new Tenant("polygenelubricants", "Test", "U01", 1000.00));
//		check(25, "key Found", table.get("polygenelubricants") != null);
//		Tenant t1 = table.get("T001");
//		Tenant t2 = table.get("T001");
//		check(25, "Same key found same tenant", t1 != null && t1 == t2);
		
		//	26
//		check(26, "Return Null", table.get("T001") == null);
//		check(26, "Return FALSE", !table.remove("T001"));
//		check(26, "size is 0", table.getAll().size() == 0);
		
	}

}
