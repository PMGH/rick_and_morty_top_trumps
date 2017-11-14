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
    private ArrayList<String> cpuBestCategory;
    private HashMap<Card, Playable> pile;
    private HashMap<Card, Playable> cardsToBeat;
    private Dealer dealer;
    private Deck deck;
    private Player player1;
    private String category;
    private Card currentBestCard;
    private Card roundWinningCard;
    private int totalNumCards;
    private int numUnusedCards;
    private Integer valueToBeat;
    private Integer roundNumber;
    private Integer roundWinningScore;
    private boolean roundDraw;
    private boolean gameWon;
    private Playable playerTurn;
    private Playable roundWinner;
    private Playable winner;


    public Game() {
        this.players = new ArrayList<>();
        this.cpuBestCategory = new ArrayList<>();

        this.cardsToBeat = new HashMap<>();
        this.pile = new HashMap<>();

        this.totalNumCards = 0;
        this.roundNumber = 1;
        this.valueToBeat = 0;
        this.roundWinningScore = 0;

        this.roundDraw = false;
        this.gameWon = false;
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

    public boolean isRoundDraw() {
        return roundDraw;
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

        // the deck's cards are dealt among the players - at least 1 per player
        // if the deck is not divisible evenly by the number of players then dealing stops
        numUnusedCards = dealer.getDeckSize() % getNumPlayers();
        while (dealer.getDeckSize() > numUnusedCards){
            for (Playable player : players){
                player.addToHand(dealer.dealTopCard());
            }
        }
    }

    public void playAgain(){
        // reset round number, roundDraw and gameWon
        roundNumber = 1;
        roundDraw = false;
        gameWon = false;

        // return cards to dealer
        returnCardsToDealer();

        // shuffle the deck
        dealer.shuffle();

        // the deck's cards are dealt among the players - at least 1 per player
        // if the deck is not divisible evenly by the number of players then dealing stops
        numUnusedCards = dealer.getDeckSize() % getNumPlayers();
        while (dealer.getDeckSize() > numUnusedCards){
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
                category = getCPUBestCategory();
            }
            playRound(category);
        }
        winner.addWin();
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
        this.category = category;

        // set the value to beat
        valueToBeat = getCardCategoryValue(currentBestCard, this.category);

        // each player's top card is added to pile
        addToPile();

        // compareValues by comparing topCard category values
        compareValues();

        // if round won, add cards to round winner's hand from pile
        if (checkRoundWin()){
            winnerTakePileCards();
        }

        // check win
        checkWin();

        // next turn
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

    public String getCPUBestCategory(){
        Card card = dealer.getTopCard(); // change this to if statement to check if player is a bot
        cpuBestCategory.clear();
        Integer cpuCategoryValue = -1;

        HashMap<String, Integer> categoryValues = new HashMap<>();
        categoryValues.put("Intellect", card.getIntellect());
        categoryValues.put("Lethality", card.getLethality());
        categoryValues.put("Morality", card.getMorality());
        categoryValues.put("How Schwifty", card.getHowSchwifty() * 10);

        for (String category : categoryValues.keySet()){
            Integer value = categoryValues.get(category);

            if (value > cpuCategoryValue) {
                // clear if the value is higher than the current highest category value
                cpuBestCategory.clear();
                cpuCategoryValue = value;
                cpuBestCategory.add(category);
            } else if (value.equals(cpuCategoryValue)){
                cpuBestCategory.add(category);
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
        // cardsToBeat - if card value is more than highest value then added to HashMap and previous highest removed, if equal it is added and there is a draw scenario, else move to next card
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
            roundDraw = false;
            for (Card card : cardsToBeat.keySet()){
                roundWinner = cardsToBeat.get(card);
                roundWinningCard = card;
                roundWinningScore = getCardCategoryValue(card, category);
                return true;
            }
        }
        roundDraw = true;
        return false;
    }

    public void winnerTakePileCards(){
        for (Card card : pile.keySet()){
            roundWinner.addToHand(card);
        }
        pile.clear();
    }

    public void checkWin(){
        // the winner is the player who obtains all the cards in play
        int numCardsInPlay = totalNumCards - numUnusedCards;

        for (Playable player : players){
            if (player.getNumCards() + getPileSize() == numCardsInPlay){
                Playable winningPlayer = player;
                gameWon = true;
                winner = winningPlayer;
                winningPlayer.addWin();
            }
        }
    }

    public void changeTurn(){
        playerTurn = roundWinner;
    }

    public void returnCardsToDealer(){
        // cards returned to dealer's deck
        ArrayList <Card> toMove = new ArrayList<>();
        ArrayList<Card> winnerHand = winner.getHand();

        for (Card card : winnerHand){
            toMove.add(card);
        }

        for (Card card : toMove){
            dealer.addToDeck(card);
            winner.removeFromHand(card);
        }
        toMove.clear();
    }

}
