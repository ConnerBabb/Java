
// ////////////////////////////////
const BasicPreRouter = require('axthe/BasicPreRouter.js')
const AnBModel = require('./AnBModel.js')

module.exports = class APreRouter extends BasicPreRouter {// pug pre render. Most pages don't need a pre render, but some do

	constructor(eapp) {
		super(eapp)// call base and pass the express app, so it knows

		this.bmodel = new AnBModel() // the model is used for data, validation, business layer and such. You would of course extends BasicBModel

		this.firstPrep()

		this.eapp.get('/screen/item', (req, res) => {
			res.render(this.getPath(req), {
				items: [
					{ name: 'one1', imageUrl: 'xxx' },
					{ name: 'one20' },
					{ name: 'one30' },
				]
			})
		})


		this.eapp.get('/1', (req, res) => {
			res.render(this.getPath(req), { title: 'My name is Mr K' })
		})

		this.eapp.post('/form', (req, res) => {
			console.log(this.constructor.name, req.body)
			res.send("received your request!")
		})

		// to finish setup
		this.finalPrep()
	}//()

}//class

