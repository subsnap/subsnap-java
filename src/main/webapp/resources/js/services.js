'use strict';

/* Services */

var AppServices = angular.module('subSnapServices', ['ngResource']);

// AppServices.factory('Project', ['$resource',
//     function($resource){
//         return $resource('projects', {}, {
//             query: { 
//                 method:'GET', 
//                 params:{projectId:'projects'}, 
//                 isArray: true}
//             }
//         );
//     }
// ]);

// AppServices.factory('Message', ['$resource',
//     function($resource){
//         return $resource('messages', {}, {
//             query: { 
//                 method:'POST', 
//                 params: { 
//                     projectId:'projects' 
//                 }, 
//                 isArray: true
//             }
//         });
//     }
// ]);

AppServices.factory('SendEmail', ['$resource', 
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


