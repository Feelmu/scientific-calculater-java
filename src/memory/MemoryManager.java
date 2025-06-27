package memory;

/**
 * Manages memory storage for calculator (MS, MR, MC, M+, M-)
 */
public class MemoryManager {
    private double memoryValue = 0.0;

    public void store(double value) {
        memoryValue = value;
    }

    public double recall() {
        return memoryValue;
    }

    public void clear() {
        memoryValue = 0.0;
    }

    public void add(double value) {
        memoryValue += value;
    }

    public void subtract(double value) {
        memoryValue -= value;
    }
}
