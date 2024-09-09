import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateInput {
    private static Scanner scanner = new Scanner(System.in);
    private static final int MAX_INT = Integer.MAX_VALUE;
    private static final long MAX_LONG = Long.MAX_VALUE;
    private static final int MAX_STRING_LENGTH = 500;
    private static final String REGEX = "^[a-zA-Z\\s]*$";
    private static final String[] validItemNames = {
        "Pistachios",
        "Walnuts",
        "Artichokes",
        "Celery",
        "Garlic",
        "Prunes",
        "Raisins",
        "Olives",
        "Peaches",
        "Nectarines",
        "Kiwi",
        "Pomegranates",
        "Honeydew",
        "Figs",
        "Plums",
        "Grapes",
        "Rice",
        "Lima Beans",
        "Corn",
        "Soybeans",
        "Winter Wheat",
        "Durum Wheat",
        "Spring Wheat",
        "Cancel"
    };

    public static int validInt(int min, int max) {
        int input;
        
        while(true){
            // System.out.print("Please enter an integer: ");
            try {
                input = Integer.parseInt(scanner.nextLine());

                if(input >= min && input <= max && input < MAX_INT){
                    
                    return input;
                } else {
                    throw new IndexOutOfBoundsException();
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Error: Index out of bounds.");
            } catch (Exception e){
                System.out.println("Error: Input was not an integer.");
            }
        }
    }

    public static String validString(String prompt) {
        Pattern pattern = Pattern.compile(REGEX);
        String input;
        
        while(true){
            System.out.print(prompt);
            input = scanner.nextLine();
            Matcher matcher = pattern.matcher(input);
            if(matcher.matches() && input.length() < MAX_STRING_LENGTH) {
                
                return input;
            }
            else System.out.println("Error: Invalid string.");
        }
    }

    public static String validItemName() {
        Pattern pattern = Pattern.compile(REGEX);
        String input;
        
        while(true){
            System.out.print("\nEnter the item name or type \"Cancel\" to cancel: ");
            input = scanner.nextLine();
            
            Matcher matcher = pattern.matcher(input);
            if(matcher.matches() && input.length() < MAX_STRING_LENGTH){
                for(int i = 0; i < validItemNames.length; i++){
                    if(input.toLowerCase().equals(validItemNames[i].toLowerCase())) {
                        
                        return validItemNames[i];
                    }
                }
                System.out.println("Error: Invalid item.");
            } else if (input.equals("\n"));
            else System.out.println("Error: Invalid string.");
        }
    }

    public static void promptEnter() {
        System.out.print("Press ENTER to continue.");
        scanner.nextLine();
    }


    public static String validPayPalEmail() {
        Pattern VALID_EMAIL_ADDRESS_REGEX = 
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        String email;

        while(true){
                email = scanner.nextLine();
                Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
                if(matcher.matches() && email.length() < MAX_STRING_LENGTH) {
                    
                    return email;
                }
                else System.out.println("Error: Invalid email. Please re-enter your email.");
            }
    }

    // Returns the card number as string
    public static String validCreditCardNumber() {
        long cardNumber;

        while(true){
                try {
                    cardNumber = Long.parseLong(scanner.nextLine());
    
                    if(cardNumber < MAX_LONG){
                        if ((thesize(cardNumber) >= 13 && thesize(cardNumber) <= 16) && (prefixmatch(cardNumber, 4)
                        || prefixmatch(cardNumber, 5) || prefixmatch(cardNumber, 37) || prefixmatch(cardNumber, 6))
                        && ((sumdoubleeven(cardNumber) + sumodd(cardNumber)) % 10 == 0))
                                return Long.toString(cardNumber);
                        else
                                throw new Exception();
                    } else {
                        throw new IndexOutOfBoundsException();
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Error: Invalid card number. Please re-enter.");
                } catch (Exception e){
                    System.out.println("Error: Invalid card number. Please re-enter.");
                }
            }
       }
        
       
       public static int sumdoubleeven(long cardNumber) {
	int sum = 0;
	String num = cardNumber + "";
	for (int i = thesize(cardNumber) - 2; i >= 0; i -= 2)
	   sum += getDigit(Integer.parseInt(num.charAt(i) + "") * 2);
	return sum;
       }
        
       // Return this cardNumber if it is a single digit, otherwise,
       // return the sum of the two digits
       public static int getDigit(int cardNumber) {
	if (cardNumber < 9)
	   return cardNumber;
	return cardNumber / 10 + cardNumber % 10;
       }
        
       // Return sum of odd-place digits in cardNumber
       public static int sumodd(long cardNumber) {
	int sum = 0;
	String num = cardNumber + "";
	for (int i = thesize(cardNumber) - 1; i >= 0; i -= 2)
	   sum += Integer.parseInt(num.charAt(i) + "");
	return sum;
       }
        
       // Return true if the digit d is a prefix for cardNumber
       public static boolean prefixmatch(long cardNumber, int d) {
	return getprefx(cardNumber, thesize(d)) == d;
       }
        
       // Return the number of digits in d
       public static int thesize(long d) {
	String num = d + "";
	return num.length();
       }
        
       // Return the first k number of digits from
       // number. If the number of digits in number
       // is less than k, return number.
       public static long getprefx(long cardNumber, int k) {
	if (thesize(cardNumber) > k) {
	   String num = cardNumber + "";
	   return Long.parseLong(num.substring(0, k));
	}
	return cardNumber;
       }
}
