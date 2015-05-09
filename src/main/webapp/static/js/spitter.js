/**
 * Created by phoenix on 5/9/15.
 */
angular.module('spitter', [])
    .controller('person', function($scope, $http) {
        $scope.author = "Phoenix";
        
        $http.get('/spitter/person/selectAll').success(function(data) {
            $scope.people = data;
        });
    });
