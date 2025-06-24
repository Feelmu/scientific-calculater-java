package logic;

// Abstract base class for all math operations
public abstract class Operation {
    public abstract double calculate(double... operands);
}

class Addition extends Operation {
    public double calculate(double... operands) {
        return operands[0] + operands[1];
    }
}

class Subtraction extends Operation {
    public double calculate(double... operands) {
        return operands[0] - operands[1];
    }
}

class Multiplication extends Operation {
    public double calculate(double... operands) {
        return operands[0] * operands[1];
    }
}

class Division extends Operation {
    public double calculate(double... operands) {
        if (operands[1] == 0) {
            throw new ArithmeticException("Cannot divide by zero.");
        }
        return operands[0] / operands[1];
    }
}

class Power extends Operation {
    public double calculate(double... operands) {
        return Math.pow(operands[0], operands[1]);
    }
}

class Percentage extends Operation {
    public double calculate(double... operands) {
        return operands[0] % operands[1];
    }
}

class SquareRoot extends Operation {
    public double calculate(double... operands) {
        return Math.sqrt(operands[0]);
    }
}

class Sine extends Operation {
    private boolean isDegree;
    public Sine(boolean isDegree) {
        this.isDegree = isDegree;
    }
    public double calculate(double... operands) {
        double angle = operands[0];
        if (isDegree) angle = Math.toRadians(angle);
        return Math.sin(angle);
    }
}

class Cosine extends Operation {
    private boolean isDegree;
    public Cosine(boolean isDegree) {
        this.isDegree = isDegree;
    }
    public double calculate(double... operands) {
        double angle = operands[0];
        if (isDegree) angle = Math.toRadians(angle);
        return Math.cos(angle);
    }
}

class Tangent extends Operation {
    private boolean isDegree;
    public Tangent(boolean isDegree) {
        this.isDegree = isDegree;
    }
    public double calculate(double... operands) {
        double angle = operands[0];
        if (isDegree) angle = Math.toRadians(angle);
        return Math.tan(angle);
    }
}

class Arcsine extends Operation {
    private boolean isDegree;
    public Arcsine(boolean isDegree) {
        this.isDegree = isDegree;
    }
    public double calculate(double... operands) {
        double value = Math.asin(operands[0]);
        return isDegree ? Math.toDegrees(value) : value;
    }
}

class Arccosine extends Operation {
    private boolean isDegree;
    public Arccosine(boolean isDegree) {
        this.isDegree = isDegree;
    }
    public double calculate(double... operands) {
        double value = Math.acos(operands[0]);
        return isDegree ? Math.toDegrees(value) : value;
    }
}

class Arctangent extends Operation {
    private boolean isDegree;
    public Arctangent(boolean isDegree) {
        this.isDegree = isDegree;
    }
    public double calculate(double... operands) {
        double value = Math.atan(operands[0]);
        return isDegree ? Math.toDegrees(value) : value;
    }
}

class Log10 extends Operation {
    public double calculate(double... operands) {
        return Math.log10(operands[0]);
    }
}

class NaturalLog extends Operation {
    public double calculate(double... operands) {
        return Math.log(operands[0]);
    }
}

class Exponential extends Operation {
    public double calculate(double... operands) {
        return Math.exp(operands[0]);
    }
}

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

class TenPowerX extends Operation {
    public double calculate(double... operands) {
        return Math.pow(10, operands[0]);
    }
}
