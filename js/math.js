/**
 * Наибольший общий делитель
 * @param a
 * @param b
 * @returns {*}
 */
function gcd(a, b) {
    return b === 0 ? a : gcd(b, a % b);
}

/**
 * Расчет N-го числа Фибоначчи
 * Итеративный процесс с использованием хвостовой рекурсии
 * @param n
 * @returns {*}
 */
function fib(n) {
    function innerFib(a0, a1, c) {
        return c >= n ? a0 : innerFib(a1, a0 + a1, c + 1);
    }

    return innerFib(0, 1, 1);
}
