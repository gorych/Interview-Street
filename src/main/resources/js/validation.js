function invalidPassportData(textbox) {
    if(textbox.validity.patternMismatch){
        textbox.setCustomValidity('����� � ����� �������� ������� �������.');
    }
    else {
        textbox.setCustomValidity('');
    }

    return true;
}