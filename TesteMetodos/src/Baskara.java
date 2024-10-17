public class Baskara {
    private double a,b,c;
    
    public void setCoef(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    public double delta() {
        return Math.pow(b,2) - 4*a*c;
    }
    public double baskaraX1() {
            return (-b + Math.sqrt(delta()))/2*a;
    }
    public double baskaraX2() {
        return (-b - Math.sqrt(delta()))/2*a;
    }
}
