
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class CodiceFiscale {

	public static void main(String[] args) {

		System.out.println("CODICE FISCALE GENERATOR");
		Scanner scanner = new Scanner(System.in);

		// Get Name from User
		System.out.println("What's your name?");
		String name = scanner.nextLine();
		while (name.trim().isEmpty() || !lettersOnly(name)) {
			System.out.println("Please, what's is our name?");
			name = scanner.nextLine();
		}

		// Get Last Name from User
		System.out.println("What's your last name?");
		String lastName = scanner.nextLine();
		while (lastName.trim().isEmpty() || !lettersOnly(lastName)) {
			System.out.println("Please, what's your last name?");
			lastName = scanner.nextLine();
		}

		// Get Gender from User
		
		  System.out.println("What is your gender? (M or F)"); 
		  String gender = scanner.nextLine(); 
		  
		  while (!gender.equalsIgnoreCase("M") && !gender.equalsIgnoreCase("F")){
		  System.out.println("Please, Male (M) ou Female (F)?"); 
		  gender = scanner.nextLine(); 
		  }
		 

		// Get a birthday from User
		
		  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		  LocalDate dateOfBirth = null; boolean dataValida = false;
		  
		  while (!dataValida) {
			  System.out.print("Enter your date of birth in the format dd/MM/yyyy: ");
			  String dateOfBirthStr = scanner.nextLine();
		  
			  try { dateOfBirth = LocalDate.parse(dateOfBirthStr, formatter); 
			  dataValida = true; 
			  	} 
			  catch (DateTimeParseException e) {
			  System.out.println("Invalid date! Please try again."); 
			  	}
			}
		  
		  int birthDay = 0;
		
		  if (gender.equalsIgnoreCase("M")) { 
			  birthDay = dateOfBirth.getDayOfMonth();
			  } 
		  else if(gender.equalsIgnoreCase("F")){ 
			  birthDay = dateOfBirth.getDayOfMonth() + 40;
		  }
		  
		// Get a placeBorn from User
		
		  System.out.println("Are You Italian? Y / N ");
		  String italian = scanner.nextLine(); 
		  while (!italian.equalsIgnoreCase("Y") && !italian.equalsIgnoreCase("N")){
			  System.out.println("Please,(Y) for YES or (N) for NO?"); 
			  italian = scanner.nextLine(); 
			  }
		  
		  String pathCommuniFile="";
		  String parthCountryFile="";
		  String comuneCode="";
		  String comuneName="";		  
		  String countryCode="";
		  String countryName="";
		  
		  pathCommuniFile = "C:\\Users\\utente\\eclipse-workspace\\CodiceFiscale\\TabellaDeiCodiciDeiComuni.csv";
		  parthCountryFile = "C:\\Users\\utente\\eclipse-workspace\\CodiceFiscale\\tabellaStatiEsteri.csv";
		  if (italian.equalsIgnoreCase("Y")) {
			  System.out.println("Which COMUNE are you born in? (Italian language)");
			  String birthComune = scanner.nextLine();
			  while (birthComune.isEmpty() || !lettersOnly(birthComune)) {
				  System.out.println("COMUNE Invalid, try again."); 
				  italian = scanner.nextLine();
			  }
			  
			  comuneName = birthComune;
			  comuneCode = ProvinciaComune.getComuneCode(pathCommuniFile, comuneName);
		      
		  }
		      else if (italian.equalsIgnoreCase("N")){
		    	  System.out.println("Which COUNTRY are you born in? (Italian language)");
				  String countryBirth = scanner.nextLine();
				  while (countryBirth.isEmpty() || !lettersOnly(countryBirth)) {
					  System.out.println("COUNTRY Invalid, try again. (Italian language)"); 
					  italian = scanner.nextLine();		
					   }
				  countryName = countryBirth;
				  countryCode = StatiEsteri.getCountryCode(parthCountryFile, countryName);
		      }
	
		 
		// Geranator
		// NAME:
		String threeLetterName = "";
		int numLetterName = name.length();
		int numConsonants = 0;
		
		
		
		// Take first consonant
		for (int i = 0; i < numLetterName && numConsonants < 1; i++) {
		    char c = name.charAt(i);
		    if (Character.isLetter(c) && !isVowel(c)) {
		        threeLetterName += c;
		        numConsonants++;
		    }
		}
		

		// If present, take the 3 and 4 consonants
		for (int i = 2; i < numLetterName && numConsonants < 4; i++) {
		    char c = name.charAt(i);
		    if (Character.isLetter(c) && !isVowel(c)) {
		        numConsonants++;
		        if (numConsonants == 3 || numConsonants == 4) {
		            threeLetterName += c;
		        }
		    } 
		}	
		for (int i = 2; i < numLetterName && numConsonants < 4; i++) {
		    char c = name.charAt(i);
		    if (Character.isLetter(c) && !isVowel(c)) {
		        numConsonants++;
		        if (numConsonants == 2 || numConsonants == 3) {
		            threeLetterName += c;
		        }
		    }
		}	
		
		// Fill in vowels if needed
		while (threeLetterName.length() < 3) {
		    for (int i = 0; i < numLetterName && threeLetterName.length() < 3; i++) {
		        char c = name.charAt(i);
		        if (Character.isLetter(c) && isVowel(c)) {
		            threeLetterName += c;
		        }
		        
		    }
		    //Fill in "X" if needed
		    while (threeLetterName.length() < 3) {
		    	for (int i = 0; i < numLetterName && threeLetterName.length() < 3; i++) {
			    	threeLetterName += "X";	
		    	}
		    }
		}

		// Take from String, 3 letters
		if (threeLetterName.length() > 3) {
		    threeLetterName = threeLetterName.substring(0, 3);
		} 
		

		
		// LAST NAME
		String threeLetterLastName = "";
		int numLetterLastName = lastName.length();
		int numConsonantsLN = 0;
		
		// Take first consonant
			for (int i = 0; i < numLetterLastName && numConsonantsLN < 1; i++) {
			    char c = lastName.charAt(i);
			    if (Character.isLetter(c) && !isVowel(c)) {
			        threeLetterLastName += c;
			        numConsonantsLN++;
			    }
			}
				

		// If present, take the 2 and 3 consonants
			for (int i = 2; i < numLetterLastName && numConsonantsLN < 4; i++) {
			    char c = lastName.charAt(i);
			    if (Character.isLetter(c) && !isVowel(c)) {
			        numConsonantsLN++;
			        if (numConsonantsLN == 2 || numConsonantsLN == 3) {
			            threeLetterLastName += c;
			        }
			    }
			}	

				
		// Fill in vowels if needed
		while (threeLetterLastName.length() < 3) {
		    for (int i = 0; i < numLetterLastName && threeLetterLastName.length() < 3; i++) {
		        char c = lastName.charAt(i);
		        if (Character.isLetter(c) && isVowel(c)) {
		            threeLetterLastName += c;
		        }
		        
		    }
	    // Fill in "X" if needed
	    while (threeLetterLastName.length() < 3) {
	    	for (int i = 0; i < numLetterLastName && threeLetterLastName.length() < 3; i++) {
		    	threeLetterLastName += "X";	
	    			}
	    		}
		}
	

		
			int lastTwoDigitsOfYear = dateOfBirth.getYear() % 100;
			
			
			int monthDigits = dateOfBirth.getMonthValue();
			
			String monthLetters = monthDigToLett(monthDigits);
			
		
			String controlCharacterStr = (threeLetterLastName+threeLetterName+lastTwoDigitsOfYear+monthLetters+
					birthDay+comuneCode+countryCode);
		
			String oddStr = "";
			String evenStr = "";
			StringBuilder oddBuilder = new StringBuilder();
			StringBuilder evenBuilder = new StringBuilder();

			for (int i = 0; i < controlCharacterStr.length(); i++) {
			    char c = controlCharacterStr.charAt(i);
			    if (i % 2 == 0) { // If was ODD 
			        oddBuilder.append(c);
			    } else { // If was EVEN
			        evenBuilder.append(c);
			    }
			
			evenStr = evenBuilder.toString().toUpperCase();
			oddStr = oddBuilder.toString().toUpperCase();
			}
			
			double sumOdd = 0;

			for (int i = 0; i < oddStr.length(); i++) {
			    char c = oddStr.charAt(i);
			    int oddValue = oddAlphanumeric(String.valueOf(c));
			    sumOdd += oddValue;
			    // FOR TEST: System.out.println(c + " >> " + oddValue);
			}
			
			double sumEven = 0;

			for (int i = 0; i < evenStr.length(); i++) {
			    char c = evenStr.charAt(i);
			    int evenValue = evenAlphanumeric(String.valueOf(c));
			    sumEven += evenValue;
			    //FOR TEST: System.out.println(c + " >> " + evenValue);
			}
			double sumTotal = (sumEven + sumOdd)/26;
			
			/* For Test
			System.out.println("sumOdd = " + sumOdd);
			System.out.println("sumTotal = " + sumTotal);
			System.out.println(restTotal);
			*/
			
			int restTotal = (int) ((sumTotal - (int)sumTotal) * 26);
			
			
			String characterControl = restAlphanumeric(restTotal);
			
		
		//1ª Argument
		System.out.print(threeLetterLastName.toUpperCase());
		System.out.print(threeLetterName.toUpperCase());
		
		//2ª Argument
		System.out.print(lastTwoDigitsOfYear);
		
		//3ª Argument
		System.out.print(monthLetters);
		
		//4ª Argument
		System.out.print(birthDay);
		
		//5ª Argument
		System.out.print(comuneCode);
		System.out.print(countryCode);
		
		//6ª Argument
		System.out.println(characterControl);
		
		scanner.close();
	}
	
	
	
	//--------------------------------------------------------------//

	public static boolean lettersOnly(String str) {
		for (char c : str.toCharArray()) {
			if (!Character.isLetter(c) && c != ' ') {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isVowel(char c) {
	    return c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U' ||
	           c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
	}
	
	public static String monthDigToLett(int monthDigits) {
	    String letter = "";
	    switch (monthDigits) {
	        case 0:
	        	letter = "A";
	            break;
	        case 2:
	        	letter = "B";
	            break;
	        case 3:
	        	letter = "C";
	            break;
	        case 4:
	        	letter = "D";
	            break;
	        case 5:
	        	letter = "E";
	            break;
	        case 6:
	        	letter = "H";
	            break;
	        case 7:
	        	letter = "L";
	            break;
	        case 8:
	        	letter = "M";
	            break;
	        case 9:
	        	letter = "P";
	            break;
	        case 10:
	        	letter = "R";
	            break;
	        case 11:
	        	letter = "S";
	            break;
	        case 12:
	        	letter = "T";
	            break;
	        default:
	            System.out.println("Invalid month!");
	            break;
	    }
	    return letter;
	}
	
	public static int oddAlphanumeric(String oddNumbers) {
	    int oddValue = 0;
	    switch (oddNumbers) {
	        case "0":
	            oddValue = 1;
	            break;
	        case "1":
	            oddValue = 0;
	            break;
	        case "2":
	            oddValue = 5;
	            break;
	        case "3":
	            oddValue = 7;
	            break;
	        case "4":
	            oddValue = 9;
	            break;
	        case "5":
	            oddValue = 13;
	            break;
	        case "6":
	            oddValue = 15;
	            break;
	        case "7":
	            oddValue = 17;
	            break;
	        case "8":
	            oddValue = 19;
	            break;
	        case "9":
	            oddValue = 21;
	            break;
	        case "A":
	            oddValue = 1;
	            break;
	        case "B":
	            oddValue = 0;
	            break;
	        case "C":
	            oddValue = 5;
	            break;
	        case "D":
	            oddValue = 7;
	            break;
	        case "E":
	            oddValue = 9;
	            break;
	        case "F":
	            oddValue = 13;
	            break;
	        case "G":
	            oddValue = 15;
	            break;
	        case "H":
	            oddValue = 17;
	            break;
	        case "I":
	            oddValue = 19;
	            break;
	        case "J":
	            oddValue = 21;
	            break;
	        case "K":
	            oddValue = 2;
	            break;
	        case "L":
	            oddValue = 4;
	            break;
	        case "M":
	            oddValue = 18;
	            break;
	        case "N":
	            oddValue = 20;
	            break;
	        case "O":
	            oddValue = 11;
	            break;
	        case "P":
	            oddValue = 3;
	            break;
	        case "Q":
	            oddValue = 6;
	            break;
	        case "R":
	            oddValue = 8;
	            break;
	        case "S":
	            oddValue = 12;
	            break;
	        case "T":
	            oddValue = 14;
	            break;
	        case "U":
	            oddValue = 16;
	            break;
	        case "V":
	            oddValue = 10;
	            break;
	        case "W":
	            oddValue = 22;
	            break;
	        case "X":
	            oddValue = 25;
	            break;
	        case "Y":
	            oddValue = 24;
	            break;
	        case "Z":
	            oddValue = 23;
	            break;
	            
	    }
	    return oddValue;
	}
	public static int evenAlphanumeric(String evenNumbers) {
	    int evenValue = 0;
	    switch (evenNumbers) {
	        case "0":
	        	evenValue = 0;
	            break;
	        case "1":
	        	evenValue = 1;
	            break;
	        case "2":
	        	evenValue = 2;
	            break;
	        case "3":
	        	evenValue = 3;
	            break;
	        case "4":
	        	evenValue = 4;
	            break;
	        case "5":
	        	evenValue = 5;
	            break;
	        case "6":
	        	evenValue = 6;
	            break;
	        case "7":
	        	evenValue = 7;
	            break;
	        case "8":
	        	evenValue = 8;
	            break;
	        case "9":
	        	evenValue = 9;
	            break;
	        case "A":
	        	evenValue = 0;
	            break;
	        case "B":
	        	evenValue = 1;
	            break;
	        case "C":
	        	evenValue = 2;
	            break;
	        case "D":
	        	evenValue = 3;
	            break;
	        case "E":
	        	evenValue = 4;
	            break;
	        case "F":
	        	evenValue = 5;
	            break;
	        case "G":
	        	evenValue = 6;
	            break;
	        case "H":
	        	evenValue = 7;
	            break;
	        case "I":
	        	evenValue = 8;
	            break;
	        case "J":
	        	evenValue = 9;
	            break;
	        case "K":
	        	evenValue = 10;
	            break;
	        case "L":
	        	evenValue = 11;
	            break;
	        case "M":
	        	evenValue = 12;
	            break;
	        case "N":
	        	evenValue = 13;
	            break;
	        case "O":
	        	evenValue = 14;
	            break;
	        case "P":
	        	evenValue = 15;
	            break;
	        case "Q":
	        	evenValue = 16;
	            break;
	        case "R":
	        	evenValue = 17;
	            break;
	        case "S":
	        	evenValue = 18;
	            break;
	        case "T":
	        	evenValue = 19;
	            break;
	        case "U":
	        	evenValue = 20;
	            break;
	        case "V":
	        	evenValue = 21;
	            break;
	        case "W":
	        	evenValue = 22;
	            break;
	        case "X":
	        	evenValue = 23;
	            break;
	        case "Y":
	        	evenValue = 24;
	            break;
	        case "Z":
	        	evenValue = 25;
	            break;
	    }
	    return evenValue;
	}
	public static String restAlphanumeric(int restTotal) {
	    String restChar = "";
	    switch (restTotal) {
	        case 0:
	        	restChar = "A";
	            break;
	        case 1:
	        	restChar = "B";
	            break;
	        case 2:
	        	restChar = "C";
	            break;
	        case 3:
	        	restChar = "D";
	            break;
	        case 4:
	        	restChar = "E";
	            break;
	        case 5:
	        	restChar = "F";
	            break;
	        case 6:
	        	restChar = "G";
	            break;
	        case 7:
	        	restChar = "H";
	            break;
	        case 8:
	        	restChar = "I";
	            break;
	        case 9:
	        	restChar = "J";
	            break;
	        case 10:
	        	restChar = "K";
	            break;
	        case 11:
	        	restChar = "L";
	            break;
	        case 12:
	        	restChar = "M";
	            break;
	        case 13:
	        	restChar = "N";
	            break;
	        case 14:
	        	restChar = "O";
	            break;
	        case 15:
	        	restChar = "P";
	            break;
	        case 16:
	        	restChar = "Q";
	            break;
	        case 17:
	        	restChar = "R";
	            break;
	        case 18:
	        	restChar = "S";
	            break;
	        case 19:
	        	restChar = "T";
	            break;
	        case 20:
	        	restChar = "U";
	            break;
	        case 21:
	        	restChar = "V";
	            break;
	        case 22:
	        	restChar = "W";
	            break;
	        case 23:
	        	restChar = "X";
	            break;
	        case 24:
	        	restChar = "Y";
	            break;
	        case 25:
	        	restChar = "Z";
	            break;
	            
	            
	    }
	    return restChar;
	}

}
