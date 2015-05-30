/**
 * Created by phoenix on 5/9/15.
 */
var spitter = angular.module('spitter', []);

spitter.controller('person', function ($scope, $http) {
    $scope.author = "Phoenix";
    $scope.insert = function () {
        $scope.people.push($scope.newPerson);
        var future = $http.post('/person/insert', $scope.newPerson);
        future.success(function (data) {
            console.log($scope.newPerson);
            console.log(data);
        });
        future.error(function (data, status) {
            console.log(data);
            console.log(status);
        });
        $scope.newPerson = undefined;
    };

    $scope.refresh = function () {
        $http.get('/person/selectAll').success(function (data) {
            $scope.people = data;
        });
    };

    $scope.refresh();

    $scope.delete = function (person) {
        $http.get('/person/deleteById/' + person.id).success(function (data) {
            $scope.refresh();
        }).error(function (data, status) {
            console.log(data);
            console.log(status);
        });
    };

    $scope.searchFilter = function (obj) {
        var re = new RegExp($scope.keyword, 'i');
        return !$scope.keyword || re.test(obj.name) || re.test(obj.age.toString());
    };
});