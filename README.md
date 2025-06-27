git# Scientific Calculator (Java Swing)

This is a desktop Scientific Calculator application developed in Java using the Swing framework. It supports both basic arithmetic and advanced scientific operations, including trigonometric functions, logarithmic functions, exponential functions, factorial, and memory storage.

## Features

- **Basic Operations:** Addition (+), Subtraction (-), Multiplication (×), Division (÷)
- **Scientific Functions:**
    - Trigonometric: sin, cos, tan
    - Inverse Trigonometric: asin, acos, atan
    - Logarithmic: log (base 10), ln (natural log)
    - Exponential: e^x, 10^x
    - Square root: √
    - Power: x^y
    - Factorial: n!
- **Memory Functions:** MS (Memory Store), MR (Memory Recall), MC (Memory Clear), M+ (Memory Add), M- (Memory Subtract)
- **Error Handling:** Displays appropriate error messages like "Invalid Input" or "Division by Zero"
- **Input Validation:** Prevents crashes from invalid formats
- **Real-Time Display Updates:** Input and output appear instantly
- **User-Friendly GUI:** Dark mode, Casio-style layout


## How to Compile and Run

### Step 1: Compile

```bash
javac -d bin src/*.java src/ui/*.java src/logic/*.java src/memory/*.java
java -cp bin ui.MainFrame
```
## OOP Principles Used

Encapsulation: Components and logic are modularized into classes
Abstraction: Complex math operations are hidden behind simple method calls
Inheritance and Polymorphism: Structured calculation operations
Modularity: Separated logic, UI, and memory for clean codebase
Exception Handling: Robust handling of invalid input and errors
Requirements

Java 11 or later
Any IDE (IntelliJ IDEA, Eclipse) or command line terminal

## Team

Segun Abraham Oladimeji – GUI Developer
Jeonghu Heo – Logic Developer
Alan Fabricio Delqadillo Vargas – Memory


## License

This project is for educational purposes. All rights reserved.