# Blackjack Console Game


****Project Objective****

The objective of this project is to create a console-based blackjack game for a video game development company. This blackjack game should automate the game's business logic, allowing players to play against the computer.

****Card Representation****

In a deck of cards, each card is characterized by its rank (1 for Ace, 2 to 10 for numeric cards, 11 for Jack, 12 for Queen, 13 for King) and its suit (1 for Diamonds, 2 for Hearts, 3 for Spades, and 4 for Clubs).

[12, 3]: Queen of Spades
[5, 1]: 5 of Diamonds
Creating a Deck of Cards
We want to create a deck of 52 cards, starting with the Ace of Diamonds. To achieve this, we need to:

Implement a method that constructs the list of cards following the order of suits (Diamonds, Hearts, Spades, Clubs).
Create a method to create the deck of 52 cards as pairs of values.


****Shuffling the Deck****

To shuffle the deck, we randomly choose an index i and extract the card at that index to build a new shuffled deck. Three methods are provided:

extraire_ieme_carte: Extracts the i-th card from a given list of cards.
tirer_une_carte: Randomly draws a card from a list of cards.
melanger_jeu_cartes: Shuffles the cards in a list.

****Drawing and Discarding Cards****

Drawing cards is different from randomly drawing cards; it involves a deck of cards. The two methods for this are:

piocher_n_cartes: Draws the first n cards from a list of cards.
defausser_cartes: Takes two lists of cards (the deck and the cards to discard) and returns the updated deck with discarded cards placed at the end.

****Blackjack Rules****

In a game of blackjack, the player competes against the dealer (bank). The game starts with the dealer drawing one card and giving two cards to the player. The objective is to get as close to 21 as possible without exceeding it. Card values are as follows: numeric cards retain their values, face cards are worth 10, and Aces can be 1 or 11.

The player can request additional cards as desired.
If the player exceeds 21, they lose.
The dealer draws cards until they reach or exceed 17.
If the dealer gets a lower score than the player or exceeds 21, the player wins.
If the dealer gets a higher score than the player, the player loses.
Otherwise, it's a tie.
