const range = (low, high, step) => {
    let cur = low;
    return () => {
        if (cur > high) return null;

        const res = cur;
        cur += step;
        return res;
    }
}

let arrStr1 = range(1, 10, 2);

let v;
while (v = arrStr1()) {
    console.log(v)
}
