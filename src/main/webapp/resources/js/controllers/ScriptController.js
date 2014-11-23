
// var ScriptController = function($scope, $http) {
//     this


// };

var subSnapControllers = angular.module('subSnapControllers', ['ngTable']);

subSnapControllers.controller('ScriptController', ['$scope', '$http', 'ngTableParams', '$location',
  function ($scope, $http, ngTableParams, $location) {
    $scope.goNext = function (hash) { 
        $location.path(hash);
    };
    
    $scope.users = [
        {projectName: "Moroni", budget: 1000, status: "pending"},
        {projectName: "Tiancum", budget: 4300, status: "declined"},
        {projectName: "Jacob", budget: 2700, status: "pending"},
        {projectName: "Nephi", budget: 29000, status: "approved"},
        {projectName: "Enos", budget: 340000,status: "pending"}
    ];

    // this.tableParams = new ngTableParams({
    //     page: 1,            // show first page
    //     count: 10,          // count per page
    //     sorting: {
    //         name: 'asc'     // initial sorting
    //     }
    // }, {
    //     total: data.length,
    //     getData: function($defer, params) {
    //         console.log(data);
    //         $defer.resolve($filter('orderBy')(data, params.orderBy()));
    //     }
    // });
  }]);

// phonecatControllers.controller('PhoneDetailCtrl', ['$scope', '$routeParams',
//   function($scope, $routeParams) {
//     $scope.phoneId = $routeParams.phoneId;
//   }]);