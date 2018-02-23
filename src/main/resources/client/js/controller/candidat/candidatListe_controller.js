App.controller('CandidatListController', ['$scope','$uibModal','$location','CandidatService', function ($scope,$uibModal,$location,CandidatService) {
	
    var self = this;
	self.settings = {
                        currentPage: 0,
                        offset: 0,
                        pageLimit: 5,
                        pageLimits: ['10', '50', '100']
                        };
     self.sort = function(keyname){
        self.sortKey = keyname;  
        self.reverse = !self.reverse;
    }

    function fetchAll(){
    	CandidatService.fetchAllCandidat()
            .then(
                function (d) {
                    self.candidats = d;
                },
                function (errResponse) {
                    console.error('Error while fetching Users');
                }
        );
        }
    fetchAll();

    self.detail = function(id){
                                var url = '/candidatEdit';
                                $location.path(url).search({num: id});
                                }
    
    self.ajouterCandidat = function(){
        var url = '/candidatAdd';
        $location.path(url);
        }
    
    self.remove = function(id){
						    	Promise.resolve(CandidatService.deleteCandidat(id)).then(function(){
						    		fetchAll();
						    	});
						    	fetchAll();
						    	fetchAll();
						    	fetchAll();
    						  }
    
}]);