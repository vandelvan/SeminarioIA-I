public class MLR{
    
    private double [] betas;
    
    public MLR(){
        betas = new double[] {0,0};
    }
    
    public MLR(double[][] dataSet){
        this.betas = cramer3x3(dataSet);
        printEquation();
    }
public static double[] cramer3x3(double[][] dataSet){
        
        int n = dataSet.length;
        double sumX1 = 0;
        double sumX2 = 0;
        double sumX1X2 = 0;
        double sumX1X1 = 0;
        double sumX2X2 = 0;
        double sumY = 0;
        double sumYX1 = 0;
        double sumYX2 = 0;
        
        final int x1 = 0;
        final int x2 = 1;
        final int y = 2;
        for(int i = 0; i < dataSet.length; i++)
        {
            sumX1 += dataSet[i][x1];
            sumX2 += dataSet[i][x2];
            sumX1X2 += dataSet[i][x1] * dataSet[i][x2];
            sumX1X1 += dataSet[i][x1] * dataSet[i][x1];
            sumX2X2 += dataSet[i][x2] * dataSet[i][x2];
            sumY += dataSet[i][y];
            sumYX1 += dataSet[i][y] * dataSet[i][x1];
            sumYX2 += dataSet[i][y] * dataSet[i][x2];
        }
        
        double ds = n*sumX1X1*sumX2X2 - n*sumX1X2*sumX1X2 - sumX1*sumX1*sumX2X2 + 2*sumX1*sumX2*sumX1X2 - sumX1X1*sumX2*sumX2;
        double db0 = sumY*sumX1X1*sumX2X2 - sumY*sumX1X2*sumX1X2 - sumX1*sumYX1*sumX2X2 + sumX1*sumX1X2*sumYX2 + sumX2*sumYX1*sumX1X2 - sumX2*sumX1X1*sumYX2;
        double db1 = n*sumYX1*sumX2X2 - n*sumX1X2*sumYX2 - sumY*sumX1*sumX2X2 + sumY*sumX1X2*sumX2 + sumX2*sumX1*sumYX2 - sumX2*sumYX1*sumX2;
        double db2 = n*sumX1X1*sumYX2 - n*sumYX1*sumX1X2 - sumX1*sumX1*sumYX2 + sumX1*sumYX1*sumX2 + sumY*sumX1*sumX1X2 - sumY*sumX1X1*sumX2;
        
        
        double b0 = db0/ds;
        double b1 = db1/ds;
        double b2 = db2/ds;
        return new double[] {b0, b1, b2};
    }
    
    public double predictFor(double[] factors) {
        double y = betas[0];
        
        for(int i = 0; i < 2; i++){
            y += betas[i+1] * factors[i];
        }
        System.out.println("y = " + String.format("%.2f", y));
        return y;
    }

    
    public double[] getBetas() {
        return this.betas;
    }

    public void printEquation() {
        System.out.println(String.format("%.2f", this.betas[0]) + " + " + String.format("%.2f", this.betas[1]) + "*Factor1 + " + String.format("%.2f", this.betas[2]) + "*x2");
    }

}
