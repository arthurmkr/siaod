import * as math from './math.js';

function emptyStream() {
    return newStream(null, null)
}

function newStream(cur, next) {
    return {
        cur: () => cur,
        next: next,
        isEmpty: () => cur == null
    }
}

function intRange(low, high) {
    return low > high ? emptyStream() : newStream(low, () => intRange(low + 1, high));
}

function filter(predicate, stream) {
    if (stream.isEmpty()) {
        return emptyStream();
    } else if (predicate(stream.cur())) {
        return newStream(stream.cur(), () => filter(predicate, stream.next()))
    } else {
        return filter(predicate, stream.next())
    }
}

function iterate(stream, handler, limit) {
    let index = 0;
    while (!stream.isEmpty() && (limit !== undefined && index < limit)) {
        handler(stream.cur());
        stream = stream.next();
        index++;
    }
}

// iterate(
//     filter(x => x % 2 === 0,
//         intRange(10, 16)),
//     console.log);

function infinityStream(start, next) {
    return newStream(start, () => infinityStream(next(start), next))
}

function naturalInt(start) {
    return infinityStream(start, x => x + 1)
}

// iterate(
//     filter(x => x % 2 === 0,
//         infinityStream(2, x => x * 2)),
//     console.log, 6);
//

function randomNumbers() {
    return newStream(Math.random(), () => randomNumbers());
}

// iterate(
//     randomNumbers(),
//     console.log, 6);

console.log(math.gcd(12, 16))


