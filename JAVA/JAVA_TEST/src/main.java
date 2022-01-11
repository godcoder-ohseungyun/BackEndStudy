import java.util.Arrays;

class Solution {
    public int[] solution(int[] array, int[][] commands) {


        int[] answer = new int[commands.length];

        int i = 0;
        int fromIndex = 0;
        int toIndex = 0;
        int findIndex = 0;

        int[] blanket = {}; //배열은 객체이다. 미리 선언을 통해 저장공간을 별도로 추가 생성하지 않는다.

        for(i=0;i<commands.length;i++){
            fromIndex = commands[i][0]-1;
            toIndex = commands[i][1]; //Arrays.copyOfRange 범위 마지막 인덱스 포함 안시킴 따라서 -1 안함
            findIndex = commands[i][2]-1;

            blanket = Arrays.copyOfRange(array,fromIndex,toIndex);

            Arrays.sort(blanket); //정렬

            answer[i] = blanket[findIndex];
        }

        return answer;
    }
}