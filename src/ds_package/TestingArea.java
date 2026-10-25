package ds_package;
import ds_package.OwnList;
import ds_package.OwnArrayList;
import ds_package.Tenant;
import ds_package.UtilityBill;
import ds_package.Payment; 
import ds_package.TenantHashTable;
import ds_package.TrackerService; 


public class TestingArea {
	static void check(int num, String label, boolean condition){
		System.out.println((condition ? "PASS: ":"FAIL: ") + "No. " +num+ " - "+label);
	}
	
	static void check(String label, boolean condition){
		System.out.println((condition ? "PASS: ":"FAIL: ") + label);
	}
	
	public static void println(Object obj) {
		System.out.println(obj);
	}
	public static void print(Object obj) {
		System.out.print(obj);
	}
	
	public static void main(String[] args) {
		ValidationHelper helper = new ValidationHelper();

		// dito codes
		
		
	}

}


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
	
	public boolean isValidDate(String text) {
		
		if(text == null) return false;
	    String trimmed = text.trim();
	    if(!trimmed.matches("\\d{4}-\\d{2}-\\d{2}")) return false;

	    int year = Integer.parseInt(trimmed.substring(0, 4));
	    int month = Integer.parseInt(trimmed.substring(5, 7));
	    int day = Integer.parseInt(trimmed.substring(8, 10));

	    if(month < 1 || month > 12) return false; 
	    return day >= 1 && day <= daysInMonth(year, month);
	}
	
	public boolean isAppliedTo(String text) {
		
		if(text == null) return false;
	    String trimmed = text.trim();
	    return trimmed.equalsIgnoreCase("RENT") || trimmed.equalsIgnoreCase("UTILITY");
	}
	
	private boolean isLeapYear(int year) {
		
		return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
	}
	
	private int daysInMonth(int year, int month) {
		
		if(month == 2) return isLeapYear(year) ? 29 : 28;
	    if(month == 4 || month == 6 || month == 9 || month == 11) return 30;
	    return 31;
	}
}



