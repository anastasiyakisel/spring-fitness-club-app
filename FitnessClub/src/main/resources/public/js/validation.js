var localizedMessages;

function check(formName) {
	
	var buttons = document.getElementById(formName);
	var isChecked = false;
	
	for ( var i = 0; i < buttons.length; i++) {
		if (buttons[i].checked === true){
			isChecked = true;
			return true;
		}			
	}
	if (isChecked==false) {
		alert('Please select at least one item');
	}			
	return false;
}
