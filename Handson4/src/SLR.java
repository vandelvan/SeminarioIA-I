public class SLR {
    private double beta1; // slope
    private double beta0; // intercept
    
    public SLR(){
        beta0 = 0;
        beta1 = 0;
    }
    
    public SLR(double[][] dataSet){
        double[] betas = cramer(dataSet); 
        this.beta0 = betas[0];
        this.beta1 = betas[1];
        printEquation();
    }

    
    public static double[] cramer(double[][] dataSet){
        
        int n = dataSet.length;
        double sumX = 0;
        double sumY = 0;
        double sumXY = 0;
        double sumXX = 0;
        
        for(int i = 0; i < n; i++){
            sumX += dataSet[i][0];
            sumXX += dataSet[i][0] * dataSet[i][0];
            sumY += dataSet[i][1];
            sumXY += dataSet[i][0] * dataSet[i][1];
        }
        
        double ds = n * sumXX - sumX * sumX;
        double db1 = n * sumXY - sumX * sumY;
        double db0 = sumY * sumXX - sumXY * sumX;
        
        double b0 = db0 / ds;
        double b1 = db1 / ds;
        
        return new double[] {b0, b1};
    }
    
    public double predict(double[] factor) {
        double y =  beta0 + beta1 * factor[0];
        System.out.println("y = " + String.format("%.2f", y));
        return y;
    }

    
    public double[] getBetas() {
        return new double [] {beta0, beta1};
    }

    
    public void printEquation() {
        System.out.println("ŷ = " + String.format("%.2f", this.beta0) + " + " + String.format("%.2f", this.beta1) + "*x1");
    }
    
}