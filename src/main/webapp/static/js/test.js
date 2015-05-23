/**
 * Created by phoenix on 5/22/15.
 */
var diy = angular.module('diy', []);

diy.controller('hardware', function ($scope, $http) {
    var ctrl = this;
    ctrl.total = 0;
    $scope.initModel = function() {
        $scope.model = {};
        $scope.component = "Select Component";
    };
    
    $scope.items = [];


    
    $scope.hardware = {};
    $scope.hardware.components = [
        "Processor", "Memory", "Disk", "BaseBoard"
    ];

    $scope.setComponent = function(index) {
        if ($scope.model == undefined) {
            $scope.model = {};
        }
        $scope.model.component = $scope.hardware.components[index];
        $scope.component = $scope.model.component;
    };

    $scope.addItem = function(item) {
        if ($scope.hardware.components.indexOf($scope.component) > -1) {
            item.component = $scope.component;
            $scope.items.push(item);
            $scope.initModel();            
        }
    };

    $scope.removeItem = function(index) {
        $scope.items.remove(index);
    };

    $scope.initModel();
});