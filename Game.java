import java.util.LinkedList;

public class Game {
    private LinkedList<Player> players;
    private Deck discards;
    private Deck unused;

    public Game(int numberOfPlayers)
    {
        discards = new Deck(true);
        unused = new Deck(false);
        unused.shuffle();
        players = new LinkedList<Player>();
        for (int i = 0; i < numberOfPlayers; i++)
        {
            Player p = new Player();
            for (int j = 0; j < 7; j++) 
                p.addCard(unused.getTop());
            players.add(p);
        }
        discards.addCard(unused.getTop());
    }

    private boolean isLegalMove(Card c)
    {
        Card top = discards.seeTop();
        if (top.num == 'T' && c.num != 'T') return false;
        if (top.num == 'F' && top.color == 'X' && c.num == 'F' && c.color == 'X') return true;
        if (top.num == 'F' && top.color == 'X') return false;
        return top.num == c.num || top.color == c.color;

    }



    public void show()
    {
        System.out.print("Top card : ");
        System.out.println(discards.seeTop());
        System.out.println();

        System.out.println("Currecnt players hand");
        System.out.println(players.get(0));
    }

    public void takeTurn()
    {
        this.show();
        Player p = players.getFirst();
        if (p.canPlay(discards.seeTop()))
        {
            Card c = p.removeCard();
            if (this.isLegalMove(c))
            {
                discards.addCard(c);
            }
            else
            {
                p.addCard(c);
                p.addCard(unused.getTop());
            }
            players.add(players.removeFirst());
        }
    }

    


}