package com.example;

public class Calculator {

    /**
     * Adds two integers and returns the result.
     * 
     * @param a The first integer to add.
     * @param b The second integer to add.
     * @return The sum of a and b.
     */
    public int add(int a, int b) {
        return a + b;
    }

    /**
     * Subtracts the second integer from the first and returns the result.
     * 
     * @param a The integer to subtract from.
     * @param b The integer to be subtracted.
     * @return The difference between a and b.
     */
    public int subtract(int a, int b) {
        return a - b;
    }

    /**
     * Multiplies two integers and returns the result.
     * 
     * @param a The first integer to multiply.
     * @param b The second integer to multiply.
     * @return The product of a and b.
     */
    public int multiply(int a, int b) {
        return a * b;
    }

    /**
     * Divides the first integer by the second and returns the result.
     * 
     * @param a The integer to divide.
     * @param b The integer to divide by.
     * @return The quotient of a and b.
     * @throws ArithmeticException if b is zero.
     */
    public int divide(int a, int b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed.");
        }
        return a / b;
    }
}
