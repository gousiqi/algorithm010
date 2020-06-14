public class PlusOne {

  public int[] plusOne(int[] digits) {

    int index = digits.length-1;
    while (true) {
      //首位溢出：需要增一位，然后跳出
      if (index==0 && digits[index]==9) {
        //直接定义一个新数组，长度加一， 首位为1即可
        int[] newDigits = new int[digits.length+1];
        newDigits[0]=1;
        digits=newDigits;
        break;
      }
      //不需要再进位,跳出
      else if (digits[index]<9) {
        digits[index]++;
        break;
      }
      //需要进位
      else if (digits[index]==9) {
        digits[index] = 0;
        index--;
      }
    }
    return digits;

  }

}