/*
  Simple Solitaire Card Game in Java
  Written by Tim Budd, Oregon State University, 1996
  
  Modified by Laurentiu Cristofor, UMass Boston, 1998
  */

import java.awt.*;
import java.awt.event.*;
import java.applet.*;

////////////////////////
// Defines a Link class
//
// used by LinkedList
////////////////////////
class Link
{
  private Object valueField;
  private Link   nextLink;

  public Link (Object newValue, Link next)
  {
    valueField = newValue;
    nextLink   = next;
  }
  
  // get Object part of link
  public Object value()
  {
    return valueField;
  }

  // get Link part of link
  public Link next()
  {
    return nextLink;
  }
}

//////////////////////////////
// Defines a LinkedList class
//
// used by CardPile
//////////////////////////////
class LinkedList
{
  private Link firstLink;

  public LinkedList()
  {
    firstLink = null;
  }

  // true if empty, false otherwise
  public boolean empty()
  {
    return firstLink == null;
  }
  
  // add an Object to our list
  public void add (Object newValue)
  {
    firstLink = new Link(newValue, firstLink);
  }

  // inspect front of list
  public Object front()
  {
    if (firstLink == null)
      return null;

    return firstLink.value();
  }

  // pop front of list
  public Object pop()
  {
    if (firstLink == null)
      return null;

    Object result = firstLink.value();
    firstLink = firstLink.next();
    return result;
  }

  // return an iterator for the list
  public ListIterator iterator()
  {
    return new ListIterator(firstLink);
  }
}

////////////////////////////////
// Defines a ListIterator class
//
// used by LinkedList
////////////////////////////////
class ListIterator
{
  private Link currentLink;
  
  public ListIterator (Link firstLink)
  {
    currentLink = firstLink;
  }

  // true if we reached end of list, false otherwise
  public boolean atEnd()
  {
    return currentLink == null;
  }

  // move to next link
  public void next()
  {
    if (currentLink != null)
      currentLink = currentLink.next();
  }

  // return value of current link
  public Object current()
  {
    if (currentLink == null)
      return null;

    return currentLink.value();
  }
}

////////////////////////
// Defines a Card class
//
// used by CardPile
////////////////////////
class Card 
{
  // data fields for colors and suits
  final static int width   = 50;
  final static int height  = 70;

  final static int red     = 0;
  final static int black   = 1;

  final static int heart   = 0;
  final static int spade   = 1;
  final static int diamond = 2;
  final static int club    = 3;

  final static int ace     = 0;
  final static int king    = 12;

  private static String names[] = {"A", "2", "3", "4", "5", "6",
				   "7", "8", "9", "10", "J", "Q", "K"};

  // data fields
  private boolean faceup;
  private int rank;
  private int suit;

  // constructor
  Card (int s, int r) 
  {
    suit = s;
    rank = r;
    faceup = false;
  }

  // get rank of card as an int in the interval [0, 12]
  public int rank() 
  { 
    return rank; 
  }
  
  // get suit of card as an int in the interval [0, 3]
  public int suit() 
  { 
    return suit; 
  }

  // true if card is face up, false otherwise
  public boolean faceUp() 
  { 
    return faceup; 
  }

  // change value of faceup
  public void flip() 
  { 
    faceup = ! faceup; 
  }

  // true if card is ace, false otherwise
  public boolean isAce()
  {
    return rank == ace;
  }

  // true if card is king, false otherwise
  public boolean isKing()
  {
    return rank == king;
  }

  // return color of card as an int in the range [0,1]
  public int color() 
  {
    if (suit() == heart || suit() == diamond)
      return red;

    return black;
  }

  // draw the card
  public void draw (Graphics g, int x, int y) {
    // clear rectangle, draw border
    g.clearRect(x, y, width, height);
    g.setColor(Color.black);
    g.drawRect(x, y, width, height);

    // draw body of card
    if (faceUp()) 
      {
	if (color() == red)
	  g.setColor(Color.red);
	else
	  g.setColor(Color.black);

	g.drawString(names[rank()], x+3, y+15);

	if (suit() == heart) 
	  {
	    g.drawLine(x+25, y+30, x+35, y+20);
	    g.drawLine(x+35, y+20, x+45, y+30);
	    g.drawLine(x+45, y+30, x+25, y+60);
	    g.drawLine(x+25, y+60, x+5, y+30);
	    g.drawLine(x+5, y+30, x+15, y+20);
	    g.drawLine(x+15, y+20, x+25, y+30);
	  }
	else if (suit() == spade) 
	  {
	    g.drawLine(x+25, y+20, x+40, y+50);
	    g.drawLine(x+40, y+50, x+10, y+50);
	    g.drawLine(x+10, y+50, x+25, y+20);
	    g.drawLine(x+23, y+45, x+20, y+60);
	    g.drawLine(x+20, y+60, x+30, y+60);
	    g.drawLine(x+30, y+60, x+27, y+45); 
	  }
	else if (suit() == diamond) 
	  {
	    g.drawLine(x+25, y+20, x+40, y+40);
	    g.drawLine(x+40, y+40, x+25, y+60);
	    g.drawLine(x+25, y+60, x+10, y+40);
	    g.drawLine(x+10, y+40, x+25, y+20);
	  }
	else if (suit() == club) 
	  {
	    g.drawOval(x+20, y+25, 10, 10);
	    g.drawOval(x+25, y+35, 10, 10);
	    g.drawOval(x+15, y+35, 10, 10);
	    g.drawLine(x+23, y+45, x+20, y+55);
	    g.drawLine(x+20, y+55, x+30, y+55);
	    g.drawLine(x+30, y+55, x+27, y+45); 
	  }
      }
    else // face down 
      {
	g.setColor(Color.yellow);
	g.drawLine(x+15, y+5, x+15, y+65);
	g.drawLine(x+35, y+5, x+35, y+65);
	g.drawLine(x+5, y+20, x+45, y+20);
	g.drawLine(x+5, y+35, x+45, y+35);
	g.drawLine(x+5, y+50, x+45, y+50);
      }
  }
}

//////////////////////////////////////
// Defines a CardPile class
//
// used as a base to build card piles
//////////////////////////////////////
class CardPile 
{
  // coordinates of the card pile
  protected int x;
  protected int y;

  // linked list of cards
  protected LinkedList cardList;

  CardPile (int xl, int yl) 
  {
    x = xl;
    y = yl;
    cardList = new LinkedList();
  }

  /////////////////////////////////////
  // access to cards are not overridden
  
  // true if pile is empty, false otherwise
  public final boolean empty() 
  { 
    return cardList.empty();
  }

  // inspect card at the top of pile
  public final Card top() 
  { 
    return (Card)cardList.front();
  }

  // pop card at the top of pile
  public final Card pop() 
  {
    return (Card)cardList.pop();
  }

  /////////////////////////////////////////
  // the following are sometimes overridden

  // true if point falls inside pile, false otherwise
  public boolean includes (int tx, int ty) 
  {
    return x <= tx && tx <= x + Card.width &&
           y <= ty && ty <= y + Card.height;
  }
        
  // to be overridden by descendants
  public void select (int tx, int ty) 
  {
    // do nothing
  }

  // add a card to pile
  public void addCard (Card aCard) 
  {
    cardList.add(aCard);
  }

  // draw pile
  public void display (Graphics g) 
  {
    g.setColor(Color.black);

    if (cardList.empty())
      g.drawRect(x, y, Card.width, Card.height);
    else
      top().draw(g, x, y);
  }

  // to be overridden by descendants
  public boolean canTake (Card aCard) 
  {
    return false; 
  }

  // get number of cards in pile
  public int getNoCards()
  {
    int count = 0;
    ListIterator iterator = cardList.iterator();

    while (!iterator.atEnd())
      {
	count++;
	iterator.next();
      }

    return count;
  }
}

////////////////////////////
// Defines a DeckPile class
////////////////////////////
class DeckPile extends CardPile 
{
  DeckPile (int x, int y) 
  {
    // first initialize parent
    super(x, y);

    // then create the new deck
    // first put them into a local pile
    CardPile pileOne = new CardPile(0, 0);
    CardPile pileTwo = new CardPile(0, 0);

    int count = 0;
    for (int i = 0; i < 4; i++)
      for (int j = 0; j <= 12; j++) 
	{
	  pileOne.addCard(new Card(i, j));
	  count++;
	}

    // then pull them out randomly
    for (; count > 0; count--) 
      {
	int limit = ((int)(Math.random() * 1000)) % count;

	// move down to a random location in pileOne
	// while poping the cards into pileTwo
	for (int i = 0; i < limit; i++)
	  pileTwo.addCard(pileOne.pop());

	// then add the card found there
	// to our LinkedList object: cardList
	addCard(pileOne.pop());

	// now put back into pileOne the cards
	// that we poped into pileTwo
	while (!pileTwo.empty())
	  pileOne.addCard(pileTwo.pop());
    }
  }
  
  public void select(int tx, int ty) 
  {
    // if deck becomes empty, refill from discard pile
    if (empty())
      {
	while (!Solitaire.discardPile.empty())
	  {
	    addCard(Solitaire.discardPile.pop());
	    top().flip();
	  }
      }
    else
      Solitaire.discardPile.addCard(pop());
  }
}

///////////////////////////////
// Defines a DiscardPile class
///////////////////////////////
class DiscardPile extends CardPile 
{
  DiscardPile (int x, int y) 
  { 
    super (x, y); 
  }
  
  public void addCard (Card aCard) 
  {
    if (!aCard.faceUp())
      aCard.flip();

    super.addCard(aCard);
  }

  public void select (int tx, int ty) 
  {
    if (empty())
      return;
            
    Card topCard = top();

    // check the SuitPile's first
    for (int i = 0; i < Solitaire.no_suit_piles; i++)
      if (Solitaire.suitPile[i].canTake(topCard)) 
	{
	  Solitaire.suitPile[i].addCard(pop());
	  return;
	}

    // then check the TablePile's
    for (int i = 0; i < Solitaire.no_table_piles; i++)
      if (Solitaire.tableau[i].canTake(topCard)) 
	{
	  Solitaire.tableau[i].addCard(pop());
	  return;
	}
  }
}

////////////////////////////
// Defines a SuitPile class
////////////////////////////
class SuitPile extends CardPile 
{
  SuitPile (int x, int y) 
  { 
    super(x, y); 
  }

  public boolean canTake (Card aCard) 
  {
    if (empty())
      return aCard.isAce();

    Card topCard = top();
    return (aCard.suit() == topCard.suit()) &&
           (aCard.rank() == 1 + topCard.rank());
  }

  public void select (int tx, int ty)   
  {
    if (empty())
      return;

    Card topCard = top();

    // check the TablePile's
    for (int i = 0; i < Solitaire.no_table_piles; i++)
      if (Solitaire.tableau[i].canTake(topCard)) 
	{
	  Solitaire.tableau[i].addCard(pop());
	  return;
	}  
  }
}

/////////////////////////////
// Defines a TablePile class
/////////////////////////////
class TablePile extends CardPile 
{
  final static int ydist = 25;

  TablePile (int x, int y, int c) 
  {
    // initialize the parent class
    super(x, y);

    // then initialize our pile of cards
    for (int i = 0; i < c; i++) 
      addCard(Solitaire.deckPile.pop());

    // flip topmost card face up
    top().flip();
  }

  public boolean canTake (Card aCard) 
  {
    if (empty())
      return aCard.isKing();

    Card topCard = top();

    // if our topmost card is face down
    // we can't accept another card
    if (!topCard.faceUp())
      return false;

    return (aCard.color() != topCard.color()) &&
           (aCard.rank() == topCard.rank() - 1);
  }

  public boolean includes (int tx, int ty) 
  {
    if (empty())
      return false;

    // don't test bottom of card
    return x <= tx && tx <= x + Card.width &&
           y <= ty;
  }

  public void select (int tx, int ty) 
  {
    if (empty())
      return;

    // if face down, then flip
    Card topCard = top();
    if (!topCard.faceUp()) 
      {
	topCard.flip();
	return;
      }
 
    // see if any suit pile can take card
    for (int i = 0; i < Solitaire.no_suit_piles; i++)
      if (Solitaire.suitPile[i].canTake(topCard))
	{
	  Solitaire.suitPile[i].addCard(pop());
	  return;
	}
 
    // try to create a build
    CardPile build = new CardPile(0, 0);

    // get the cards for the build from the suit pile
    while (!empty())
      {
	// stop if we reached a card that is face down
	if (!top().faceUp())
	  break;

	build.addCard(pop());
      }

    // We don't allow the user to play a King card
    // that is at the bottom of a table pile
    // to another table pile
    if (build.top().isKing() && empty())
      {
	while (!build.empty())
	  addCard(build.pop());
	return;
      }    

    // if we have to play only one card
    if (build.top() == topCard)
      {
	// put it back into the table pile
	addCard(build.pop());

	// we have already tried the suit piles
        // see if any other table pile can take card
        for (int i = 0; i < Solitaire.no_table_piles; i++)
          if (Solitaire.tableau[i].canTake(topCard))
            {
              Solitaire.tableau[i].addCard(pop());
              return;
            }
      }
    else // we got ourselves a build to play
      {
	topCard = build.top();

	// see if any other table pile can take this build
	for (int i = 0; i < Solitaire.no_table_piles; i++)
	  if (Solitaire.tableau[i].canTake(topCard))
	    {
	      while (!build.empty())
		Solitaire.tableau[i].addCard(build.pop());
	      
	      return;
	    }
	
	// can't play the build?
	// then we must restore our pile
	while (!build.empty())
	  addCard(build.pop());
      }
  }

  private void stackDisplay(Graphics g) 
  {
    // holds y-coordinate of cards in pile
    int localy = y; 

    LinkedList reverseCardList = new LinkedList();

    // get iterator for our list
    ListIterator iterator = cardList.iterator();

    // build reversed order list
    while (!iterator.atEnd())
      {
	reverseCardList.add(iterator.current());
	iterator.next();
      }

    // get iterator for reversed order list
    iterator = reverseCardList.iterator();

    // Go through the reversed order list
    // and draw each card in the list
    while (!iterator.atEnd())
      {
	((Card)iterator.current()).draw(g, x, localy);
	localy += ydist;
	iterator.next();
      }
  }

  public void display (Graphics g) 
  {
    stackDisplay(g);
  }
}

///////////////////////////
// Defines Solitaire class
///////////////////////////
public class Solitaire extends Applet 
{
  final static int no_card_piles  = 13;
  final static int no_suit_piles  = 4;
  final static int no_table_piles = 7;

  final static int topMargin      = 40;
  final static int leftMargin     = 5;
  final static int distTable      = 5;
  final static int distSuit       = 10;

  static DeckPile deckPile;
  static DiscardPile discardPile;
  static TablePile tableau[];
  static SuitPile suitPile[];
  static CardPile allPiles[];

  protected void initialize()
  {
    // first allocate the arrays
    allPiles = new CardPile[no_card_piles];
    suitPile = new SuitPile[no_suit_piles];
    tableau  = new TablePile[no_table_piles];

    // then fill them in
    int xDeck = leftMargin + (no_table_piles - 1) * (Card.width + distTable);
    allPiles[0] = deckPile = new DeckPile(xDeck, topMargin);
    allPiles[1] = discardPile = new DiscardPile(xDeck - Card.width - distSuit, 
						topMargin);

    for (int i = 0; i < no_suit_piles; i++)
      allPiles[2+i] = suitPile[i] =
	new SuitPile(leftMargin + (Card.width + distSuit) * i, topMargin);

    for (int i = 0; i < no_table_piles; i++)
      allPiles[6+i] = tableau[i] =
	new TablePile(leftMargin + (Card.width + distTable) * i, 
		      Card.height + distTable + topMargin, i+1); 

    showStatus("Welcome to Solitaire!");
  }

  protected boolean gameEnded()
  {
    if (!deckPile.empty())
      return false;

    if (!discardPile.empty())
      return false;

    for (int i = 0; i < no_table_piles; i++)
      if (!tableau[i].empty())
	return false;

    // if we reached this point then all piles are empty
    // notify the user in the status bar
    showStatus("Congratulations! You have won this game.");

    return true;
  }

  public void init() 
  {
    // Create a new game button
    Button b = new Button("New game");

    // Define, instantiate, and register a listener to handle button presses
    b.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) 
	{  // start a new game
	  initialize();
	  repaint();
	}
    });

    // Add the button to the applet
    add(b);

    // Define, instantiate and register a MouseListener object.
    addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) 
	{
	  if (gameEnded())
	    return;
	  
	  showStatus("Neat click!");

	  int x = e.getX();
	  int y = e.getY(); 

	  for (int i = 0; i < no_card_piles; i++)
	    if (allPiles[i].includes(x, y))
	      {
		allPiles[i].select(x, y);
		repaint();
	      }
	} 
    }); 

    initialize();
  }

  public void paint(Graphics g) 
  {
    for (int i = 0; i < no_card_piles; i++)
      allPiles[i].display(g);
  }
}