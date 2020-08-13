var app = angular.module("springDemo", []);
app.controller("AppCtrl", function ($scope, $http, $window) {

    $scope.websites = [];

    $http.get('http://localhost:8099/api/stackoverflow').then(function(response) {

        $scope.websites = response.data;

    }).catch(function (data) {
        console.log("Ошибка", data);
        $window.location.href = "errorStackExhange.html"

     });

});


app.controller("AppCtrlFromApiStackExchange", function ($scope, $http , $window) {

    $scope.websites = [];

    $http.get('http://localhost:8099/api/stackexchange').then(function(response) {
        $scope.websites = response.data;
    }).catch(function (data) {
        console.log("Ошибка", data);
        $window.location.href = "errorStackExhange.html"

    });;

});

app.controller("AppCtrlHealth", function ($scope, $http , $window)
{

    $scope.websites = "";

    $http.get('http://localhost:8099/api/check')
    .then(function(response) {

        $scope.websites = response.data;

    }).catch(function (data) {

        console.log("Ошибка " , data)
    });

});
