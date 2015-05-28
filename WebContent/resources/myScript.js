//<![CDATA[
function validateAlphabet(e) {
	var event = e || window.event;
	var key = event.keyCode || event.which;
	key = String.fromCharCode(key);

	var regex = /[A-Za-z\s]/;
	return regex.test(key);
}

function validateNumbers(e) {
	var event = e || window.event;
	var key = event.keyCode || event.which;
	key = String.fromCharCode(key);

	var regex = /[0-9]/;
	return regex.test(key);
}
// ]]>
