'use strict';

angular.module('myApp').factory('CandidatService', ['$http', '$q', function ($http, $q) {

    var REST_SERVICE_URI = 'http://localhost:8090/candidat/';

    var factory = {
        fetchAllCandidat:fetchAllCandidat,
        getCandidat:getCandidat,
        deleteCandidat:deleteCandidat,
        createCandidat:createCandidat,
        fetchAllFormation:fetchAllFormation
    };

    return factory;

    function fetchAllCandidat() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error pour listers toutes les candidats');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }
    
    function fetchAllFormation() {
        var deferred = $q.defer();
        $http.get("http://localhost:8090/formation/")
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

    function getCandidat(id) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+ 'id/' + id)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error pour retourner candidat');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function deleteCandidat(code) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI + 'delete/' + code)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error pour supprimer un candidat');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function createCandidat(candidat) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, candidat)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error pour creer un candidat');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }
}]);