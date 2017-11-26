# **Rick and Morty Top Trumps**    

![Image](/top_trumps_readme/images/r_m_splash.png)
![Image](/top_trumps_readme/images/r_m_rules.png)
![Image](/top_trumps_readme/images/r_m_main.png)
![Image](/top_trumps_readme/images/r_m_top_card.png)
![Image](/top_trumps_readme/images/r_m_result.png)
![Image](/top_trumps_readme/images/r_m_game_over.png)

## Origins:    

I started the Rick and Morty Top Trumps project while at CodeClan in Glasgow. I initially planned on creating a simple card game such as BlackJack or Highest Card but I am a Rick and Morty fan and decided it would be much more fun and challenging to create a game of Top Trumps instead.    

I was lucky enough to stumble across a post on Reddit, by user 3schmeckles, that contained a selection of Rick and Morty Top Trump cards which I used for the character card statistics. The original post can be found here: [Reddit: Rick and Morty Top Trumps](https://redd.it/3zl2l5).    

I created the game using Object Oriented Programming, with the game logic and object classes created using Test Driven Development and Java in Android Studio.    

### *For those more technically minded:*    
I decided to try out the Singleton design pattern for the game logic as it made data persistence across the application activities possible. However, I am now aware that in some cases the Singleton design pattern (with the exception of Unity) is generally considered bad practice as it makes the class instance globally accessible, violates the single responsibility principle of SOLID, preserves state throughout the life of the application which also violates testing best practice. I came across some of these issues during testing where previous tests would alter the state of the Game instance, impacting subsequent tests. I struggled to understand what was causing this issue at first due to my novice understanding of Singleton, took a fair bit of time trying to understand the problem and then eventually found a suitable solution; Reflection. For a game this size, Singleton worked for what I needed but in hindsight I would use a database to store the application state instead.    

## Getting Started    

These instructions aim to get a copy of the project up and running on your local machine for development and testing purposes.    

### Prerequisites    

* Android Studio installed on your machine.  
* An emulator set up on Android Studio or an Android device you can run the application on.    

### Clone    

The project can be cloned and opened (from version control) using the GitHub option on Android Studio.     

The game can be cloned with SSH using:  
git@github.com:PMGH/rick_and_morty_top_trumps.git    

You can run the application through an emulator or connect your Android device to your computer/laptop and run the it from Android Studio to deploy it on your device.    

## Built With:  
* Test Driven Development (JUnit)  
* Android Studio  
* Java    


## Authors  
* Peter McCready - Initial work    

## Acknowledgments  
* Rick and Morty (Justin Roiland and Dan Harmon)  
* 3schmeckles (Reddit User)  
