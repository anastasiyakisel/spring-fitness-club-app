function check(buttons, message) {
	if (buttons == null)
		return false;
	for ( var i = 0; i < buttons.length; i++) {
		if (buttons[i].checked === true)
			return true;
	}
	alert(message);
	return false;
}
