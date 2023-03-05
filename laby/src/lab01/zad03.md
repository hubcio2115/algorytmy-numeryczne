```rust
use fraction::ToPrimitive;

fn compute_one_64(iter: usize) -> f64 {
    let mut sum = 0.0;
    let addend = 1.0 / iter.to_f64().unwrap();

    for _ in 0..iter {
        sum += addend;
    }

    sum
}

fn compute_one_32(iter: usize) -> f32 {
    let mut sum: f32 = 0.0;
    let append = 1.0 / iter.to_f32().unwrap();

    for _ in 0..iter {
        sum += append;
    }

    sum
}

fn main() {
    // 64 bit results
    dbg!(compute_one_64(1024) - 1.0); // 0.0
    dbg!(compute_one_64(1000) - 1.0); // 6.661338147750939e-16
    dbg!(compute_one_64(10000) - 1.0); // -9.381384558082573e-14
    dbg!(compute_one_64(100000) - 1.0); // -1.91624494050302e-12


    let x1024 = (0.0 - compute_one_64(1024) + 1.0).abs(); // 0.0
    let x1000 = (0.0 - compute_one_64(1000) + 1.0).abs(); // 6.661338147750939e-16
    let x10000 = (0.0 - compute_one_64(10000) + 1.0).abs(); // -9.381384558082573e-14
    let x100000 = (0.0 - compute_one_64(100000) + 1.0).abs(); // -1.91624494050302e-12

    dbg!(x1024);
    dbg!(x1000);
    dbg!(x10000);
    dbg!(x100000);

    println!("");

    // 32 bit results
    dbg!(compute_one_32(1024) - 1.0); // 0.0
    dbg!(compute_one_32(1000) - 1.0); // -9.298325e-6
    dbg!(compute_one_32(10000) - 1.0); // 5.352497e-5
    dbg!(compute_one_32(100000) - 1.0); // 0.0009901524

    let x1024 = (0.0f32 - compute_one_32(1024) + 1.0).abs(); // 0.0
    let x1000 = (0.0f32 - compute_one_32(1000) + 1.0).abs(); // -9.298325e-6
    let x10000 = (0.0f32 - compute_one_32(10000) + 1.0).abs(); // 5.352497e-5
    let x100000 = (0.0f32 - compute_one_32(100000) + 1.0).abs(); // 0.0009901524

    dbg!(x1024);
    dbg!(x1000);
    dbg!(x10000);
    dbg!(x100000);

}
```

błąd bezwzględny = 2

błąd względny nie istnieje, ponieważ x = 0
