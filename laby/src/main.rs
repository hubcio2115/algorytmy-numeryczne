use itertools::Itertools;

fn main() {
    let vec = vec![0.12, 1.24, 0.41, -2.61, -0.43];

    let mut min = vec.iter().cloned().fold(0. / 0., f64::max);
    let mut max = vec.iter().cloned().fold(0. / 0., f64::min);

    for x in vec.iter().permutations(vec.len()) {
        let s = x.iter().fold(0.0, |acc, x| acc + x.to_owned());

        if s > max {
            max = s;
        }
        if s < min {
            min = s
        }
    }

    dbg!(min);
    dbg!(max);
}
