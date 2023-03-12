package org.example;

import com.opencsv.CSVWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
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

    for (int i = 2; i <= 20; i += 2) {
      res += pow(x, i) / fac(i) * polar;
      polar *= -1;
    }

    return res;
  }

  // V2
  public static double t_cos_rev(double x) {
    List<Double> res = new ArrayList<>();
    int polar = -1;

    for (int i = 2; i <= 20; i += 2) {
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

    for (int i = 2; i <= 20; i += 2) {
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
    double denominator = 1;
    double meter = 1;

    for (int i = 2; i <= 20; i += 2) {
      denominator *= pow(x, 2);
      meter *= i * (i - 1);
      double term = denominator / meter * polar;

      res.add(term);
      polar *= -1;
    }

    Collections.reverse(res);

    return res.stream().reduce(1.0, Double::sum);
  }

  public static void main(String[] args) throws IOException {
    try {
      String[] headers = {"value", "math_cos", "t_cos", "t_cos_rev", "power_series_cos", "power_series_cos_rev"};
      ArrayList<String[]> result = new ArrayList<>();
      result.add(headers);

      for (double point = -2 * Math.PI; point <= 2 * Math.PI; point += 4 * Math.PI / 1_000_000) {
        String[] row = {
          Double.toString(point),
          Double.toString(Math.cos(point)),
          Double.toString(t_cos(point)),
          Double.toString(t_cos_rev(point)),
          Double.toString(power_series_cos(point)),
          Double.toString(power_series_cos_rev(point))
        };

        result.add(row);
      }

      FileOutputStream fos = new FileOutputStream("./out.csv");
      OutputStreamWriter osw = new OutputStreamWriter(fos);
      CSVWriter writer = new CSVWriter(osw);

      writer.writeAll(result);

      writer.close();
      osw.close();
      fos.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
