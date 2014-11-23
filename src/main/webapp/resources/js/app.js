'use strict';

var SubSnapApp = {};

var App = angular.module('SubSnap', [
    // 'SubSnap.filters', 
    // 'SubSnap.services', 
    // 'SubSnap.directives', 
    'subSnapControllers',
    'ngTable',
    'ngRoute'
]);

App.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider
        .when('/scripts', {
            controller: 'ScriptController',
            templateUrl: 'scripts/layout.html'
        })
        .when('/scripts/new', {
            controller: '',
            templateUrl: 'scripts/new-script.html'
        })
        .otherwise(
            {
                redirectTo: '/scripts'
            }
        );
}]);