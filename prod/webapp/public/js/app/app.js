var app = angular.module('app', ['ui.bootstrap', 'ngAnimate', 'ui.router', 'angular-loading-bar'])
	.run(['$rootScope', '$state', '$stateParams',
		function ($rootScope,   $state,   $stateParams) {
			'use strict';

			$rootScope.$state = $state;
			$rootScope.$stateParams = $stateParams;

		}])