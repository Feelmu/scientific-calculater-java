package memory;

public class MemoryManager {
    private double memory = 0;

    public void store(double value) {
        memory = value;
    }

    public double recall() {
        return memory;
    }

    public void clear() {
        memory = 0;
    }

    public void add(double value) {
        memory += value;
    }

    public void subtract(double value) {
        memory -= value;
    }
}
