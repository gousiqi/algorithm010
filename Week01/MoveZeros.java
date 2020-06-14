
public class MoveZeroes {

  public void moveZeroes(int[] nums) {

    //双指针，第一个指针定位在最靠前的0值；
    int j=0;
    //第二个指针用于遍历，遍历到非0值时与指针j的值交换，时间复杂度为O(n)
    for(int i=0; i<nums.length; i++){
      if(nums[i]!=0){
        if(i!=j){
          nums[j]=nums[i];
          nums[i]=0;
        }
        j++;
      }
    }
  }

}