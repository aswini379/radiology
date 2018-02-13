function findPatient(mrn) {
    window.parent.postMessage('findPatient:' + mrn, '*');
}

function createPatient(mrn, ptGivenName, ptFamilyName, dob, sex) {
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
                "birthdate": dob,
                "gender": sex
            }
        };
        window.parent.postMessage('createPatient:' + patientJson, '*');
    }
}


var receiveMessage = function (event) {
    if (event.data == 'foundPatient:0') {
        createPatient();
    } else if(event.data.includes('foundPatient:')) {
        console.log('FOUND THE PATIENT');
    }
}
window.addEventListener("message", receiveMessage, true);