var app = angular.module('app',['ngRoute'])
.config(['$routeProvider', function($routeProvider){
    $routeProvider
    .when('/',{templateUrl: 'views/dashboard.html'})
    .when('/accounting',{template:'账套页面'})
    .when('/printers',{template:'这是打印机页面'})
    .when('/rzqs',{templateUrl:'views/rzqs.html'})
    .when('/voucher',{templateUrl:'views/voucher.html'})
    .otherwise({redirectTo:'/'});
}]);

