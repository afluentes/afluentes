package afluentes.impl;

import java.rmi.Remote;

public interface ICalculator extends Remote {
    public double subtraction(double x, double y);
    public double multiplication(double x, double y);
}