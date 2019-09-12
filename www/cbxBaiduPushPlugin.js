// Empty constructor
function CbxBaiduPushPlugin() {}

// The function that passes work along to native shells
// Message is a string, duration may be 'long' or 'short'
CbxBaiduPushPlugin.prototype.show = function(message, duration, successCallback, errorCallback) {
    var options = {};
    options.message = message;
    options.duration = duration;
    cordova.exec(successCallback, errorCallback, 'CbxBaiduPushPlugin', 'show', [options]);
}

CbxBaiduPushPlugin.prototype.startWork = function(apiKey, successCallback, errorCallback) {
    var options = {};
    options.apiKey = apiKey;

    cordova.exec(successCallback, errorCallback, 'CbxBaiduPushPlugin', 'startWork', [options]);
}





// Installation constructor that binds CbxBaiduPushPlugin to window
CbxBaiduPushPlugin.install = function() {
    if (!window.plugins) {
      window.plugins = {};
    }
    window.plugins.cbxBaiduPushPlugin = new CbxBaiduPushPlugin();
    return window.plugins.cbxBaiduPushPlugin;
};

cordova.addConstructor(CbxBaiduPushPlugin.install);