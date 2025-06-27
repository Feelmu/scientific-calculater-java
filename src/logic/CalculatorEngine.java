package logic;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class CalculatorEngine {

    private ScriptEngine engine;
    private boolean degreeMode;
    private double lastAnswer = 0;

    public CalculatorEngine(boolean degreeMode) {
        this.degreeMode = degreeMode;
        initEngine();
    }

    private void initEngine() {
        ScriptEngineManager mgr = new ScriptEngineManager();
        engine = mgr.getEngineByName("JavaScript");

        try {
            // Define math functions with degree/radian conversion dynamically
            engine.eval(
                    "function toRadians(x) { return " + (degreeMode ? "(x * Math.PI / 180)" : "x") + "; }" +
                            "function fromRadians(x) { return " + (degreeMode ? "(x * 180 / Math.PI)" : "x") + "; }" +

                            "var sin = function(x) { return Math.sin(toRadians(x)); };" +
                            "var cos = function(x) { return Math.cos(toRadians(x)); };" +
                            "var tan = function(x) { return Math.tan(toRadians(x)); };" +

                            "var asin = function(x) { return fromRadians(Math.asin(x)); };" +
                            "var acos = function(x) { return fromRadians(Math.acos(x)); };" +
                            "var atan = function(x) { return fromRadians(Math.atan(x)); };" +

                            // log base 10 fallback
                            "var log = function(x) { return Math.log10 ? Math.log10(x) : Math.log(x)/Math.LN10; };" +
                            "var ln = function(x) { return Math.log(x); };" +
                            "var sqrt = function(x) { return Math.sqrt(x); };" +
                            "var exp = function(x) { return Math.exp(x); };" +
                            "var pow = Math.pow;" +

                            // Factorial function for non-negative integers
                            "var fact = function(n) { " +
                            "  if(n < 0 || n !== Math.floor(n)) return NaN;" +
                            "  var res = 1; for(var i=2; i<=n; i++) res *= i; return res;" +
                            "};" +

                            // Last answer variable
                            "var Ans = 0;"
            );
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    public void setDegreeMode(boolean degreeMode) {
        this.degreeMode = degreeMode;
        initEngine();  // reinitialize with new mode
    }

    /**
     * Supports usage of "Ans" for last answer.
     * @param expression The mathematical expression to evaluate.
     * @return The computed result as a double.
     * @throws ScriptException if the expression is invalid.
     */
    public double calculate(String expression) throws ScriptException {
        expression = expression.trim();

        // Prepend "Ans" if expression starts with an operator to chain calculations
        if (!expression.contains("Ans") && (expression.startsWith("+") || expression.startsWith("-") || expression.startsWith("*") || expression.startsWith("/"))) {
            expression = "Ans" + expression;
        }

        // Update Ans variable in JS engine with lastAnswer value
        engine.eval("Ans = " + lastAnswer + ";");

        // Evaluate the expression
        Object result = engine.eval(expression);

        if (result instanceof Number) {
            lastAnswer = ((Number) result).doubleValue();
            return lastAnswer;
        } else if (result instanceof Double) {
            lastAnswer = (Double) result;
            return lastAnswer;
        } else {
            return Double.NaN;
        }
    }

    // Resets stored last answer to zero
    public void resetLastAnswer() {
        lastAnswer = 0;
    }
}
