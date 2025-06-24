package logic;

public class CalculatorEngine {

    // true: degree, false: radian
    private boolean isDegree;

    public CalculatorEngine(boolean isDegree) {
        this.isDegree = isDegree;
    }

    public void setDegreeMode(boolean isDegree) {
        this.isDegree = isDegree;
    }

    public double calculate(String expression) {
        String[] parts = expression.split(" ");

        if (parts.length == 3) {
            double a = Double.parseDouble(parts[0]);
            String op = parts[1];
            double b = Double.parseDouble(parts[2]);

            if (op.equals("+")) return new Addition().calculate(a, b);
            else if (op.equals("-")) return new Subtraction().calculate(a, b);
            else if (op.equals("*")) return new Multiplication().calculate(a, b);
            else if (op.equals("/")) return new Division().calculate(a, b);
            else if (op.equals("%")) return new Percentage().calculate(a, b);
            else if (op.equals("^")) return new Power().calculate(a, b);
            else throw new IllegalArgumentException("Unknown operator: " + op);

        } else if (parts.length == 2) {
            String op = parts[0];
            double a = Double.parseDouble(parts[1]);

            if (op.equals("sqrt")) return new SquareRoot().calculate(a);
            else if (op.equals("log")) return new Log10().calculate(a);
            else if (op.equals("ln")) return new NaturalLog().calculate(a);
            else if (op.equals("exp")) return new Exponential().calculate(a);
            else if (op.equals("fact")) return new Factorial().calculate(a);
            else if (op.equals("sin")) return new Sine(isDegree).calculate(a);
            else if (op.equals("cos")) return new Cosine(isDegree).calculate(a);
            else if (op.equals("tan")) return new Tangent(isDegree).calculate(a);
            else if (op.equals("asin")) return new Arcsine(isDegree).calculate(a);
            else if (op.equals("acos")) return new Arccosine(isDegree).calculate(a);
            else if (op.equals("atan")) return new Arctangent(isDegree).calculate(a);
            else if (op.equals("10^x")) return new TenPowerX().calculate(a);
            else throw new IllegalArgumentException("Unknown operator: " + op);

        } else {
            throw new IllegalArgumentException("Invalid expression format.");
        }
    }
}
