public class test {
    
    public static void main(String[] args){

        int sum = 0;
        int N = 4;
        int count = 0;

        for(int i = N; i > 0; i = i/2){
            for(int j = 1; j <= i; j++){
                //System.out.println("Before: "+ sum);
                sum = sum + j;
                //System.out.println("After: "+ sum);
                System.out.println("i = " + i);
                System.out.println("j = " + j);
                System.out.println("Sum: "+sum);
                count++;
            }
            System.out.println("count: " + count);
        }
    }










    public int[] bfCountHelper(AVL_Node cur, int[] bfArr){
		int bf = 0;
		if(cur.getLeftChild() == null && cur.getRightChild() == null){
			System.out.println("1111111111111111111111111111");
			bf = cur.getBalanceFactor();
			if (bf == -1)
				bfArr[0]++;
			else if (bf == 0)
				bfArr[1]++;
			else
				bfArr[2]++;
			return bfArr;
		}

		else if (cur.getLeftChild() != null && cur.getRightChild() == null){
			System.out.println("22222222222222222222222222");
			bf = cur.getLeftChild().getBalanceFactor();
			if(bf == -1){
				bfArr[0]++;
				bfCountHelper(cur.getLeftChild(), bfArr);
			}
			else if (bf == 0){
				bfArr[1]++;
				bfCountHelper(cur.getLeftChild(), bfArr); 
			}
			else{
				bfArr[2]++;
				bfCountHelper(cur.getLeftChild(), bfArr);
			}
		}
		else if (cur.getLeftChild() == null && cur.getRightChild() != null){
			System.out.println("3333333333333333333333333");
			bf = cur.getLeftChild().getBalanceFactor();
			if(bf == -1){
				bfArr[0]++;
				bfCountHelper(cur.getRightChild(), bfArr);
			}
			else if (bf == 0){
				bfArr[0]++;
				bfCountHelper(cur.getRightChild(), bfArr);
			}
			else{
				bfArr[2]++;
				bfCountHelper(cur.getRightChild(), bfArr);
			}
		}
		else{
			System.out.println("4444444444444444444444444444");
			int bfL = cur.getLeftChild().getBalanceFactor();
			int bfR = cur.getRightChild().getBalanceFactor();
			if(bfL == -1)
				bfArr[0]++;
			else if(bfL == 0)
				bfArr[1]++;
			else
				bfArr[2]++;

			if(bfR == -1)
				bfArr[0]++;
			else if(bfR == 0)
				bfArr[1]++;
			else
				bfArr[2]++;
			
			bfCountHelper(cur.getLeftChild(), bfArr);
			bfCountHelper(cur.getRightChild(),bfArr);
		}
		return bfArr;
		
	}
    public int[] bfCount(AVL_Node cur){
		int[] bfArr = {0,0,0};
		if(cur == null)
			return null;
		else
			return bfCountHelper(cur,bfArr);
	}
}
