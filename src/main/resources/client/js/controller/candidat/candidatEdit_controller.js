App.controller('CandidatEditController', ['$scope','$uibModal','$location','CandidatService', function ($scope,$uibModal,$location,CandidatService) {
	
    var self = this;


    	function findCandidat(id){
    	CandidatService.getCandidat(id)
            .then(
                function (d) {
                    self.candidat = d;
                },
                function (errResponse) {
                    console.error('Error while fetching Users');
                }
            	);
        }
    
        function getFormation(){
        	CandidatService.fetchAllFormation()
                .then(
                    function (d) {
                        self.formations = d;
                    },
                    function (errResponse) {
                        console.error('Error while fetching Users');
                    }
                	);
            }    
        
    findCandidat($location.search().num);
    getFormation();

    self.retourner = function(){
                                var url = '/candidat';
                                $location.path(url);
                                }
    
    self.edit=function(candidat){
    	CandidatService.createCandidat(candidat)
        .then(
            function () {
            	findCandidat($location.search().num);
            });
    }
    
}]);