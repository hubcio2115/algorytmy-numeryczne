```rust
fn main() {
    let value: f32 = 12.5;
    let ba = value.to_ne_bytes();

    for bytes in ba {
        print!("0x{:02x}, ", bytes) // 0x00, 0x00, 0x48, 0x41, ⏎
    }
}
```
