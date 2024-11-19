package verificacoes;

public class Checks {
	/* Como usar este package?
	 * 1/ Escrever :import extras.Checks; antes da respetiva classe 
	 * 2/ Chamar os métodos  pra situação que necessita ex:. Checks.SeShort(String)
	 * 3/ O método irá returnar um boolean
	 * Se lhe apetecer poderá adicionar outras classes e métodos, se encontrar
	 * algum erro neste código corrija pk tenho demasiada preguiça pra isso :]
	 */

	public static boolean SeInteger(String input) {
		// range -2,147,483,648 to 2,147,483,647
		try {
			int num = Integer.parseInt(input);
			if(num > -2147483648  && num <2147483647);
			return true;

		}catch (NumberFormatException | NullPointerException E) {
			return false;
		}
	}


	public static boolean SeShort(String input) {
		//range -32768 to 32767
		try {
			int num = Integer.parseInt(input);
			if(num > -32768 && num < 32767);
			return true;

		}catch (NumberFormatException | NullPointerException E) {
			return false;
		}
	}



	public static boolean SeChar(String input) {
		//Stores a single character/letter or ASCII values
		try {
			if(input.length()== 1);
			return true;

		}catch (NumberFormatException | NullPointerException E) {
			return false;
		}
	}

	public static boolean SeBoolean(String input) {
		// range 0 to 1
		try {
			boolean num = Boolean.parseBoolean(input);
			if(num == true || num == false);
			return true;

		}catch (NumberFormatException | NullPointerException E) {
			return false;
		}
	}


	public static boolean SeByte(String input) {
		// range -127 to 128
		try {
			int num = Integer.parseInt(input);
			if(num > -127 && num < 128);
			return true;

		}catch (NumberFormatException | NullPointerException E) {
			return false;
		}
	}


	public static boolean SeLong(String input) {
		//range -9223372036854775808 to 9223372036854775807
		try {
			long num = Long.parseLong(input);
			if(num > -9223372036854775808L && num < 9223372036854775807L);
			return true;

		}catch (NumberFormatException | NullPointerException E) {
			return false;
		}
	}


	public static boolean SeFloat(String input) {
		// range = 1.4e-45 to 3.4e+38
		try {
			float num = Float.parseFloat(input);
			if( num > Math.pow(1.4, -45)  && num < Math.pow(3.4028235, 38));
			return true;

		}catch (NumberFormatException | NullPointerException E) {
			return false;
		}
	}

	public static boolean SeDouble(String input) {
		// range = 1.7E-308 to 1.7E+308
		try {
			double num = Double.parseDouble(input);
			if( num > Math.pow(1.7, -308)  && num < Math.pow(1.7976931348623157, 308));
			return true;

		}catch (NumberFormatException | NullPointerException E) {
			return false;
		}
	}

}
