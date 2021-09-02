/**
 * Наибольший общий делитель
 * @param a
 * @param b
 * @returns {*}
 */
function gcd(a, b) {
    return b === 0 ? a : gcd(b, a % b);
}
