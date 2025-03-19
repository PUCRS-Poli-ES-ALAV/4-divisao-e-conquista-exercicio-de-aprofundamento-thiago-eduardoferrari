package br.pucrs;

import java.util.Random;

public class App 
{
    private static int[] geraVetor(int nroPares, int nroImpares){
        int [] res = null;
        int contPar = 0, contImpar = 0, novoNum;
        Random rnd = new Random();

        if ((nroPares >= 0) ||
                (nroImpares >= 0) &&
                (nroPares + nroImpares > 0)) {

            res = new int[nroPares + nroImpares];

            while ((contPar < nroPares) || (contImpar < nroImpares)) {
                novoNum = rnd.nextInt(98)+1;

                if ((novoNum % 2 == 0) && (contPar < nroPares)) {
                    res[contPar+contImpar] = novoNum;
                    contPar++;
                }
                else if ((novoNum % 2 == 1) && (contImpar < nroImpares)) {
                    res[contPar+contImpar] = novoNum;
                    contImpar++;
                }
            }
        }

        return res;
    }

    private static int[] mergeSort(int[] list, int n){ 
        if(list.length == 1){
            return list;
        }
        
        int[] left = new int[list.length/2];
        int[] right = new int[list.length - left.length];

        for(int i = 0; i < left.length; i++){
            left[i] = list[i];
        }

        for(int i = left.length; i < list.length; i++){
            right[i - left.length] = list[i];
        }

        if (left.length > 1){
            left = mergeSort(left, left.length);
        }

        if (right.length > 1){
            right = mergeSort(right, right.length);
        }
        
        list = merge(left, right);

        return list;
    }

    private static int[] merge(int[] left, int[] right) {
        int n = left.length, m = right.length;
        int[] mergedArray = new int[n + m];

        int i = 0, j = 0, k = 0;

        // Junta os arrays mantendo a ordenação
        while (i < n && j < m) {
            if (left[i] <= right[j]) {
                mergedArray[k++] = left[i++];
            } else {
                mergedArray[k++] = right[j++];
            }
        }

        // Copia os elementos restantes do arr1, se houver
        while (i < n) {
            mergedArray[k++] = left[i++];
        }

        // Copia os elementos restantes do arr2, se houver
        while (j < m) {
            mergedArray[k++] = right[j++];
        }

        return mergedArray;
    }

    public static int maxValue(int[] list){
        int max = list[0];
        for(int i = 1; i < list.length; i++){
            if(list[i] > max){
                max = list[i];
            }
        }
        return max;

    }

    public static long maxValueRecursive(long[] list, int init, int end) {
        if (end - init <= 1) {
            return maxLong(list[init],list[end]);
        } else {
            int m = (init + end) / 2;
            long v1 = maxValueRecursive(list, init, m);
            long v2 = maxValueRecursive(list, m + 1, end);
            return maxLong(v1, v2);
        }
    }

    public static long maxLong(long v1, long v2) {
        return v1 > v2 ? v1 : v2;
    }

    public static long multiply(long x, long y, int n) {
        // Caso base: Se n = 1, multiplicação direta
        if (n == 1) {
            return x * y;
        }

        // Define m como a metade de n (arredondado para cima)
        int m = (int) Math.ceil(n / 2.0);

        // Divide x em a (parte mais significativa) e b (menos significativa)
        long a = x >> m; // Equivalente a x / 2^m
        long b = x & ((1L << m) - 1); // x mod 2^m

        // Divide y em c (parte mais significativa) e d (menos significativa)
        long c = y >> m; // y / 2^m
        long d = y & ((1L << m) - 1); // y mod 2^m

        // Chamadas recursivas
        long e = multiply(a, c, m);
        long f = multiply(b, d, m);
        long g = multiply(b, c, m);
        long h = multiply(a, d, m);

        // Retorna o resultado combinando as partes
        return (e << (2 * m)) + ((g + h) << m) + f;
    }


    public static void main( String[] args )
    {
        int[] list = geraVetor(5, 5);
        long[] listLong = {13, 62, 23, 94, 58, 66, 07, 38, 92, 10};
        System.out.println("Lista antes de ordenar:");
        for(int i = 0; i < list.length; i++){
            System.out.println(list[i]);
        }
        // list = mergeSort(list, list.length);
        // System.out.println("Lista depois de ordenar:");
        // for(int i = 0; i < list.length; i++){
        //     System.out.println(list[i]);
        // }
        System.out.println("Maior valor: " + maxValue(list));
        System.out.println("Maior valor recursivo: " + maxValueRecursive(listLong, 0, listLong.length - 1));
    }
}
