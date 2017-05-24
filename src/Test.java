import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by mek on 17.05.2017.
 */
public class Test {
    public static void main(String[] args) {
        ArrayList<Integer> list1 = new ArrayList<Integer>();
        list1.add(new Integer(5));
        ArrayList<Integer> list2 = new ArrayList<Integer>(list1);
        list2.add(new Integer(10));

        System.out.println(list1.toString());
        System.out.println(list2.toString());
    }
}
