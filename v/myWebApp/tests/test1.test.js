const test = require('tape')
const AnBModel     = require('../lib/AnBModel.js')

test('test sample', async function (t) {
    t.plan(1)

    const abm = new AnBModel()
    const c = await abm._config
    t.ok(c)
})
