App.controller('MessageController', ['$scope','$rootScope','$uibModal','$location', function ($scope,$rootScope,$uibModal,$location) {
    var self = this;
    self.modalOptions=$rootScope.modalOptions;
	self.modalOptionsTemp=$rootScope.modalOptionsTemp;
}]);