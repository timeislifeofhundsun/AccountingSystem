angular.module('app',['ngRoute'])
.config(['$routeProvider', function($routeProvider){
    $routeProvider
    .when('/',{templateUrl:'view/dashboard.html'})
    .when('/accounting',{template:'账套页面'})
    .when('/printers',{template:'这是打印机页面'})
    .when('/voucher',{templateUrl:'view/voucher.html'})
    .otherwise({redirectTo:'/'});
}]);

$(function(){

})