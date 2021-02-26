/*
* Creates a hash for a value using the SHA-256 algorithm. Returns a promise.

Use crypto API to create a hash for the given value, setTimeout to prevent blocking on a long operation, and a Promise to give it a familiar interface.
* */


const crypto = require('crypto');
const hashNode = val =>
  new Promise(resolve =>
    setTimeout(
      () =>
        resolve(
          crypto
            .createHash('sha256')
            .update(val)
            .digest('hex')
        ),
      0
    )
  );

// '04aa106279f5977f59f9067fa9712afc4aedc6f5862a8defc34552d8c7206393'
hashNode(JSON.stringify({a: 'a', b: [1, 2, 3, 4], foo: {c: 'bar'}})).then(console.log);
