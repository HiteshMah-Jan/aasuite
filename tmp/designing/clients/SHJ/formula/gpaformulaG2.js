function runFormula(e) {
	total = parseInt(e.qallCCF+.5) + parseInt(e.qallFilipino+.5) + parseInt(e.qallScience+.5) + parseInt(e.qallEnglish+.5) + parseInt(e.qallMath+.5) + parseInt(e.qallMakabayan+.5);
	return total/6;
}