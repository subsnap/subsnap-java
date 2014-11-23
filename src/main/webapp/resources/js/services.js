'use strict';

/* Services */

var AppServices = angular.module('subSnapServices', ['ngResource']);

AppServices.factory('Project', ['$resource',
    function($resource){
        return $resource('projects', {}, {
            query: { 
                method:'GET', 
                params:{projectId:'projects'}, 
                isArray: true}
            }
        );
    }
]);

AppServices.factory('Message', ['$resource',
    function($resource){
        return $resource('messages', {}, {
            query: { 
                method:'POST', 
                params: { 
                    projectId:'projects' 
                }, 
                isArray: true
            }
        });
    }
]);