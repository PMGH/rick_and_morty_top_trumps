package com.example.peter.toptrumps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Peter on 10/11/2017.
 */

public class Game {

//    public static Game INSTANCE = new Game();

    private ArrayList<Playable> players;
    private Dealer dealer;
    private Deck deck;
    private int totalNumCards;
    private Playable playerTurn;
    private String category;
    private Integer valueToBeat;
    private HashMap<Card, Playable> cardsToBeat;
    private Card currentBestCard;
    private Playable roundWinner;
    private HashMap<Card, Playable> pile;
    private boolean gameWon;
    private Playable winner;

    public Game() {
        this.players = new ArrayList<>();
        this.dealer = null;
        this.deck = null;
        this.totalNumCards = 0;
        this.playerTurn = null;
        this.category = null;
        this.valueToBeat = null;
        this.cardsToBeat = new HashMap<>();
        this.currentBestCard = null;
        this.roundWinner = null;
        this.pile = new HashMap<>();
        this.gameWon = false;
        this.winner = null;
    }


    // getters

    public int getNumPlayers(){
        return players.size();
    }

    public ArrayList<Playable> getPlayers(){
        return players;
    }

    public int getDeckSize(){
        return deck.getNumCards();
    }

    public Playable getPlayerTurn() {
        return playerTurn;
    }

    public int getPileSize(){
        return pile.size();
    }

    public String getCategory(){
        return category;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    // other behaviour

//    public static Game getInstance() {
//        return INSTANCE;
//    }

    public void start(){
        // empty players array
        players.clear();

        // setup CPU dealer
        String[] dealerNames = new String[]{ "David", "Christine", "Scott", "Kerry", "Paul" };
        dealer = new Dealer("CPU " + dealerNames[generateRandom(dealerNames.length)]);
        players.add(dealer);

        // set total number of cards within the game
        deck = dealer.getDeck();
        totalNumCards = deck.getNumCards();

        // get player name and create Player instance (at least 2 players incl. CPU needed)
        // CHANGE WHEN USING ANDROID UI
        Player player1 = new Player("Peter");
        players.add(player1);

        Player player2 = new Player("Alan");
        players.add(player2);

        // shuffle the deck
        dealer.shuffle();

        // all the deck's cards are dealt among the players - at least 1 per player
        while (dealer.getDeckSize() > 0){
            for (Playable player : players){
                player.addToHand(dealer.dealTopCard());
            }
        }

        // first playerTurn set to Player at left of dealer
        changeTurn();
    }


    public void play(){
        // while game is not won
        while (!gameWon){
            // get top card and set it to be the card to beat
            cardsToBeat.clear();
            currentBestCard = playerTurn.getTopCard();
            cardsToBeat.put(currentBestCard, playerTurn);

            // player picks category from their topmost card - dropdown box on Android?
            ArrayList<String> categories = new ArrayList<String>();
            categories.add("Intellect");
            categories.add("Lethality");
            categories.add("Morality");
            categories.add("How Schwifty");
            String testCategory = categories.get(generateRandom(categories.size()));
            setCategory(testCategory);

            // set the value to beat - value is displayed / read out (TextValue - and visibility)
            valueToBeat = getCardCategoryValue(currentBestCard, category);

            // each player's top card is added to pile
            addToPile();

            // display other player cards (one by one if time allows for animation). Highlight equivalent value of that category from their topmost card (TextValue - and visibility), Handler for postDelay

            // compareValues() by comparing card category values (weight?)
            compareValues();

            // if round won, add cards to roundWinner's hand from pile
            if (checkRoundWin()){
                winnerTakePileCards();
            }

            // checkWin()
            checkWin();

            // checkEliminated()
            checkEliminated();

            // next Turn
            if (players.size() > 1){
                changeTurn();
            }
        }
    }

    public int generateRandom(int limit){
        Random rand = new Random();
        return rand.nextInt(limit);
    }

    public String setCategory(String category){
        this.category = category;
        return category;
    }

    public Integer getCardCategoryValue(Card topCard, String category){
        switch (category) {
            case "Intellect":
                return topCard.getIntellect();
            case "Lethality":
                return topCard.getLethality();
            case "Morality":
                return topCard.getMorality();
            case "How Schwifty":
                return topCard.getHowSchwifty();
            default:
                return 0;
        }
    }

    public void addToPile(){
        for (Playable player : players){
            Card playerCard = player.removeFromHand(player.getTopCard());
            pile.put(playerCard, player);
        }
    }

    public boolean compareValues(){
        // HashMap<>() cardsToBeat - if card value is more than highest value then added to HashMap and previous highest removed, if equal it is added and there is a draw scenario, else move to next card
        for (Card card : pile.keySet()){
            if (card != currentBestCard){

                Integer nextCardValue = getCardCategoryValue(card, category);

                if (nextCardValue > valueToBeat){
                    cardsToBeat.remove(currentBestCard);
                    cardsToBeat.put(card, pile.get(card));
                    currentBestCard = card;
                    valueToBeat = nextCardValue;
                    return true;
                }

                if (nextCardValue.equals(valueToBeat)){
                    cardsToBeat.put(card, pile.get(card));
                }
            }
        }
        return false;
    }

    public boolean checkRoundWin(){
        if (cardsToBeat.size() == 1){
            roundWinner = (Playable) cardsToBeat.values().toArray()[0];
            return true;
        }
        return false;
    }

    public void winnerTakePileCards(){
        for (Card card : pile.keySet()){
            roundWinner.addToHand(card);
        }
        // clear() used out of loop as remove() might remove all instances of card (multiple of same card in deck - extensible)
        pile.clear();
    }

    public void checkEliminated(){
        // players are eliminated when they lose their last card
        for (Playable player : players){
            if (player.getNumCards() == 0){
                players.remove(player);
            }
        }
    }

    public void checkWin(){
        // the winner is the player who obtains the whole pack
        if (players.size() == 1){
            Playable winningPlayer = players.get(0);
            gameWon = true;
            winner = winningPlayer;
            winningPlayer.addWin();
        }

        // some variants of the rules allow 'three card pick', whereby a player who has only three deckCards remaining is allowed to choose any of their three deckCards to play with. Typically, this lengthens the game considerably.
    }

    public void changeTurn(){
        Collections.rotate(players, 1);
        playerTurn = players.get(0);
    }

}
