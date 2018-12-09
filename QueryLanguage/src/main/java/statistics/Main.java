package statistics;

import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        Statistics stats = new Statistics(new PlayerReaderImpl("http://nhlstats-2013-14.herokuapp.com/players.txt"));
          
        Matcher m = new And( new HasAtLeast(10, "goals"),
                             new HasAtLeast(10, "assists"),
                             new PlaysIn("PHI")
        );

        for (Player player : stats.matches(m)) {
            System.out.println( player );
        }

        System.out.println("-----------------------------------------------");

        Matcher ma = new Or( new HasAtLeast(40, "goals"),
                new HasAtLeast(60, "assists"),
                new HasAtLeast(85, "points")
        );

        for (Player player : stats.matches(ma)) {
            System.out.println( player );
        }

        System.out.println("-----------------------------------------------");

        Matcher mat = new All( new HasAtLeast(40, "goals"),
                new HasAtLeast(60, "assists"),
                new HasAtLeast(85, "points")
        );

        /*for (Player player : stats.matches(mat)) {
            System.out.println( player );
        }*/



        Matcher matc = new Not( new HasAtLeast(10, "goals"),
                new HasAtLeast(10, "assists"),
                new PlaysIn("PHI"),
                new PlaysIn("FLA")
        );

        for (Player player : stats.matches(matc)) {
            System.out.println( player );
        }

        System.out.println("-----------------------------------------------");

        Matcher match = new Not( new HasAtLeast(1, "goals") );

        for (Player player : stats.matches(match)) {
            System.out.println( player );
        }

        System.out.println("-----------------------------------------------");

        Matcher matche = new HasFewerThan(1, "goals");

        for (Player player : stats.matches(matche)) {
            System.out.println( player );
        }
    }
}
