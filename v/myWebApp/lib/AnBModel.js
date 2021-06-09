
const BasicBModel = require('axthe/BasicBModel.js')
const yaml = require('js-yaml')
const fs = require('fs')
const SUPABASE_URL = "https://qkacsffiqokwdpkrwpyl.supabase.co";
// /////////////////////////////////////////////////////////////
// use for pgSQL
const { createClient } = require('@supabase/supabase-js')

// validation
const validate = require("validate.js")

/**
 * base class has sb property for supabase, used for full stack programming
 */
module.exports = class AnBModel extends BasicBModel {

	_config = yaml.load(fs.readFileSync(process.cwd() + '/config.yaml'));

	_sb = createClient(this._config.SUPABASE_URL, this._config.SUPABASE_KEY);

	constructor() {
		super()
		console.log(process.cwd())
		let data = this.getSearchResults('natural toothpaste', 1)
		data.then(data => data.forEach(element => {
				console.log(element.bestProduct_title)
		}))
	}

	async getSearchResults(query, page) {
		page = page*11
		let { data: Type1Match, error } = await this._sb
			.from('Type1Match')
			.select("*")
			.textSearch('bestProduct_title', query, {
				type: 'websearch',
				config: 'english'
			})
			.range(page, page + 11)

		if (error) console.error(error)
		else return Type1Match
	}

	async getSearchResultsWithFilter(query, page, ...tags) {
		page = page*11
		let { data: Type1Match, error } = await this._sb
			.from('Type1Match')
			.select("*")
			.textSearch('bestProduct_title', query, {
				type: 'websearch',
				config: 'english'
			})
			.contains('tags->0', `${tags}`)
			.range(page, page + 11)

		if (error) console.error(error)
		else return Type1Match
	}

	async getSampleResults() {
		let { data: Type1Match, error } = await this._sb
			.rpc('sample10')

		if (error) console.error(error)
		else return Type1Match
	}

	async getProductByID(ID) {
		let { data: Type1Match, error } = await this._sb
			.from('Type1Match')
			.select("*")
			.eq('_id', ID)

		if (error) console.error(error)
		else return Type1Match
	}

}
