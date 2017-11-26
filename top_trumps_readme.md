# **Rick and Morty Top Trumps**    

![Image](/top_trumps_readme/images/r_m_splash.png)
![Image](/top_trumps_readme/images/r_m_rules.png)
![Image](/top_trumps_readme/images/r_m_main.png)
![Image](/top_trumps_readme/images/r_m_top_card.png)
![Image](/top_trumps_readme/images/r_m_result.png)
![Image](/top_trumps_readme/images/r_m_game_over.png)

## Project:    

I started the **Rick and Morty Top Trumps** project while at CodeClan in Glasgow. I initially planned on creating a simple card game such as BlackJack or Highest Card but I am a Rick and Morty fan and decided it would be much more fun and challenging to create a game of Top Trumps instead.    

I was lucky enough to stumble across a post on Reddit, by user 3schmeckles (thank you!), that contained a selection of Rick and Morty Top Trump cards which I used for the character card statistics. The original post can be found here: [Reddit: Rick and Morty Top Trumps](https://redd.it/3zl2l5).    

### *MVP:*    

##Card game**    

*Goal: Create a card game in Android.*

The game does not need to have much or any interaction. The aim is to display the results of the Java logic already written. For example, if you had two players being dealt two cards each your screen might look something like:    

  - Player 1: Ace of Hearts, 9 of Diamonds
  - Player 2: 3 of Clubs, 2 of Hearts
  - Player 1 Wins!

Project Extensions:    

  * Possibly add another card game
  * Improve the UI    

### *Planning:*    

The planning stage of the project initially involved reviewing the **project spec** to clarify what needed to be done to achieve the MVP. Following this I drew up **class and object diagrams** and an **inheritance diagram** in [Draw.io](https://www.draw.io/) to solidify my thoughts on the classes I would need, the properties they would possess, the methods they would require and the connections they would have to other classes (e.g. inheritance - extensions) and any interfaces they might implement. Next, for **user experience considerations** and as a blueprint to follow I created several **wireframes** using [NinjaMock](https://ninjamock.com/account/register) and then wrote **pseudocode** before coding up.    

### *Code Overview:*    

I created the game using **Object Oriented Programming**, with the game logic and object classes created using **Test Driven Development** and **Java in Android Studio**.    

### *A Little Deeper Into The Guts:*    
I decided to try out the **Singleton design pattern** for the game logic as it made **data persistence** across the application activities possible. However, I am now aware that in some cases the Singleton design pattern (with the exception of Unity) is generally considered bad practice as it makes the class instance globally accessible, violates the single responsibility principle of SOLID, preserves state throughout the life of the application which also violates testing best practice. I came across some of these issues during testing where previous tests would alter the state of the Game instance, impacting subsequent tests. I struggled to understand what was causing this issue at first due to my novice understanding of Singleton, took a fair bit of time trying to understand the problem and then eventually found a suitable solution; **Reflection**. For a game this size, Singleton worked for what I needed but in hindsight I would use a database to store the application state instead.      

## Getting Started    

These instructions aim to get a copy of the project up and running on your local machine for development and testing purposes.    

### Prerequisites    

* Android Studio installed on your machine.  
* An emulator set up on Android Studio or an Android device you can run the application on.    

### Clone    

The project can be cloned and opened (from version control) using the GitHub option on Android Studio.     

The game can be cloned with SSH using:  
[git@github.com:PMGH/rick_and_morty_top_trumps.git](git@github.com:PMGH/rick_and_morty_top_trumps.git)    

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
