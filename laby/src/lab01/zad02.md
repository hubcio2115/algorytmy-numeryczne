# 2.

Nie należy stosować bezpośredniego porównania dla liczb zmiennoprzecinkowych.

```rust
fn main() {
  let a = 0.1;
  let b = 0.2;

  a + b == 0.3; // false - a + b = 0.30000000000000004
}
```

### Przeanalizuj powyższy przykład. Skąd bierze się błąd obliczeń?

Błąd obliczeń w tym przypadku bierze się z faktu, że liczby zmiennoprzecinkowe
nie są reprezentowane dokładnie w pamięci komputera, a jedynie jako przybliżenie
w postaci skończonej liczby bitów. W związku z tym, nie wszystkie liczby
zmiennoprzecinkowe mogą być dokładnie reprezentowane w pamięci, co prowadzi do
niedokładności w obliczeniach.
