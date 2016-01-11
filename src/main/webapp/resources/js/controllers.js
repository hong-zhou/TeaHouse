var cartApp = angular.module('cartApp', []);

cartApp.controller('cartCtrl', function($scope, $http){
	
	$scope.refreshCart = function(cartId){
		$http.get('/teahouse/rest/cart' + $scope.cartId)
		.success(function(data){
			$scope.cart = data;
		});
	};
	
	$scope.clearCart = function(){
		$http.delete('/teahouse/rest/cart/' + $scope.cartId)
		.success($scope.refreshCart($scope.cartId));
	};
	
	$scope.initCartid = function(cartId){
		$scope.cartId = cartId;
		$scope.refreshCart($scope.cartId);
	};
	
	$scope.addToCart = function(productId){
		$http.put('/teahouse/rest/cart/add/' + productId)
		.sucess(function(data){
			$scope.refreshCart($http.get('/teahouse/rest/get/cartId'));
			alert("Product Successfully added to the Cart!");
		});
	};
	
	$scope.removeFromCart = function(productId){
		$http.put('/teahouse/rest/cart/remove/' + productId)
		.success(function(data){
			$scope.refreshCart($http.get('/teahouse/rest/cart/get/cartId'));
		});
	};
});
