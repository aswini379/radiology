function findPatient(mrn) {
    window.parent.postMessage('findPatient|' + mrn, '*');
}

function createPatient(param) {
    var mrn = $('#'+param).data('mrn');
    var patientName = $('#'+param).data('patientname');
    var ptGivenName='FNU';
    var ptFamilyName='LNU';
    if(patientName.split(',').length == 1) {
        ptFamilyName = $('#'+param).data('patientname').split(',')[0];
    } else {
        ptGivenName =$('#'+param).data('patientname').split(',')[1];
        ptFamilyName = $('#'+param).data('patientname').split(',')[0];
    }
    var dob = $('#'+param).data('dob').toString();
    var sex = $('#'+param).data('sex');

    if (mrn != '' || mrn != undefined) {
        var patientJson = {
            "identifiers": [{
                "identifier": mrn,
                "identifierType": "8d79403a-c2cc-11de-8d13-0010c6dffd0f",
                "location": "Unknown Location"
            }],
            "person": {
                "names": [{
                    "givenName": ptGivenName,
                    "familyName": ptFamilyName
                }],
                "birthdate": dob.slice(0,4) + '-' + dob.slice(4,6) + '-' + dob.slice(6),
                "gender": sex
            }
        };
        window.parent.postMessage('createPatient|' + JSON.stringify(patientJson), '*');
    }
}


var receiveMessage = function (event) {
    var functionName = event.data.split('|')[0];
    var param = event.data.split('|')[1];
    if (functionName == '0') {
        if (confirm('Patient not found. Are you sure you want to create a matching patient to claim the study?')) {
            createPatient(param);
        } else {
            // Do nothing!
        }
    } else if(functionName == 'foundPatient') {
        var uuid = param;
        if (confirm('Patient matched. Are you sure you want to claim the study?')) {
            window.parent.postMessage('newReport|'+ uuid, '*');
        } else {
            // Do nothing!
        }
    }
}

window.addEventListener("message", receiveMessage);