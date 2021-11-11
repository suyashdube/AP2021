import java.util.ArrayList;
import java.util.Scanner;

public class matrix {
    static ArrayList <matrix> matrices = new ArrayList<matrix>();
    private int[][] Matrix;
    private ArrayList <String> matrixTypes;
    private float[][] floatMatrix;
    int row, col;
    static int id = 0;

//    public static int createID()
//    {
//        return id++;
//    }


    public matrix(int r, int c){
        row = r;
        col = c;
        //id++;
        id = id +1;
        Matrix = new int[row][col];
        floatMatrix = new float[row][col];
        matrixTypes = new ArrayList<>();

    }


    public static void inputMatrix(matrix m){
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the elements of matrix: ");
        for(int i = 0; i < m.row; i++){
            for(int j = 0; j < m.col; j++){
                m.Matrix[i][j] = s.nextInt();
            }
        }
    }
    public static matrix transposeMatrix(matrix m){
        int transpose[][] = new int[m.col][m.row];
        for(int i = 0; i < m.col; i++){
            for(int j = 0; j < m.row; j++){
                transpose[i][j] = m.Matrix[j][i];
            }
        }
        matrix k = new matrix(m.col,m.row);
        k.Matrix = transpose;

        return k;
    }
    public static int[][] makeNegative(matrix m){
        int[][] a = new int[m.row][m.col];
        for(int i = 0; i < m.row; i++){
            for(int j = 0; j < m.col; j++){
                a[i][j] = -m.Matrix[i][j];
            }
        }
        return a;
    }
    public static boolean checkIfUpperTriangular(matrix m){
        boolean flag = true;
        if((m.row == 1 && m.col == 1) || (m.row != m.col)){
            flag = false;
        }
        else{
            for(int i = 1; i < m.row; i++){
                for(int j = 0; j < i; j++){
                    if(m.Matrix[i][j] != 0)
                        flag = false;
                    else
                        flag = true;
                }
            }

        }
        return flag;
    }
    public static boolean checkIfLowerTriangular(matrix m){
        boolean flag = true;
        if((m.row == 1 && m.col == 1) || (m.row != m.col)){
            flag = false;
        }
        else{
            for(int i = 1; i < m.row; i++){
                for(int j = i+1; j < m.row; j++){
                    if(m.Matrix[i][j] != 0)
                        flag = false;
                    else
                        flag = true;
                }
            }

        }
        return flag;
    }
    public static void transpose(int mat[][], int temp[][], int p, int q, int n){
        int i = 0, j = 0;
        for(int m = 0; m < n; m++){
            for(int k = 0; k < n; k++){
                if(m!=p && k!=q){
                   temp[i][j++] = mat[m][k];
                   if(j == n -1){
                       j = 0;
                       i++;
                   }
                }
            }
        }
    }
    public static float checkSingular(matrix m, int n){
        float determinant = 0;
        if((m.row == 1 && m.col == 1) || (m.row != m.col)){
            determinant = 1;
        }
        else {
            if (n == 1) {
                return m.Matrix[0][0];
            }
            matrix temp = new matrix(m.row, m.row);
            int s = 1;
            for (int i = 0; i < n; i++) {
                transpose(m.Matrix, temp.Matrix, 0, i, n);
                determinant += (float) s * m.Matrix[0][i] * checkSingular(temp, n - 1);
            }
        }
        return determinant;

    }
    public static float[][] adjoint(matrix m, int a[][], int b[][]){
        float [][] adjointMatrix = new float[m.row][m.col];

        if(m.row == 1 && m.col == 1){
            adjointMatrix[0][0] = 1;
            return adjointMatrix;
        }
        int s = 1;
        matrix temp = new matrix(m.row,m.col);
        //int [][] temp = new int[m.row][m.col];
        for(int i = 0; i < m.row; i++){
            for(int j = 0; j < m.col; j++){
                transpose(a,temp.Matrix, i, j, m.row);
                if((i+j)%2 == 0)
                    s = 1;
                else
                    s = -1;
                adjointMatrix[j][i] = s*checkSingular(temp,m.row-1);
            }
        }
        return adjointMatrix;

    }

   public static float[][] inverseMatrix(matrix a, float[][] inverse){
        //float [][] inverse;
        float d = checkSingular(a, a.col);
        if(d == 0){
            System.out.println("inverse doesn't exist");
            return null;
        }
        matrix adjoint = new matrix(a.row, a.col);
        addMatrices(a,adjoint);
        for(int i = 0; i < a.row; i++){
            for(int j = 0; j < a.col; j++){
                inverse[i][j] = adjoint.Matrix[i][j]/(float)d;
            }
        }
        return inverse;
   }
   //1 2
   //3 4
   public static float computeRowMean(matrix m, int row){
        int sum = 0;
        for(int j = 0; j < m.col; j++){
                sum += m.Matrix[row][j];
            }
        float mean = (float) sum/m.col;
        return mean;
   }
    public static float computeColMean(matrix m, int col){
        int sum = 0;
        for(int j = 0; j < m.row; j++){
            sum += m.Matrix[j][col];
        }
        float mean = (float) sum/m.row;
        return mean;
    }
    public static float computeMean(matrix m){
        int sum = 0;
        for(int i = 0; i < m.row; i++){
            for(int j = 0; j < m.col; j++){
                sum += m.Matrix[i][j];
            }
        }
        int n = m.row*m.col;
        float mean = (float) sum/n;
        return mean;
    }
    public static int[][] computeScalarMultiplication(matrix a, matrix b){
        int[][] answer = new int[a.row][a.col];
        int c =  b.Matrix[0][0];
        for(int i = 0; i < a.row; i++){
            for(int j = 0; j < a.col; j++){
                answer[i][j] = c*a.Matrix[i][j];
            }
        }
        return answer;
    }
    public static int[][] computeScalarDivision(matrix a, matrix b){
        int[][] answer = new int[a.row][a.col];
        int c =  b.Matrix[0][0];
        for(int i = 0; i < a.row; i++){
            for(int j = 0; j < a.col; j++){
                answer[i][j] = a.Matrix[i][j]/c;
            }
        }
        return answer;
    }
    public static int[][] computeScalarAddition(matrix a, matrix b){
        int[][] answer = new int[a.row][a.col];
        int c =  b.Matrix[0][0];
        for(int i = 0; i < a.row; i++){
            for(int j = 0; j < a.col; j++){
                answer[i][j] = a.Matrix[i][j]+c;
            }
        }
        return answer;
    }


    public static boolean checkDiagonal(matrix m){
        boolean flag = true;
        a: for(int i = 0; i < m.col; i++){
            for(int j = 0; j < m.col; j++){
                if(i == j){
                    if(m.Matrix[i][j] == 0){
                        flag = false;
                        break a;
                    }
                }
                else if(m.Matrix[i][j] != 0){
                    flag = false;
                    break a;
                }
            }
        }

        return flag;
    }
    public static boolean checkScalar(matrix m){
        boolean flag = true;
        if(m.row != m.col){
            flag = false;
        }
        else {
            int a = m.Matrix[0][0], c = 0, d = 0;
            for (int i = 0; i < m.row; i++) {
                for (int j = 0; j < m.row; j++) {
                    if (i != j && m.Matrix[i][j] != 0) {
                        c = 1;
                        break;
                    }

                    if (i == j && (m.Matrix[i][j] == 0 || m.Matrix[i][j] != a)) {
                        d = 1;
                        break;
                    }
                }
            }
            if(c == 0 && d == 0)
                flag = true;
            else
                flag = false;
        }


        return flag;
    }
    public static boolean checkIdentityMatrix(matrix m){
        boolean flag = true;
        if(m.row != m.col)
            flag = false;
        else {
            for (int i = 0; i < m.row; i++) {
                for (int j = 0; j < m.col; j++) {
                    if (i == j && m.Matrix[i][j] != 1) {
                        flag = false;
                        break;
                    }
                    if (i != j && m.Matrix[i][j] != 0) {
                        flag = false;
                        break;
                    }
                }
            }
        }

        return flag;
    }
    public static boolean checkOnesMatrix(matrix m){
        boolean flag = true;
        for(int i = 0; i < m.row; i++){
            for(int j = 0; j < m.col; j++){
                if(m.Matrix[i][j] != 1)
                    flag = false;
            }

        }
        return flag;
    }
    public static boolean checkNullMatrix(matrix m){
        boolean flag = true;
        for(int i = 0; i < m.row; i++){
            for(int j = 0; j < m.col; j++){
                if(m.Matrix[i][j] != 0)
                    flag = false;
            }

        }
        return flag;
    }
    public static int[][] addMatrices(matrix a, matrix b){
        int sum[][] = new int[a.row][a.col];
        if((a.col != b.col)||(a.row != b.row)){
            System.out.println("Dimensions of the matrices must be same");
            return null;
        }
        else{

            for(int i = 0; i < a.row; i++){
                for(int j = 0; j < a.col; j++){
                   sum[i][j] = a.Matrix[i][j] + b.Matrix[i][j];
                }
            }

        }
        return sum;
    }
    public static int[][] subtractMatrices(matrix a, matrix b){
        int difference[][] = new int[a.row][a.col];
        if((a.col != b.col)||(a.row != b.row)){
            System.out.println("Dimensions of the matrices must be same");
            return null;
        }
        else{

            for(int i = 0; i < a.row; i++){
                for(int j = 0; j < a.col; j++){
                    difference[i][j] = a.Matrix[i][j] - b.Matrix[i][j];
                }
            }

        }
        return difference;
    }
    public static int[][] multiplyMatrices(matrix a, matrix b){
        int mul[][] = new int[a.row][b.col];
        if((b.row != a.col)||(checkNullMatrix(a)||checkNullMatrix(b))){
            System.out.println("multiplication isn't possible");
            int[][] f = new int[a.row][b.col];
            return f;
        }
        else{

            for(int i = 0; i < a.row; i++){
                for(int j = 0; j < b.col; j++){
                    for(int k = 0; k < b.row; k++)
                        mul[i][j] += a.Matrix[i][k]*b.Matrix[k][j];
                }
            }

        }
        return mul;
    }
    public static int[][] divideMatrices(matrix a, matrix b){
        int div[][] = new int[a.row][b.col];
        if((b.row != a.col)||(checkNullMatrix(b))){
            System.out.println("division isn't possible");
            int[][] f = new int[a.row][b.col];
            return f;
        }
        else{

            for(int i = 0; i < a.row; i++){
                for(int j = 0; j < b.col; j++){
                    div[i][j] += a.Matrix[i][j]/b.Matrix[i][j];
                }
            }

        }
        return div;
    }
    public static int[][] multiplyElementwiseMatrices(matrix a, matrix b){
        int mul[][] = new int[a.row][b.col];
        if((a.row != b.row)||(a.col != b.col)||(checkNullMatrix(a)||checkNullMatrix(b))){
            System.out.println("multiplication isn't possible");
            int[][] f = new int[a.row][b.col];
            return f;
        }
        else{

            for(int i = 0; i < a.row; i++){
                for(int j = 0; j < b.col; j++){
                    mul[i][j] += a.Matrix[i][j]*b.Matrix[i][j];
                }
            }

        }
        return mul;
    }

    public static int[][] computeTask12(matrix a){
        int[][] answer = new int[a.row][a.col];
        answer = addMatrices(a,transposeMatrix(a));
        return answer;
    }
    public static int[][] equationSolver(matrix m, matrix c){
        int[][] answer = new int[m.row][m.col];
       float[][] b = new float[m.row][m.col];
        matrix a = new matrix(m.row, m.col);
        inverseMatrix(m,b);
        a.floatMatrix = b;
        answer = multiplyMatrices(a,c);
        return answer;
    }
    public static matrix displayMatrix(matrix m){
        matrix answer = new matrix(m.row, m.col);
        for(int i = 0; i < m.row; i++){
            for(int j = 0; j <m.col; j++){
                answer.Matrix[i][j] = m.Matrix[i][j];
            }
        }
        return answer;
    }

    public static void retriveMatrix(int id){
         for(int i =0; i < matrices.size(); i++){
             if(matrices.get(i).id == id)
                 displayMatrix(matrices.get(i));
         }
    }

    public static void classifyMatrix(matrix m){
       // matrix b = transposeMatrix(m);
        if(m.row == m.col){
            //square matrix
            System.out.println("square matrix");
            SquareMatrix s = new SquareMatrix(m);
            m.matrixTypes.add("square matrix");

        }
        if(m.row != m.col){
            //rectangle matrix
            System.out.println("rectangle matrix");
            RectangularMatrix r = new RectangularMatrix(m);
            m.matrixTypes.add("rectangle matrix");


        }
        if(m.row == 1){
            //row matrix
            System.out.println("row matrix");
            m.matrixTypes.add("row matrix");

        }
        if(m.col == 1){
            //col matrix
            System.out.println("column matrix");
            m.matrixTypes.add("column matrix");

        }
        if(m.Matrix == transposeMatrix(m).Matrix){
            //symmetric
            System.out.println("symmetric matrix");
            m.matrixTypes.add("symmetric matrix");

        }
        if(m.Matrix == makeNegative(transposeMatrix(m))){
            //skew symmetric matrix
            System.out.println("skew symmetric matrix");
            m.matrixTypes.add("skew symmetric matrix");

        }
        if(checkIfUpperTriangular(m)){
            //upper triangle matrix
            System.out.println("upper triangular matrix");
            m.matrixTypes.add("upper triangular matrix");

        }
        if(checkIfLowerTriangular(m)){
            //lower triangular
            System.out.println("lower triangular matrix");
            m.matrixTypes.add("lower triangular matrix");

        }
        if(checkSingular(m,m.row) == 0){
            //singular matrix
            System.out.println("singular matrix");
            m.matrixTypes.add("singular matrix");

        }
        if(checkDiagonal(m)){
           //diagonal matrix
            System.out.println("diagonal matrix");
            m.matrixTypes.add("diagonal matrix");

        }
        if(checkScalar(m)){
          //scalar matrix
            System.out.println("scalar matrix");
            m.matrixTypes.add("scalar matrix");

        }
        if(checkIdentityMatrix(m)){
            //identity matrix
            System.out.println("identity matrix");
            m.matrixTypes.add("identity matrix");

        }
        if(m.row == 1 && m.col == 1){
            //singleton matrix
            System.out.println("singleton matrix");
            m.matrixTypes.add("singleton matrix");

        }
        if(checkOnesMatrix(m)){
            //ones matrix
            System.out.println("ones matrix");
            m.matrixTypes.add("ones matrix");

        }
        if(checkNullMatrix(m)){
            //null matrix
            System.out.println("null matrix");
            m.matrixTypes.add("null matrix");

        }


    }
 public static void listIDs(){
        for(int i =0; i < matrices.size(); i++){
            System.out.println(matrices.get(i).id);
        }
 }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
       // ArrayList <matrix> matrices = new ArrayList<matrix>();



        while(true){
            System.out.println("enter no of rows of matrix: ");
            int row = sc.nextInt();
            System.out.println("enter no of columns of matrix: ");
            int col = sc.nextInt();
//            matrix mx = new matrix(row, col);
//            mx.id = createID();
           // System.out.println("the id of matrix added: " + mx.createID());
            //inputMatrix(mx);
            matrices.add(new matrix(row,col));
            inputMatrix(matrices.get(matrices.size()-1));
            System.out.println("ID:" + matrices.get(matrices.size()-1).id);
            System.out.println("press 1 to exit. press any other key to keep adding more matrices");
            int choice = sc.nextInt();
            if(choice == 1){
                break;
            }
            //count++;
        }

        while(true){
            System.out.println("Choose one of the operations to be performed: ");
            System.out.println("1. change the elements of the matrix");
            System.out.println("2. Display labels of matrix");
            System.out.println("3. perform addition of 2 matrices");
            System.out.println("4. perform subtraction of 2 matrices");
            System.out.println("5. perform multiplication of 2 matrices");
            System.out.println("6. perform division of 2 matrices");
            System.out.println("7. perform element wise multiplication");
            System.out.println("8. perform element wise division");
            System.out.println("9. transpose matrix");
            System.out.println("10. inverse matrix");
            System.out.println("11. compute row mean of a matrix");
            System.out.println("12. compute column mean of a matrix");
            System.out.println("13. compute mean of all elements");
            System.out.println("14. compute determinants");
            System.out.println("15. use singleton matrices as scalars");
            System.out.println("16. compute A+At for a matrix A");
            System.out.println("17. solve sets of linear equations");
            System.out.println("18. retrieve a matrix using id");
            System.out.println("PRESS 0 to exit");
            int choice = sc.nextInt();
            if(choice == 0)
                break;
            else{
                switch (choice){
                    case 1:
                        System.out.println("enter the id of matrix whose element you wanna change:");
                        int id = sc.nextInt();
                        System.out.println("Enter the row and col position of matrix that you wanna change:");
                        int row = sc.nextInt();
                        int col = sc.nextInt();
                        System.out.println("Enter the value you wanna change it with: ");
                        int val = sc.nextInt();
                        for(int i =0; i < matrices.size(); i++){
                            if(matrices.get(i).id == id) {
                                matrices.get(i).Matrix[row][col] = val;
                            }
                        }
                        System.out.println("The final matrix after changing is: ");
                        for(int i =0; i < matrices.size(); i++){
                            if(matrices.get(i).id == id){
                                displayMatrix(matrices.get(i));
                            }
                        }
                        break;

                    case 2:
                        //System.out.println("Enter the id of matrix whose labels you want to get: ");
                        //classifyMatrix();
                        for(int i =0; i < matrices.size(); i++){

                                    classifyMatrix(matrices.get(i));

                        }
                        break;

                    case 3:
                        listIDs();
                        System.out.println("Enter the ids of matrices whose sum you want to get: ");
                        int b = sc.nextInt(); int z = sc.nextInt();
                        //int[][] sum = new int[matrices.get(b).row][matrices.get(b).col];
                        ArrayList<matrix> s = new ArrayList<>();

                        matrix m1, m2;
                        matrix sum = new matrix(1000,1000);
                        System.out.println(matrices);



                        for(int i =0; i < matrices.size(); i++){
                            if(matrices.get(i).id == b) {
                                System.out.println("hi");
                                m1 = matrices.get(i);
                                s.add(m1);

                            }
                            if(matrices.get(i).id == z){
                                System.out.println("bye");
                                m2 = matrices.get(i);
                                s.add(m2);
                            }


                            sum.Matrix = addMatrices(s.get(0), s.get(1));
                            displayMatrix(sum);
                        }
                        System.out.println(s.size());

                        break;
                    case 10:
                        System.out.println("enter rows and col of the matrix:");
                        int rowM = sc.nextInt();
                        int colM = sc.nextInt();
                        matrix in = new matrix(rowM,colM);
                        for(int l = 0; l < rowM; l++){
                            for(int j = 0; j < colM; j++){
                                in.Matrix[l][j] = sc.nextInt();
                            }
                        }
                        matrix i = new matrix(rowM,colM);
                        inverseMatrix(in,i.floatMatrix);
                        for(int u = 0; u < rowM; u++){
                            for(int j = 0; j < colM; j++){
                                System.out.println(i.floatMatrix[u][j]);
                            }
                        }


                }

            }


        }

        //classifyMatrix(mx);

    }
}
class RectangularMatrix extends matrix{
    int row, col, id;
    public RectangularMatrix(matrix m){
        super(m.row,m.col);
        this.row = m.row;
        this.col = m.col;
        this.id = m.id;
    }
}
class SquareMatrix extends matrix{
    int row, col, id;
    public SquareMatrix(matrix m){
        super(m.row,m.col);
        this.row = m.row;
        this.col = m.col;
        this.id = m.id;
    }
}

