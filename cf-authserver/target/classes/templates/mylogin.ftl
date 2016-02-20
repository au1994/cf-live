<!DOCTYPE html>
<html lang="en">

    <head>

        <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.0-rc2/css/bootstrap.min.css" type="text/css" />
        <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.js"></script>

    </head>

    <body ng-app="myApp">

        <div ng-controller="formctrl">
            <p ng-model = "err" ng-show = "err">error logging in</p>
            <form id="submit_form" role="form" action="login" method="post">
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" ng-model="user.userName" class="form-control" id="username" name="username"/>
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" ng-model="user.password" class="form-control" id="password" name="password"/>
                </div>
                <input type="hidden" id="csrf_token" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-primary">Submit</button>
                <button ng-click="myFunction" class="btn btn-primary">programatically</button>
            </form>





        <section padding="40px">

            <fb:login-button scope="public_profile,email" onlogin="checkLoginState();">
            </fb:login-button>


            <div margin="40px" ng-show="Facebook.getAuth()">
                <h4>Access Token:</h4>
                <p ng-bind="Facebook.getAuth().accessToken"></p>
                <button class='btn btn-small' ng-click="Facebook.logout()">Logout</button>
            </div>

            <div id='fb-root'></div>
            <div id="status">
            </div>

        </section>

        </div>

    <script>
        var app = angular.module('myApp', []);



        app.controller('formctrl', function($scope,$location) {
            $scope.err = false;
            if($location.absUrl().indexOf("error") > -1) {
                $scope.err = true;
            }
            $scope.master = {userName: "user", password: "pwd"};
            $scope.reset = function () {
                $scope.user = angular.copy($scope.master);
            };
            $scope.reset();


            $scope.myFunction = function() {

                (document.getElementById("submit_form")).submit();

            };



        });




        app.factory('Facebook', function() {

            var self = this;
            this.auth = null;

            return {
                checkloginstatus: function() {
                    FB.getLoginStatus(function(response) {
                        statusChangeCallback(response);
                    });

                },

                getAuth: function() {
                    return self.auth;
                },

                login: function() {

                    FB.login(function(response) {
                        if (response.authResponse) {
                            conlose.log("yes done!");
                            checkLoginState();
                            self.auth = response.authResponse;
                        } else {
                            console.log('Facebook login failed', response);
                        }
                    })

                },

                logout: function() {

                    FB.logout(function(response) {
                        if (response) {
                            self.auth = null;
                        } else {
                            console.log('Facebook logout failed.', response);
                        }

                    })

                }

            }

        });

            function statusChangeCallback(response) {
                console.log('statusChangeCallback');
                console.log(response);
                // The response object is returned with a status field that lets the
                // app know the current login status of the person.
                // Full docs on the response object can be found in the documentation
                // for FB.getLoginStatus().
                if (response.status === 'connected') {
                    // Logged into your app and Facebook.
                    testAPI();
                } else if (response.status === 'not_authorized') {
                    // The person is logged into Facebook, but not your app.
                    document.getElementById('status').innerHTML = 'Please log ' +
                            'into this app.';
                } else {
                    // The person is not logged into Facebook, so we're not sure if
                    // they are logged into this app or not.
                    document.getElementById('status').innerHTML = 'Please log ' +
                            'into Facebook.';
                }
            }

            function testAPI() {
                console.log('Welcome!  Fetching your information.... ');
                FB.api('/me', function(response) {
                    console.log('Successful login for: ' + response.name);
                    document.getElementById('status').innerHTML =
                            'Thanks for logging in, ' + response.name + '!';
                    console.log(response);
                    document.getElementById("username").value=response.id;
                    document.getElementById("password").value=response.id;
                    (document.getElementById("submit_form")).submit();


                });
            }


            function checkLoginState() {
                FB.getLoginStatus(function(response) {
                    statusChangeCallback(response);
                });
            }


        // This is called with the results from from FB.getLoginStatus().








        window.fbAsyncInit = function() {
            FB.init({
                appId: '564672750367062',
                //cookie     : true,  // enable cookies to allow the server to access
                // the session
                xfbml      : true,  // parse social plugins on this page
                version    : 'v2.2' // use version 2.2
            });
        };

        // Load the SDK Asynchronously
        (function(d, s, id) {
            var js, fjs = d.getElementsByTagName(s)[0];
            if (d.getElementById(id)) return;
            js = d.createElement(s); js.id = id;
            js.src = "//connect.facebook.net/en_US/sdk.js";
            fjs.parentNode.insertBefore(js, fjs);
        }(document, 'script', 'facebook-jssdk'));






    </script>


    </body>
</html>