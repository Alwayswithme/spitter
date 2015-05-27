/**
 * Created by phoenix on 5/22/15.
 */
var diy = angular.module('diy', []);

diy.controller('hardware', function ($scope, $http,  $locale) {
    // initialize
    var ctrl = this;
    $scope.initModel = function() {
        $scope.model = {};
        $scope.component = "Select Component";
    };
    $scope.initModel();
    $scope.items = [];
    $scope.hardware = {};
    $scope.hardware.components = [
        "Processor", "Cooling", "BaseBoard", "Memory",
        "Disk", "Graphics",  "Case", "PSU",
        "Monitor", "Keyboard", "Mouse"
    ];

    $scope.model.local = $locale;
    // function
    $scope.setComponent = function(index) {
        if ($scope.model == undefined) {
            $scope.model = {};
        }
        $scope.model.component = $scope.hardware.components[index];
        $scope.component = $scope.model.component;
    };

    $scope.resetComponent = function() {
        $scope.initModel();
    };

    $scope.addItem = function(item) {
        if ($scope.hardware.components.indexOf($scope.component) > -1) {
            item.component = $scope.component;
            $scope.items.push(item);
            $scope.initModel();            
        }
    };

    $scope.removeItem = function(index) {
        $scope.model.index = index;
        $scope.items.splice(index, 1);
    };

    this.clearPlan = function() {
        $scope.items.length = 0;
    };

    this.savePlan = function() {
        if (this.planName) {
            this.planName = '';
        }
        $scope.items.length = 0;
    };

    this.getTotalCost = function() {
        return $scope.items.map(function(item){
            return item.price * item.quantity;
        }).reduce(function(a, b){
            return a + b;
        }, 0);
    };

    this.print = function() {
        var title = "Componets    Brand Model    Quantity\n";
        var all = "";
        itemStrs = $scope.items.map(function(item){
            return item.component + ":   " + item.brandModel + "    " + item.quantity + "\n";
        });
        itemStrs.forEach(function(str) {
           all += str;
        });

        return title + all;
    }
});