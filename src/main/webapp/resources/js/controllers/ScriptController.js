
// var ScriptController = function($scope, $http) {
//     this


// };

var subSnapControllers = angular.module('subSnapControllers', ['ngTable','ui.bootstrap']);

subSnapControllers.controller('ScriptController', ['$scope', '$http', '$modal', 'SendGridEmail', 'Project', 'SendEmail',
  function ($scope, $http, $modal, SendGridEmail, Project, SendEmail) {
    $scope.goNext = function (hash) { 
        $location.path(hash);
    };
    
    // $scope.projects = [
    //     {projectId: 'safasdgvsd', projectName: "The Social Network", writer: "Aaron Sorkin", director: "David Fincher", star: "Jesse Eisenberg", dateSubmitted: "1416703568837"},
    //     {projectId: 'safsafas', projectName: "Requim For A Dream", writer: "Hubert Selby Jr.", director: "Darren Aronofsky", star: "Jarad Leto",  dateSubmitted: "1416703568837"},
    //     {projectId: 'safasvdgvsd', projectName: "Eternal Sunshine of the Spotless Mind", writer: "Charlie Kaufman", director: "Michel Gondry", star: "Jim Carrey", dateSubmitted: "1416703568837"},
    //     {projectId: 'safaasfassdgvsd', projectName: "Reservoir Dogs", writer: "Quentin Tarantino", director: "Quentin Tarantino", star: "Harvey Keitel", dateSubmitted: "1416703568837"},
    //     {projectId: 'safaavasdgvsd', projectName: "Snatch", writer: "Guy Ritchie", director: "Guy Ritchie", star: "Brad Pitt", dateSubmitted: "1416703568837"}
    // ];
    
    $scope.projects = Project.query({}, function (projects) {
        for (var i = projects.length - 1; i >= 0; i--) {
            projects[i].showLog = false;
        }
        return projects;
    });

    // $scope.getSends = function (project) {
    //     sendEmail.$GetSends({ id: project.projectId }, function (send) {
    //         debugger;
    //     });
    // };
    $scope.toggleLogs = function (projectIndex) {
        project = $scope.projects[projectIndex];
        $scope.projects[projectIndex].showLog = !project.showLog;
    };
    $scope.hasLogs = function (project) {
        return project.sends.length;
    };
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
            var sendEmail = new SendEmail();
            sendEmail.$PostSend({
                projectId: message.projectId,
                sendMail: [
                    {
                        sendEmailName: message.toName,
                        sendEmailAddress: message.email,
                        isWatermarked: message.isWatermarked,
                        sendEmailTitle: message.title,
                        sendEmailBody: message.body
                    }
                ]
            });

            // var send = new SendGridEmail({
            //     to: message.email,
            //     toName: message.name,
            //     subject: message.title,
            //     text: message.body,
            //     api_user: 'subsnapAPI',
            //     api_key: '5195Wint!',
            //     from: 'Jenny@subsnap.com'
            // }, function () {
            //     alert(response);
            // });
            // send.$save();
        });
    };

  }]);

subSnapControllers.controller('EmailController', function ($scope, $modalInstance, project) {

    var assembleMessage = function (project) {
        var string = 'As discussed, attached please find ' + project.projectName.toUpperCase();
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
        toName: '',
        email: '',
        isWatermarked: false,
        title: project.projectName.toUpperCase(),
        body: assembleMessage(project)
    };

    $scope.contactNames = ["Ari Gold", "Alex Trebeck", "Aston Kutcher", "James Franco", "Quentin Tarantino", "Bret Easton Ellis"];


    $scope.firstName = function () {
        var name = $scope.message.toName;
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

