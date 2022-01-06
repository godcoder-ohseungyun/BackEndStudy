import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class main {

    public static void main(String[] args) {

        List list = new ArrayList();
        list.iterator();




        int[] arr = {1,5,7,6};
        int[][] arr2 = {{1,2},{3,4}};
        Arrays.toString(arr); //배열의 요소를 문자열로 반환:배열의 타입에 따라 다 구현되어있다.  [1,5,7,6]
        Arrays.deepToString(arr2); //다차원 배열은 deepToString으로 해야한다. [[1, 2], [3, 4]]

        Arrays.equals(arr,arr); //얕은 비교: 원시타입 1차원 배열 비교에 사용
        Arrays.deepEquals(arr2,arr2); //깊은 비교: 객체(String..) 혹은 다차원 배열 비교


        Integer[] a = {1,2,3};
        List list2  = Arrays.asList(a);
        Iterator it = list2.iterator();

        while (it.hasNext()){
            System.out.println(it.next());
        }



    }
}
