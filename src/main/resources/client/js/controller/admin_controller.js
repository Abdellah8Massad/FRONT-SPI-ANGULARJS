App.controller('AdminController', ['$scope','$uibModal','AdminService', function ($scope,$uibModal,AdminService) {
	var self = this;
	
    function fetchAllCount(){
    	AdminService.countFormation()
            .then(
                function (d) {
                    self.formation = d;
                },
                function (errResponse) {
                    console.error('Error while fetching Users');
                }
        );
    	
    	AdminService.countEnseignant()
        .then(
            function (d) {
                self.enseignant = d;
            },
            function (errResponse) {
                console.error('Error while fetching Users');
            }
        );
    	
    	AdminService.countCandidat()
        .then(
            function (d) {
                self.candidat = d;
            },
            function (errResponse) {
                console.error('Error while fetching Users');
            }
        );
    	
    	AdminService.countPromotion()
        .then(
            function (d) {
                self.promotion = d;
            },
            function (errResponse) {
                console.error('Error while fetching Users');
            }
        );
    	
    }
    fetchAllCount();
}]);