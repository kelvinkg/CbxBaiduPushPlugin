// Empty constructor
var CbxBaiduPushPlugin = function(options) {

    this.options = options;

}

//function CbxBaiduPushPlugin(options) {}

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


CbxBaiduPushPlugin.prototype.stopWork = function(successCallback, errorCallback) {
    var options = {};

    cordova.exec(successCallback, errorCallback, 'CbxBaiduPushPlugin', 'stopWork', [options]);
}


CbxBaiduPushPlugin.prototype.resumeWork = function(successCallback, errorCallback) {
    var options = {};

    cordova.exec(successCallback, errorCallback, 'CbxBaiduPushPlugin', 'resumeWork', [options]);
}


CbxBaiduPushPlugin.prototype.isPushEnabled = function(successCallback, errorCallback) {
    var options = {};
    cordova.exec(successCallback, errorCallback, 'CbxBaiduPushPlugin', 'isPushEnabled', [options]);
}


CbxBaiduPushPlugin.prototype.setTags = function(tags, successCallback, errorCallback) {
    var options = {};
    options.tags = tags;

    cordova.exec(successCallback, errorCallback, 'CbxBaiduPushPlugin', 'setTags', [options]);
}


CbxBaiduPushPlugin.prototype.delTags = function(tags, successCallback, errorCallback) {
    var options = {};
    options.tags = tags;

    cordova.exec(successCallback, errorCallback, 'CbxBaiduPushPlugin', 'delTags', [options]);
}


CbxBaiduPushPlugin.prototype.listTags = function(successCallback, errorCallback) {
    var options = {};

    cordova.exec(successCallback, errorCallback, 'CbxBaiduPushPlugin', 'delTags', [options]);
}

// Installation constructor that binds CbxBaiduPushPlugin to window
/*
CbxBaiduPushPlugin.init = function(options) {
    if (!window.plugins) {
      window.plugins = {};
    }
    window.plugins.cbxBaiduPushPlugin = new CbxBaiduPushPlugin(options);
    return window.plugins.cbxBaiduPushPlugin;
};

//cordova.addConstructor(CbxBaiduPushPlugin.install);
*/

module.exports = {

    init: function(options) {
        console.log('100');
        // comments
        return new CbxBaiduPushPlugin(options);
    },

    CbxBaiduPushPlugin: CbxBaiduPushPlugin
};
