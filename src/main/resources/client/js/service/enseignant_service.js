'use strict';

angular.module('myApp').factory('EnseignantService', ['$http', '$q', function ($http, $q) {

    var REST_SERVICE_URI = 'http://localhost:8090/enseignant/';

    var factory = {
        fetchAllEnseignant:fetchAllEnseignant,
        getEnseignant:getEnseignant,
        getEnseignantMail:getEnseignantMail,
        deleteEnseignant:deleteEnseignant,
        createEnseignant:createEnseignant
    };

    return factory;

    function fetchAllEnseignant() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error pour listers toutes les enseignant');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function getEnseignant(nom) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+ 'nom/' + nom)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error pour retourner des enseignants');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function getEnseignantMail(mail) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + 'mail/' + mail)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error pour retourner une enseignant');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function deleteEnseignant(code) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI + 'delete/' + code)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error pour supprimer une enseignant');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function createEnseignant(enseignant) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, enseignant)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error pour creer une enseignant');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }
}]);