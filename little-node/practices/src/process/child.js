process.on('message', (str) => {
    console.log('child received ' + str)
})