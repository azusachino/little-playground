const interview = cb => {
    setTimeout(() => {
        if (Math.random() > 0.3) {
            cb(null, 'success')
        } else {
            cb(new Error('fail'))
        }
    }, 500)
}

// 单参数回调
interview((err, res) => {
    if (err) {
        console.error(err)
        return
    }
    console.log(res)
})

// 回调地狱
interview(function (err, res) {
    if (err) {
        console.log('cry at 1')
        return;
    }
    interview(function (err, res) {
        if (err) {
            console.log('cry at 2')
            return;
        }
        interview(function (err, res) {
            if (err) {
                console.log('cry at 3')
                return;
            }
            console.log('smile')
        })
    })
})