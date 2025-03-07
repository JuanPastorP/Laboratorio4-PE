package com.example.Laboratorio4.presentation.navgraph

sealed class Route (
    val route: String
){
    object OnBoardingScreen : Route(route = "onBoardingScreen")

    object HomeScreen : Route(route = "homeScreen")

    object SearchScreen : Route(route = "searchScreen")

    object BookmarkScreen : Route(route = "bookMarkScreen")

    object DetailsScreen : Route(route = "detailsScreen")

    object AppStartNavigation : Route(route = "appStartNavigation")

    object NewsNavigation : Route(route = "newsNavigation")

    object NewsNavigatorScreen : Route(route = "newsNavigator")

    object MapScreen : Route(route = "mapScreen")

    object GoogleMap : Route(route = "googleMap")

}