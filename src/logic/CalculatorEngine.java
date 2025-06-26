package logic;

public class CalculatorEngine {

    // Degree mode: true = degree, false = radian
    private boolean isDegree;

    public CalculatorEngine(boolean isDegree) {
        this.isDegree = isDegree;
    }

    public double calculate(String expression) {
        String[] parts = expression.trim().split(" ");

        if (parts.length == 3) {
            double a = Double.parseDouble(parts[0]);
            String op = parts[1];
            double b = Double.parseDouble(parts[2]);

            switch (op) {
                case "+": return new Addition().calculate(a, b);
                case "-": return new Subtraction().calculate(a, b);
                case "*": return new Multiplication().calculate(a, b);
                case "/": return new Division().calculate(a, b);
                case "%": return new Percentage().calculate(a, b);
                case "^": return new Power().calculate(a, b);
                default:
                    throw new IllegalArgumentException("Unknown operator: " + op);
            }

        } else if (parts.length == 2) {
            String op = parts[0];
            double a = Double.parseDouble(parts[1]);

            switch (op) {
                case "sqrt": return new SquareRoot().calculate(a);
                case "log": return new Log10().calculate(a);
                case "ln": return new NaturalLog().calculate(a);
                case "exp": return new Exponential().calculate(a);
                case "fact": return new Factorial().calculate(a);
                case "sin": return new Sine(isDegree).calculate(a);
                case "cos": return new Cosine(isDegree).calculate(a);
                case "tan": return new Tangent(isDegree).calculate(a);
                case "asin": return isDegree ? Math.toDegrees(Math.asin(a)) : Math.asin(a);
                case "acos": return isDegree ? Math.toDegrees(Math.acos(a)) : Math.acos(a);
                case "atan": return isDegree ? Math.toDegrees(Math.atan(a)) : Math.atan(a);
                case "10^": return Math.pow(10, a);
                default:
                    throw new IllegalArgumentException("Unknown operator: " + op);
            }

        } else {
            throw new IllegalArgumentException("Invalid expression format.");
        }
    }
}
