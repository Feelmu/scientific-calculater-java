package logic;

// Abstract base class for all math operations
public abstract class Operation {
    public abstract double calculate(double... operands);
}

// Addition
class Addition extends Operation {
    public double calculate(double... operands) {
        return operands[0] + operands[1];
    }
}

// Subtraction
class Subtraction extends Operation {
    public double calculate(double... operands) {
        return operands[0] - operands[1];
    }
}

// Multiplication
class Multiplication extends Operation {
    public double calculate(double... operands) {
        return operands[0] * operands[1];
    }
}

// Division
class Division extends Operation {
    public double calculate(double... operands) {
        if (operands[1] == 0) {
            throw new ArithmeticException("Cannot divide by zero.");
        }
        return operands[0] / operands[1];
    }
}

// Power (a ^ b)
class Power extends Operation {
    public double calculate(double... operands) {
        return Math.pow(operands[0], operands[1]);
    }
}

// Percentage (a % b)
class Percentage extends Operation {
    public double calculate(double... operands) {
        return operands[0] % operands[1];
    }
}

// Square root (√a)
class SquareRoot extends Operation {
    public double calculate(double... operands) {
        return Math.sqrt(operands[0]);
    }
}

// Sine function
class Sine extends Operation {
    private boolean isDegree;

    public Sine(boolean isDegree) {
        this.isDegree = isDegree;
    }

    public double calculate(double... operands) {
        double angle = operands[0];
        if (isDegree) {
            angle = Math.toRadians(angle);
        }
        return Math.sin(angle);
    }
}

// Cosine function
class Cosine extends Operation {
    private boolean isDegree;

    public Cosine(boolean isDegree) {
        this.isDegree = isDegree;
    }

    public double calculate(double... operands) {
        double angle = operands[0];
        if (isDegree) {
            angle = Math.toRadians(angle);
        }
        return Math.cos(angle);
    }
}

// Tangent function
class Tangent extends Operation {
    private boolean isDegree;

    public Tangent(boolean isDegree) {
        this.isDegree = isDegree;
    }

    public double calculate(double... operands) {
        double angle = operands[0];
        if (isDegree) {
            angle = Math.toRadians(angle);
        }
        return Math.tan(angle);
    }
}

// Logarithm base 10 (log)
class Log10 extends Operation {
    public double calculate(double... operands) {
        return Math.log10(operands[0]);
    }
}

// Natural logarithm (ln)
class NaturalLog extends Operation {
    public double calculate(double... operands) {
        return Math.log(operands[0]);
    }
}

// Exponential function (e^x)
class Exponential extends Operation {
    public double calculate(double... operands) {
        return Math.exp(operands[0]);
    }
}

// Factorial (n!)
class Factorial extends Operation {
    public double calculate(double... operands) {
        double n = operands[0];
        if (n < 0 || n != (int) n) {
            throw new IllegalArgumentException("Factorial is only defined for non-negative integers.");
        }
        double result = 1;
        for (int i = 1; i <= (int)n; i++) {
            result *= i;
        }
        return result;
    }
}