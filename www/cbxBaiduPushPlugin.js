// Empty constructor
var CbxBaiduPushPlugin = function(envInfo) {
    console.log('CbxBaiduPushPlugin#constructor() is called, envInfo = ' + JSON.stringify(envInfo));

    this.envInfo = {...envInfo};
}


CbxBaiduPushPlugin.prototype.startWork = function(successCallback, errorCallback) {
    console.log('CbxBaiduPushPlugin#startWork() with envInfo = ' + JSON.stringify(this.envInfo));

    if (!this.envInfo || !this.envInfo.apiKey) {
        console.error('CbxBaiduPushPlugin#startWork() empty API Key.');
        return;
    }

    var options = {};
    options.apiKey = this.envInfo.apiKey;
    console.log('startWork(): apiKey = ' + options.apiKey);

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
    console.log('CbxBaiduPushPlugin#isPushEnabled()');
    var options = {};

    cordova.exec(successCallback, errorCallback, 'CbxBaiduPushPlugin', 'isPushEnabled', [options]);
}


CbxBaiduPushPlugin.prototype.setTags = function(tags, successCallback, errorCallback) {
    console.log('CbxBaiduPushPlugin#setTags(): tags = ' + JSON.stringify(tags));
    var options = {};
    options.tags = tags;

    cordova.exec(successCallback, errorCallback, 'CbxBaiduPushPlugin', 'setTags', [options]);
}


CbxBaiduPushPlugin.prototype.delTags = function(tags, successCallback, errorCallback) {
    console.log('CbxBaiduPushPlugin#delTags(): tags = ' + JSON.stringify(tags));
    var options = {};
    options.tags = tags;

    cordova.exec(successCallback, errorCallback, 'CbxBaiduPushPlugin', 'delTags', [options]);
}


CbxBaiduPushPlugin.prototype.listTags = function(successCallback, errorCallback) {
    console.log('CbxBaiduPushPlugin#listTags()');

    var options = {};

    cordova.exec(successCallback, errorCallback, 'CbxBaiduPushPlugin', 'listTags', [options]);
}

/*
  onMessage: function(successCallback, failureCallback){
      exec(successCallback, failureCallback, 'BaiduPush', 'onMessage', []);
  },
  onNotificationClicked: function(successCallback, failureCallback){
      exec(successCallback, failureCallback, 'BaiduPush', 'onNotificationClicked', []);
  },
  onNotificationArrived: function(successCallback, failureCallback){
      exec(successCallback, failureCallback, 'BaiduPush', 'onNotificationArrived', []);
  },
*/


module.exports = {
    init: function(envInfo) {

        return new CbxBaiduPushPlugin(envInfo);
    },

    CbxBaiduPushPlugin: CbxBaiduPushPlugin
};
