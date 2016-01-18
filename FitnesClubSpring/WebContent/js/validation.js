var localizedMessages;

function check() {
	
	var buttons = document.getElementsByName("view");
	
	for ( var i = 0; i < buttons.length; i++) {
		if (buttons[i].checked === true)
			return true;
	}
	alert('Please select at least one training');
	return false;
}
