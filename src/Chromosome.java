public class Chromosome {
    int[][] byteValue;
    double x1;
    double x2;

    public Chromosome(int mpp, int maxV, int minV){
        byteValue = chromosomes(mpp, maxV, minV);
        x1 = decode(byteValue[0], mpp);
        x2 = decode(byteValue[1], mpp);
    }
    public Chromosome(int mpp, int[][] otherChromValue){
        byteValue = otherChromValue;
        x1 = decode(byteValue[0], mpp);
        x2 = decode(byteValue[1], mpp);
    }
    public static double decode(int[] tempTab, int mpp) {
        String napis = "";
        for (int t = 0; t < tempTab.length; t++) {
            napis = String.join("", napis, String.valueOf(tempTab[t]));
        }
        int znak = 1;
        if (napis.startsWith("0")) {
            znak = -1;
        }
        int decimal = Integer.parseInt(napis, 2);
        decimal *= znak;
        return (decimal * Math.pow(10, -mpp));
    }

    public  double function() {
        double wynik = -Math.pow(x1, 2) - Math.pow(x2, 2) + 2;
        return wynik;
    }

    public static int[][] chromosomes(int mpp, int maxV, int minV) {
        double result1 = maxV + 1;
        double result2 = maxV + 1;

        int bytesNumber = (int) Math.ceil(Math.log(Math.pow(10, mpp) * (maxV - minV)) / Math.log(2));
        int[][] result = new int[2][bytesNumber];
        int[] tablica1 = new int[bytesNumber];
        int[] tablica2 = new int[bytesNumber];
        while (result1 > maxV || result1 < minV || result2 > maxV || result2 < minV) {
            for (int i = 0; i < bytesNumber; i++) {
                int temp = (int) Math.floor(Math.random() * 2);
                tablica1[i] = temp;
            }
            for (int i = 0; i < bytesNumber; i++) {
                int temp = (int) Math.floor(Math.random() * 2);
                tablica2[i] = temp;
            }
            result1 = decode(tablica1, mpp);
            result2 = decode(tablica2, mpp);
            for (int i = 0; i < bytesNumber; i++) {
                result[0][i] = tablica1[i];
            }
            for (int i = 0; i < bytesNumber; i++) {
                result[1][i] = tablica2[i];
            }
        }

        return result;
    }

    public int[][] hybridization(int[][] tempchromosomes2) {
        int[][] tempchromosomes1 = this.byteValue;
        int bytesnumber = tempchromosomes1[0].length;
        int point = (int) (Math.random() * (2 * bytesnumber - 1) + 1);
        int[] firstCode = new int[2 * bytesnumber];
        int[] secondCode = new int[2 * bytesnumber];
        int[] firstCodeChanged = new int[2 * bytesnumber];
        int[] secondCodeChanged = new int[2 * bytesnumber];
        int[][] hybridizedTable = new int[tempchromosomes1.length][bytesnumber];
        if (point >= 2 * bytesnumber || point <= 0) {
            System.out.println("U can't get hybridisation with that point of hybridisation");
        } else {

            for (int i = 0; i < 2 * bytesnumber; i++) {
                if (i >= bytesnumber) firstCode[i] = tempchromosomes1[1][i - bytesnumber];
                else firstCode[i] = tempchromosomes1[0][i];
            }
            for (int i = 0; i < 2 * bytesnumber; i++) {
                if (i >= bytesnumber) secondCode[i] = tempchromosomes2[1][i - bytesnumber];
                else secondCode[i] = tempchromosomes2[0][i];
            }
            for (int i = 0; i < 2 * bytesnumber; i++) {
                if (i < point) firstCodeChanged[i] = firstCode[i];
                else firstCodeChanged[i] = secondCode[i];
            }
            for (int i = 0; i < 2 * bytesnumber; i++) {
                if (i < point) secondCodeChanged[i] = secondCode[i];
                else secondCodeChanged[i] = firstCode[i];
            }
        }
        hybridizedTable[0] = firstCodeChanged;
        hybridizedTable[1] = secondCodeChanged;
        System.out.println("Point of hybrydisation: " + String.valueOf(point));
        return hybridizedTable;
    }

    public String chromToString() {
        String tempText = "";
        for (int i = 0; i < this.byteValue[0].length; i++) {
            tempText = tempText + String.valueOf(this.byteValue[0][i]);
        }
        tempText = tempText + " , ";
        for (int i = 0; i < this.byteValue[1].length; i++) {
            tempText = tempText + String.valueOf(this.byteValue[1][i]);
        }
        return tempText;
    }

    public int[][] mutation(double pm) {
        int[][] chromosome = this.byteValue;
        int[][] result = new int[chromosome.length][chromosome[0].length];
        int bytesnumber = chromosome[0].length;
        for (int i = 0; i < 2 * bytesnumber; i++) {
            double r = Math.random();
            if (i < bytesnumber) {
                if (r <= pm) result[0][i] = changeByte(chromosome[0][i]);
                else result[0][i] = chromosome[0][i];
            }
            else {
                if(r<=pm)result[1][i - bytesnumber] = changeByte(chromosome[1][i - bytesnumber]);
                else result[1][i - bytesnumber] = chromosome[1][i - bytesnumber];
            }
        }
        return result;
    }
    public int[][] dualhybridization(int[][] tempchromosomes2){
        int[][] tempchromosomes1 = this.byteValue;
        int bytesnumber = tempchromosomes1[0].length;
        int point1 = (int) (Math.random() * (2 * bytesnumber - 1) + 1);
        int point2 = (int) (Math.random() * (2 * bytesnumber - 1) + 1);
        int[] firstCode = new int[2 * bytesnumber];
        int[] secondCode = new int[2 * bytesnumber];
        int[][] hybridizedTable = new int[tempchromosomes1.length][bytesnumber];
            for (int i = 0; i < 2 * bytesnumber; i++) {
                if (i >= bytesnumber) firstCode[i] = tempchromosomes1[1][i - bytesnumber];
                else firstCode[i] = tempchromosomes1[0][i];
            }
            for (int i = 0; i < 2 * bytesnumber; i++) {
                if (i >= bytesnumber) secondCode[i] = tempchromosomes2[1][i - bytesnumber];
                else secondCode[i] = tempchromosomes2[0][i];
            }
            for (int i = 0; i< 2*bytesnumber; i++){
                if(point1 >= point2){
                    if(i >= point2 && i < point1){
                        int temp = secondCode[i];
                        secondCode[i] = firstCode[i];
                        firstCode[i] = temp;
                    }
                }
                else if(i < point2 && i >= point1){
                    int temp = secondCode[i];
                    secondCode[i] = firstCode[i];
                    firstCode[i] = temp;
                }
        }
        hybridizedTable[0] = firstCode;
        hybridizedTable[1] = secondCode;
        System.out.println("Point 1 of hybrydisation: " + String.valueOf(point1));
        System.out.println("Point 2 of hybrydisation: " + String.valueOf(point2));
        return hybridizedTable;
    }
    public int changeByte(int tempbyte){
        int result;
        if(tempbyte == 0)  result = 1;
        else result = 0;
        return result;
    }
    public static int[][] divide(int[][] chromosomeList, int index){
        int bytesnumber = chromosomeList[1].length/2;
        int[] temp = chromosomeList[index];
        int length = temp.length;
        int[] result1 = new int[bytesnumber];
        int[] result2 = new int[bytesnumber];
        for(int i = 0; i<length-1; i++){
            if(i<bytesnumber){
                result1[i] = temp[i];
            }
            else result2[i-bytesnumber] = temp[i];
        }
        int[][] result = {result1, result2};
        return result;
    }
}
