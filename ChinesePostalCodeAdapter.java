

public class ChinesePostalCodeAdapter extends ChinesePostalCode implements ZipCodeValidator  {

  /* The object adapter holds a reference to the adaptee object */
  private ChinesePostalCode objChPostCode;

  public ChinesePostalCodeAdapter(ChinesePostalCode pCode) {
     objChPostCode = pCode;
  }

  /* Translate the request isValidZipCode(zip, state) of the  */
  /* ZipCodeValidator interface into the call of the adaptee  */
  /* method isValidChinesePCode(pcode). A Chinese postal code  */
  /* does not depend on the province, so state is not used.    */
  public boolean isValidZipCode(String zip, String state) {
      return objChPostCode.isValidChinesePCode(zip);
  }
}// end of class
