'use strict';

	angular.module('myApp').factory('AdminService', ['$http', '$q', function ($http, $q) {

    var REST_SERVICE_URI = 'http://localhost:8090/';

    var factory = {
    		countFormation:countFormation,
    		countEnseignant:countEnseignant,
    		countCandidat:countCandidat,
    		countPromotion:countPromotion
    };
    
    return factory;
    
    function countFormation() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+"formation/count/")
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }
    
    function countEnseignant() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+"enseignant/count/")
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }
    
    function countCandidat() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+"candidat/count/")
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }
    
    function countPromotion() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+"promotion/count/")
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }
    
}]);