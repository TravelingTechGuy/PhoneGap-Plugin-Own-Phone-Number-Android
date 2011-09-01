//constructor
var MyPhoneNumberPlugin = {
	getMyPhoneNumber: function(onSuccess, onError) {
		console.log("at getMyPhoneNumber");
		return PhoneGap.exec(onSuccess, onError, "MyPhoneNumberPlugin", "getMyPhoneNumber", []);
	}
}
