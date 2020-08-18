const promise = new Promise((resolve, reject) => {
    setTimeout(() => {
        resolve(Math.random() * 10)
    }, 500)

    if (Math.random() > 0.5) {
        reject(new Error('failed'))
    }
})

promise.then((r) => console.log(r)).catch((e) => console.error(e))

interview(1)
    .then(() => {
        return interview(2);
    })
    .then(() => {
        return interview(3);
    })
    .then(() => {
        if (Math.random() > 0.1) {
            const error = new Error('keyboard')
            error.round = 'keyboard'
            throw error
        }
    })
    .catch((err) => {
        console.log('cry at ' + err.round)
    })

function interview(round) {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            if (Math.random() < 0.2) {
                const error = new Error('failed');
                error.round = round;
                reject(error);

            } else {
                resolve('success');
            }
        }, 500)
    })
}

/**
 * 通过Promise.all完成异步并行任务
 */

Promise.all([
    family('father').catch(() => {
    }),
    family('mother'),
    family('wife'),

]).then(() => {
    console.log('family all agree')

}).catch((err) => {
    console.log(err.name + ' not agree');
})

function family(name) {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            if (Math.random() < 0.2) {
                const error = new Error('disagree');
                error.name = name;
                reject(error);

            } else {
                resolve('agree');
            }
        }, Math.random() * 400)
    })
}

interview(1)
    .then(() => {
        return interview(2);
    })
    .then(() => {
        return interview(3);
    })
    .then(() => {
        return Promise.all([
            family('father').catch(() => { /* 忽略老爸的的反对意见 */}),
            family('mother'),
            family('wife'),

        ]).catch(e => {
            e.round = 'family'
            throw e;
        })
    })
    .then(() => {
        console.log('success');
    })
    .catch((err) => {
        console.log('cry at ' + err.round)
    })



