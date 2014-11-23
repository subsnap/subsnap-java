'use strict';

/* Services */

var AppServices = angular.module('subSnapServices', ['ngResource']);

AppServices.factory('Project', ['$resource',
    function($resource){
        return $resource('/projects/', {}, {
            query: { 
                method:'GET', 
                // params: {projectId:'projects'}, 
                isArray: true
            }
        });
    }
]);

AppServices.factory('SendEmail', ['$resource',
    function($resource) {
        return $resource('/sends/:id', {}, {
            PostSend: { method:'POST', params: {} },
            GetSends: { method: 'GET', params: { id: 0 } }


            // query: { 
            //     params: { 
            //         projectId:'projects' 
            //     }, 
            //     isArray: true
            // }
        });
    }
]);

// function TodoService($resource) {
//     var src = $resource('api/1/todo/:id:cmd',
//               {id: "@id", cmd: "@cmd"}, //parameters default
//               {
//                 ListTodos: { method: "GET", params: {} },
//                 GetTodo: { method: "GET", params: { id: 0 } },                            
//                 CreateTodo: { method: "POST", params: { content: "", order: 0, done: false } },
//                 UpdateTodo: { method: "PATCH", params: { /*...*/ } },
//                 DeleteTodo: { method: "DELETE", params: { id: 0 } },
//                 ResetTodos: { method: "GET", params: { cmd: "reset" } },
//               });


AppServices.factory('SendGridEmail', ['$resource', 
    function ($resource) {
        return $resource('https://api.sendgrid.com/api/mail.send.json', {}, {
            query: { 
                method:'POST', 
                params: {
                    api_user: 'subsnap',
                    api_key: '5195Wint!',
                    to: '',
                    toName: '',
                    subject: '',
                    text: '',
                    from: 'Jenny@subsnap.com',
                }
            }
        });
    }
]);  
// api_user=your_sendgrid_username&
// api_key=your_sendgrid_password&
// to[]=destination@example.com&
// toname[]=Destination&
// to[]=destination2@example.com&
// toname[]=Destination2&
// subject=Example_Subject&
// text=testingtextbody&
// from=info@domain.com  


