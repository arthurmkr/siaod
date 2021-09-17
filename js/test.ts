function fib(n) {
    function innerFib(a0, a1, c) {
        return c < n ? innerFib(a1, a0 + a1, c + 1) : a0;
    }

    return innerFib(0, 1, 1);
}

for (var i = 1; i < 10; i++)
    console.log(fib(i));