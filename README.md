# Rick And Morty App

This repository contains Android card swipe application by using RickAndMortyAPI.

<img src=screenshot.png width="220" height="360">

## Functionality

The application is composed of 1 main screen. This screen displays the details of the characters(name, status and last known location) in a card view. The card can be swiped and after each swiped character is changed with new one.

## Building

You can clone and open the project in Android studio and press run!


## Technical details

The Application implemented using Java programming language and structured based on **Clean Architecture** and **SOLID** principles best practices and the presentation layer is implemented based on the **MVP** pattern.

**Retrofit API** is used in order to take the data from RickAndMortyAPI. While processing the data pagination is used.


## Libraries

- **Picasso** image downloading and caching library made by [square](https://github.com/square/picasso)
- **Retrofit** API libraries made by [square](https://github.com/square/retrofit)