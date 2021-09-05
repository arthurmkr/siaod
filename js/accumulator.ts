/**
 * Накапливает значение в переменной из замыкания
 * @param value
 * @returns {function(*): *}
 */
function makeAccum(value) {
    return addValue => value += addValue;
}

const accum1 = makeAccum(3);

console.assert(accum1(2) === 5)
console.assert(accum1(10) === 15)

