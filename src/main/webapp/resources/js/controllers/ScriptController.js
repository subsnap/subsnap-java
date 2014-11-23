
// var ScriptController = function($scope, $http) {
//     this


// };

var subSnapControllers = angular.module('subSnapControllers', ['ngTable','ui.bootstrap']);

subSnapControllers.controller('ScriptController', ['$scope', '$http', '$modal',
  function ($scope, $http, $modal) {
    $scope.goNext = function (hash) { 
        $location.path(hash);
    };
    
    $scope.users = [
        {projectId: 'safasdgvsd', projectName: "Moroni", budget: 1000, status: "pending", dateSubmitted: "1416703568837", genre: "horror", tagline: "Here comes Johnny!", purpose: "finance"},
        {projectId: 'safasdgvsd', projectName: "Tiancum", budget: 4300, status: "declined", dateSubmitted: "1416703568837", genre: "romance", tagline: "Here comes Johnny!", purpose: "finance"},
        {projectId: 'safasdgvsd', projectName: "Jacob", budget: 2700, status: "pending", dateSubmitted: "1416703568837", genre: "sci-fi", tagline: "Here comes Johnny!", purpose: "finance"},
        {projectId: 'safasdgvsd', projectName: "Nephi", budget: 29000, status: "approved", dateSubmitted: "1416703568837", genre: "reality", tagline: "Here comes Johnny!", purpose: "finance"},
        {projectId: 'safasdgvsd', projectName: "Enos", budget: 340000,status: "pending", dateSubmitted: "1416703568837", genre: "horror", tagline: "Here comes Johnny!", purpose: "finance"}
    ];

    $scope.sendEmail = function(project) {
        $modal.open({
            templateUrl: 'scripts/send-modal.html',
            backdrop: true,
            controller: 'EmailController',
            size: 'lg',
            resolve: {
                project: function () {
                    return project;
                }
            }
        });
    };

  }]);

subSnapControllers.controller('EmailController', function ($scope, $modalInstance, project) {

    $scope.project = project;
    $scope.message = {
        project: project.projectId,
        name: '',
        email: '',
        title: 'THE SOCIAL NETWORK',
        body: 'As discussed, attached please find THE SOCIAL NETWORK written by Aaron Sorkin.  David Fincher is attached to direct.  Jesse Eisenberg is attached to star.  I look forward to hearing your thoughts.'
    };

    $scope.firstName = function () {
        var name = $scope.message.name;
        if (name) {
            return name.split(' ')[0] + ',';
        }
        return '';
    };

    $scope.ok = function () {
        $modalInstance.close($scope.message);
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});

