'use strict';

angular.module('myApp').factory('FormationService', ['$http', '$uibModal', '$q', function ($http,$uibModal, $q) {

    var REST_SERVICE_URI = 'http://localhost:8090/formation/';

    var factory = {
        fetchAllFormation:fetchAllFormation,
        getFormation:getFormation,
        createFormation:createFormation,
        getFormationCode:getFormationCode,
        deleteFormation:deleteFormation
    };

    return factory;

    function fetchAllFormation() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error pour listers toutes les formations');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function getFormation(nom) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + nom)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error pour retourner une formation');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function getFormationCode(code) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + 'code/' + code)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error pour retourner une formation');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function deleteFormation(code) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI + 'delete/' + code)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error pour supprimer une formation');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function createFormation(formation) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, formation)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error pour creer une formation');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }
}]);