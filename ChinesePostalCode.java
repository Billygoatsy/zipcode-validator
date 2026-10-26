

public class ChinesePostalCode {

    /*=======================================================*/
    /* Chinese postal code has the form DDDDDD, where D is   */
    /* any digit from 0 to 9, for example 150001. That is,   */
    /* a Chinese postal code is made up of exactly 6 digits. */
    /* Any number of spaces is allowed in the user input     */
    /* postal code.                                          */
    /*=======================================================*/
	public boolean isValidChinesePCode(String postalCode) {

       char[] pCode = toNonBlankCharArray(postalCode);

       if (pCode.length != 6)
          return false;

       for(int i=0; i<pCode.length; i++){
          if( Character.isDigit(pCode[i]) == false)
             return false;
	   }

       return true;
   }

   /*====================================================*/
   /* Get rid of all the spaces from the user input      */
   /*====================================================*/
   public char[] toNonBlankCharArray(String postalCode){
      int m=0;

      for (int k=0;k<postalCode.length(); k++){
		 if (Character.isSpaceChar(postalCode.charAt(k)) == false ){
		    m++;
		 }
	  }

	  char[] p = new char[m];

	  int n = 0;
	  for (int k=0;k<postalCode.length(); k++){
	     if (Character.isSpaceChar(postalCode.charAt(k)) == false ){
	  	    p[n] =  postalCode.charAt(k);
	  	    n++;
	  	 }
	  }
	  return p;
   }
}// end of class
