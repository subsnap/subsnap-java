
// var ScriptController = function($scope, $http) {
//     this


// };

var subSnapControllers = angular.module('subSnapControllers', ['ngTable','ui.bootstrap']);

subSnapControllers.controller('ScriptController', ['$scope', '$http', '$modal', 'SendEmail',
  function ($scope, $http, $modal, SendEmail) {
    $scope.goNext = function (hash) { 
        $location.path(hash);
    };
    
    $scope.projects = [
        {projectId: 'safasdgvsd', name: "The Social Network", writer: "Aaron Sorkin", director: "David Fincher", star: "Jesse Eisenberg", dateSubmitted: "1416703568837"},
        {projectId: 'safsafas', name: "Requim For A Dream", writer: "Hubert Selby Jr.", director: "Darren Aronofsky", star: "Jarad Leto",  dateSubmitted: "1416703568837"},
        {projectId: 'safasvdgvsd', name: "Eternal Sunshine of the Spotless Mind", writer: "Charlie Kaufman", director: "Michel Gondry", star: "Jim Carrey", dateSubmitted: "1416703568837"},
        {projectId: 'safaasfassdgvsd', name: "Reservoir Dogs", writer: "Quentin Tarantino", director: "Quentin Tarantino", star: "Harvey Keitel", dateSubmitted: "1416703568837"},
        {projectId: 'safaavasdgvsd', name: "Snatch", writer: "Guy Ritchie", director: "Guy Ritchie", star: "Brad Pitt", dateSubmitted: "1416703568837"}
    ];


    $scope.sendEmail = function(project) {
        var resultPromise = $modal.open({
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

        resultPromise.result.then(function(message) {
            var send = new SendEmail({
                to: message.email,
                toName: message.name,
                subject: message.title,
                text: message.body,
                                    api_user: 'subsnap',
                    api_key: '5195Wint!',
                    from: 'Jenny@subsnap.com'
            }, function () {
                alert(response);
            });
            send.$save();


            // Movie.get({ id: $scope.id }, function() {
            //   // $scope.entry is fetched from server and is an instance of Entry
            //     $scope.entry.data = 'something else';
            //     $scope.entry.$delete(function() {
            //     //gone forever!
            // });
// });
            // alert('email sent!!!' + message);
            // @TODO post result to server
        });
    };

  }]);

subSnapControllers.controller('EmailController', function ($scope, $modalInstance, project) {

    var assembleMessage = function (project) {
        var string = 'As discussed, attached please find ' + project.name.toUpperCase();
        if (project.writer) {
            string += ' written by ' + project.writer;
        }
        
        string += '. ';
        if (project.director) {
            string += project.director + ' is attached to direct. ';
        }

        if (project.star) {
            string += project.star + ' is attached to star. ';
        }
        return string + 'I look forward to hearing your thoughts.';
    };

    $scope.project = project;
    $scope.message = {
        projectId: project.projectId,
        name: '',
        email: '',
        isWatermarked: false,
        title: '',
        body: assembleMessage(project)
    };

    $scope.contactNames = ["Ari Gold", "Alex Trebeck", "Aston Kutcher", "James Franco", "Quentin Tarantino", "Bret Easton Ellis"];


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

