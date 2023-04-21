import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // liczby do zakodowania  200000 + 200000 + 1 = 400001
        // 19 bitów
        Chromosome chromosomes1 = new Chromosome(5, 2, -2);
        Chromosome chromosomes2 = new Chromosome(5, 2, -2);
        System.out.println(chromosomes1.chromToString());
        System.out.println(chromosomes2.chromToString());
        int[][] temp = chromosomes1.dualhybridization(chromosomes2.byteValue);
        Chromosome chromosomehyb1 = new Chromosome(5, Chromosome.divide(temp, 0));
        Chromosome chromosomehyb2 = new Chromosome(5, Chromosome.divide(temp, 1));
        System.out.println(chromosomehyb1.chromToString());
        System.out.println(chromosomehyb2.chromToString());

    }
}
