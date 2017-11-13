package com.example.peter.toptrumps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


/**
 * Created by Peter on 10/11/2017.
 */

public class Game {

    private static Game INSTANCE = new Game();

    private ArrayList<Playable> players;
    private Dealer dealer;
    private Player player1;
    private Deck deck;
    private int totalNumCards; // for testing
    private Playable playerTurn;
    private String category;
    private Integer valueToBeat;
    private HashMap<Card, Playable> cardsToBeat;
    private Card currentBestCard;
    private Playable roundWinner;
    private Card roundWinningCard;
    private Integer roundWinningScore;
    private HashMap<Card, Playable> pile;
    private boolean gameWon;
    private Playable winner;

    private ArrayList<String> cpuBestCategory;
    private Integer cpuCategoryLowestDelta;
    private Integer roundNumber;


    public Game() {
        this.players = new ArrayList<>();
        this.totalNumCards = 0;
        this.playerTurn = new Player(null);
        this.category = "";
        this.valueToBeat = 0;
        this.cardsToBeat = new HashMap<>();
        this.currentBestCard = new Card(null, null, null, null, null, null, null);
        this.roundWinner = new Player(null);
        this.roundWinningCard = new Card(null, null, null, null, null, null, null);
        this.roundWinningScore = 0;
        this.pile = new HashMap<>();
        this.gameWon = false;
        this.winner = new Player(null);

        this.cpuBestCategory = new ArrayList<>();
        this.cpuCategoryLowestDelta = 101;
        this.roundNumber = 1;
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

    public Integer getRoundNumber() {
        return roundNumber;
    }

    public Playable getRoundWinner() {
        return roundWinner;
    }

    public Card getRoundWinningCard() {
        return roundWinningCard;
    }

    public Integer getRoundWinningScore() {
        return roundWinningScore;
    }

    public int getPileSize(){
        return pile.size();
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Playable getWinner() {
        return winner;
    }

    // setters

    public String playerSetCategory(String category){
        this.category = category;
        return this.category;
    }


    // game behaviour

    public static Game getInstance() {
        return INSTANCE;
    }

    public void start(String playerName){
        // clear players array each time a new game is started
        players.clear();

        // setup CPU dealer
        String[] dealerNames = new String[]{ "David", "Christine", "Scott", "Kerry", "Paul" };
        this.dealer = new Dealer("CPU " + dealerNames[generateRandom(dealerNames.length)]);
        players.add(dealer);

        // set total number of cards within the game
        this.deck = dealer.getDeck();
        totalNumCards = deck.getNumCards();

        // add new player - Android UI, name provided is used to create new Player instance and added to players
        player1 = new Player(playerName);
        players.add(player1);

        // set first turn to left of dealer
        this.playerTurn = players.get(1);

        // shuffle the deck
        dealer.shuffle();

        // all the deck's cards are dealt among the players - at least 1 per player
        while (dealer.getDeckSize() > 0){
            for (Playable player : players){
                player.addToHand(dealer.dealTopCard());
            }
        }
    }

    public void play(){
        // while game is not won
        while (!isGameWon()){
            if (playerTurn.equals(player1)){
                category = playerSetCategory("Intellect");
            } else {
                category = getBestCategory();
            }
            playRound(category);
        }
        System.out.println(winner.getName() + " won!");
    }

    public void playRound(String category){
        // increment round number
        roundNumber++;

        // clear cardsToBeat HashMap
        cardsToBeat.clear();

        // get playerTurn topCard and set it to be the cardToBeat
        currentBestCard = playerTurn.getTopCard();
        cardsToBeat.put(currentBestCard, playerTurn);

        // set the value to beat - value is displayed / read out (TextValue - and visibility)
        valueToBeat = getCardCategoryValue(currentBestCard, category);

        // each player's top card is added to pile
        addToPile();

        // compareValues() by comparing card category values (weight?)
        compareValues();

        // if round won, add cards to roundWinnerName's hand from pile
        if (checkRoundWin()){
            winnerTakePileCards();
        }

        // checkEliminated()
        checkEliminated();

        // checkWin()
        checkWin();

        // next Turn
        if (players.size() > 1) {
            changeTurn();
        }
    }


    // ancillary functions

    public int generateRandom(int limit){
        Random rand = new Random();
        return rand.nextInt(limit);
    }

    public int getCardCategoryValue(Card topCard, String category){
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

    public String getBestCategory(){
        Card card = dealer.getTopCard(); // change this to if statement to check if player is a bot
        cpuBestCategory.clear();
        cpuCategoryLowestDelta = 101;

        HashMap<String, Integer> deltas = new HashMap<>();
        deltas.put("Intellect", 100 - card.getIntellect());
        deltas.put("Lethality", 100 - card.getLethality());
        deltas.put("Morality", 100 - card.getMorality());
        deltas.put("How Schwifty", (10 - card.getHowSchwifty()) * 10);

        for (String delta : deltas.keySet()){
            Integer value = deltas.get(delta);

            if (value < cpuCategoryLowestDelta) {
                cpuBestCategory.clear();
                cpuCategoryLowestDelta = value;
                cpuBestCategory.add(delta);
            } else if (value.equals(cpuCategoryLowestDelta)){
                cpuBestCategory.add(delta);
            }
        } return cpuBestCategory.get(generateRandom(cpuBestCategory.size()));
    }

    public void addToPile(){
        for (Playable player : players){
            Card playerCard = player.removeFromHand(player.getTopCard());
            pile.put(playerCard, player);
        }
    }

    public void compareValues(){
        // HashMap<>() cardsToBeat - if card value is more than highest value then added to HashMap and previous highest removed, if equal it is added and there is a draw scenario, else move to next card
        for (Card card : pile.keySet()){
            if (card != currentBestCard){
                Integer nextCardValue = getCardCategoryValue(card, category);

                if (nextCardValue > valueToBeat){
                    cardsToBeat.remove(currentBestCard);
                    cardsToBeat.put(card, pile.get(card));
                    currentBestCard = card;
                    valueToBeat = nextCardValue;
                } else if (nextCardValue.equals(valueToBeat)){
                    cardsToBeat.put(card, pile.get(card));
                }
            }
        }
    }

    public boolean checkRoundWin(){
        if (cardsToBeat.size() == 1){
            for (Card card : cardsToBeat.keySet()){
                roundWinner = cardsToBeat.get(card);
                roundWinningCard = card;
                roundWinningScore = getCardCategoryValue(card, category);
            }
        } return true;
    }

    public void winnerTakePileCards(){
        for (Card card : pile.keySet()){
            roundWinner.addToHand(card);
        }
        pile.clear();
    }

    public void checkEliminated(){
        // players are eliminated when they lose their last card
        ArrayList<Playable> toRemove = new ArrayList<>();

        for (Playable player : players){
            if (player.getNumCards() == 0){
                toRemove.add(player);
            }
        } players.removeAll(toRemove);
    }

    public void checkWin(){
        // the winner is the player who obtains the whole pack
        if (players.size() == 1){
            Playable winningPlayer = players.get(0);
            gameWon = true;
            winner = winningPlayer;
            winningPlayer.addWin();
        }
    }

    public void changeTurn(){
        playerTurn = roundWinner;
    }

}
