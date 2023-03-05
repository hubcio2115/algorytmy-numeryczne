// Konwersja ułamków w pomiędzy systemami liczenia, w tym ułamków okresowych.
//
// Przykład:
//
// 17.3(10)  =  10001.01(0011)(2)
//
// Oblicz:
// 0.3(12)(10)  = ? (2)
// 0.1(010)(2) = ? (10)
//
//
//
// Jakie ułamki mają skończone rozwinięcie dwójkowe?
// Skończone rozwinięcie dwójkowe mają tylko ułamki postaci 2^n gdzie n to liczba naturalna.
// Oznacza to tyle, że tylko ułamki z mianowsnikiem będącym potęgą 2 mają skończone rozwinięcie dwójkowe.


use fraction::ToPrimitive;

fn fraction_to_bin(fraction: &f32) -> String {
  let mut results: Vec<i32> = vec![];
  let mut fractions: Vec<f32> = vec![];

  let mut result = fraction.clone();
  while !fractions.contains(&result) {
    let whole = (result * 2.0).to_i32().unwrap();

    results.push(whole);
    fractions.push(result);

    if whole == 1 {
      result = result * 2.0 - 1.0;
    } else {
      result *= 2.0
    }
  }

  return format!(
    "{}",
    results
      .iter()
      .map(|num| num.to_string())
      .collect::<Vec<String>>()
      .join("")
  );
}

fn convert_to_bin(float: &f32) -> String {
  let whole = float.to_i32().unwrap();
  let fraction = float.fract();

  let whole_as_bin = format!("{:b}", whole);
  let fraction_as_bin = fraction_to_bin(&fraction);

  return whole_as_bin + "." + fraction_as_bin.to_string().as_str();
}

fn main() {
  let num: f32 = 0.312;

  println!("{}", convert_to_bin(&num));
}
