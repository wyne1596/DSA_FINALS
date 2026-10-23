package ds_package;
import ds_package.OwnList;
import ds_package.OwnArrayList;
import ds_package.Tenant;
import ds_package.UtilityBill;
import ds_package.Payment; 
import ds_package.TenantHashTable;

public class TestingArea {
	static void check(int num, String label, boolean condition){
		System.out.println((condition ? "PASS: ":"FAIL: ") + "No. " +num+ " - "+label);
	}
	
	public static void main(String[] args) {

//	 	PAKI TEST NALANG NG PAYMENT, UTILITYBILL, and TENANTHASHTABLE
		
			/*	example code format:
					check(No.#, "Ano yung tinitest", object.method() == something) 			if numbers
					check(No.#, "Ano yung tinitest", object.method().equals("something")) 	if String
					check(No.#, "Ano yung tinitest", object.method()) 						if boolean (true/false) 
													!object.method() 						if expecting false
													
					check(No.#, "Ano yung tinitest", object.method() == null) 				if expecting null
																	  != null				if NOT expecting null
				
				expected output:
					PASS: No. # - Ano yung tinitest
					FAIL: No. # - Ano yung tinitest

				PEDE gumamit ng AI or magtanong sa programmer if nahihirapan
			*/

		Tenant tenant = new Tenant("T001", "Juan Tamad", "A02", 2500.00);
		UtilityBill bill = new UtilityBill("B001", "Electricity", 350.00, "2026-10");
		Payment payment = new Payment("P001", "2026-10-05", 2000.00, "RENT");
		TenantHashTable table = new TenantHashTable();
		
		

		
	}

}
