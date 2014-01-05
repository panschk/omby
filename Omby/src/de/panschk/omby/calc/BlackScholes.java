package de.panschk.omby.calc;


public class BlackScholes {
    
    
    /**
     * @param r  "riskless interest rate"
     * @param underlyingPrice price of underlying
     * @param vola volatility
     * @param strike 
     * @param remainT remaining term on the option (T - t) in years
     * @return
     */
    public static double callPrice(double r, double underlyingPrice, double vola, double strike, double remainT) {
        double d1 = d1(strike, underlyingPrice, vola, r, remainT);
        double d2 = d2(d1, vola, remainT);
        System.out.println("d1 = " + d1);
        System.out.println("d2 = " + d2);
        
        double callPrice = underlyingPrice * phi(d1) - strike * Math.exp(- r*remainT) * phi(d2);
        return callPrice;
    }
    
    /**
     * @param r  "riskless interest rate"
     * @param underlyingPrice price of underlying
     * @param vola volatility
     * @param strike 
     * @param remainT remaining term on the option (T - t) in years
     * @return
     */
    public static double putPrice(double r, double underlyingPrice, double vola, double strike, double remainT) {
        double d1 = d1(strike, underlyingPrice, vola, r, remainT);
        double d2 = d2(d1, vola, remainT);
        
        double putPrice = strike * Math.exp(-r*remainT) * phi(-d2) - underlyingPrice * phi(-d1);
        return putPrice;
    }

    private static double phi(double x) {
       return StandardNormalVerteilung.normalVerteilung(x, 100);
    }

    private static double d2(double d1, double vola, double remainT) {
        return d1 - vola*Math.sqrt(remainT);
    }

    private static double d1(double strike, double underlyingPrice,
            double vola, double r, double remainT) {
        double result = (Math.log(underlyingPrice/strike) + (r + vola*vola/2)*remainT) / (vola*Math.sqrt(remainT));
        return result;
    }
    
    
    public static void main(String[] args) {
        System.out.println(callPrice(0.05, 376, 0.3, 346, 2));
        System.out.println(putPrice(0.05, 376, 0.3, 346, 2));
    }

}
