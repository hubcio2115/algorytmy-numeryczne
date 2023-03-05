package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
  private final int numberOfRuns = 10;

  public static double fac(double n) {
    if (n == 0) return 0;

    double result = n;
    for (int i = 0; i < n - 1; i++) result *= i + 1;

    return result;
  }

  public static double pow(double x, int n) {
    if (x == 0) return 0;
    if (n == 0) return 1;

    double res = 1;
    for (int i = 0; i < n; i++) res *= x;

    return res;
  }


  // V1
  public static double t_cos(double x) {
    double res = 1;
    int polar = -1;

    for (int i = 2; i <= 10; i += 2) {
      res += pow(x, i) / fac(i) * polar;
      polar *= -1;
    }

    return res;
  }

  // V2
  public static double t_cos_rev(double x) {
    List<Double> res = new ArrayList<>();
    int polar = -1;

    for (int i = 2; i <= 10; i += 2) {
      res.add(pow(x, i) / fac(i) * polar);
      polar *= -1;
    }

    Collections.reverse(res);

    return res.stream().reduce(1.0, Double::sum);
  }

  // V3
  public static double power_series_cos(double x) {
    double res = 1;
    double polar = -1;
    double denominator = 1;
    double meter = 1;

    for (int i = 2; i <= 10; i += 2) {
      denominator *= pow(x, 2);
      meter *= i * (i - 1);
      double term = denominator / meter * polar;

      res += term;
      polar *= -1;
    }

    return res;
  }

  // V4
  public static double power_series_cos_rev(double x) {
    List<Double> res = new ArrayList<>();
    double polar = -1;
    double denominator = pow(x, 10);
    double meter = fac(10);

    for (int i = 10; i > 0; i -= 2) {
      double term = denominator / meter * polar;
      res.add(term);

      denominator /= pow(x, 2);
      meter /= i * (i - 1);
      polar *= -1;
    }

    Collections.reverse(res);

    return res.stream().reduce(1.0, Double::sum);
  }

  public static void main(String[] args) {
    double point = 30 * (Math.PI / 180.0);

    System.out.println("Math.cos: " + Math.cos(point));
    System.out.println("t_cos: " + t_cos(point));
    System.out.println("t_cos_rev: " + t_cos_rev(point));
    System.out.println("power_series_cos: " + power_series_cos(point));
    System.out.println("power_series_cos_rev: " + power_series_cos_rev(point));
  }
}
