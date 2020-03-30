const canadianDollar = 0.91

const roundTwo = (amount) => {
    return Math.round(amount*100)/100
}

exports.canadian2Us = canadian => roundTwo(canadian*canadianDollar)
exports.us2Canadian = us => roundTwo(us/canadianDollar)
