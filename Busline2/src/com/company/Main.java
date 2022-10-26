package com.company;

//public class Main {
//
//    public static void main(String[] args)
//    {
//        var b = new BusLine();
////        b.addBusLine("a", new Position2D(1, 1), new Position2D(7, 7));
////        b.addLineSegment("a", new LineSegment(new Position2D(7, 1), new Position2D(2, 6))); // 3
////        b.addLineSegment("a", new LineSegment(new Position2D(7, 7), new Position2D(7, 1))); // 2
////        b.addBusLine("b", new Position2D(0, 2), new Position2D(4, 6));
////        b.addLineSegment("b", new LineSegment(new Position2D(0, 11), new Position2D(3, 8)));
//        b.addBusLine("c", new Position2D(1, 1), new Position2D(4, 2));
//        b.addLineSegment("c", new LineSegment(new Position2D(1, 1), new Position2D(1, 4)));
//        b.addLineSegment("c", new LineSegment(new Position2D(1, 4), new Position2D(4, 4)));
//        b.addLineSegment("c", new LineSegment(new Position2D(4, 4), new Position2D(7, 4)));
//        b.addLineSegment("c", new LineSegment(new Position2D(7, 4), new Position2D(8, 4)));
//        b.addLineSegment("c", new LineSegment(new Position2D(8, 4), new Position2D(8, 2)));
//        b.addLineSegment("c", new LineSegment(new Position2D(8, 2), new Position2D(4, 2)));
////        b.addBusLine("d", new Position2D(4, 6), new Position2D(6, 4));
////        b.addLineSegment("d", new LineSegment(new Position2D(5, 5), new Position2D(6, 0)));
////        b.addBusLine("a", new Position2D(5, 5), new Position2D(4, 4));
////        b.addLineSegment("a", new LineSegment(new Position2D(4, 4), new Position2D(7, 7)));
////        b.addBusLine("b", new Position2D(2, 6), new Position2D(4, 4));
////        b.addLineSegment("b", new LineSegment(new Position2D(4, 4), new Position2D(7, 1)));
//
//        b.findIntersections();
//        System.out.println(b.getLines());
//        System.out.println("\n");
//        System.out.println(b.getIntersectionPositions());
//        System.out.println("\n");
//        System.out.println(b.getIntersectionsWithLines());
//        System.out.println("\n");
//        System.out.println(b.getIntersectionOfLinesPair());
//    }
//}
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    static int errorCount = 0;

    public static void main(String[] args) {
        var b = new BusLine();
        b.addBusLine("a", new Position2D(1, 1), new Position2D(2, 6));
        b.addLineSegment("a", new LineSegment(new Position2D(1, 1), new Position2D(7, 7))); // 1
        b.addLineSegment("a", new LineSegment(new Position2D(7, 1), new Position2D(2, 6))); // 3
        b.addLineSegment("a", new LineSegment(new Position2D(7, 7), new Position2D(7, 1))); // 2
        b.addBusLine("b", new Position2D(4, 7), new Position2D(7, 7));
        b.addLineSegment("b", new LineSegment(new Position2D(4, 7), new Position2D(7, 7)));
        b.addBusLine("c", new Position2D(1, 1), new Position2D(4, 2));
        b.addLineSegment("c", new LineSegment(new Position2D(1, 1), new Position2D(1, 4))); // 0
        b.addLineSegment("c", new LineSegment(new Position2D(1, 4), new Position2D(4, 4))); // 1
        b.addLineSegment("c", new LineSegment(new Position2D(4, 4), new Position2D(8, 4))); // 2
        b.addLineSegment("c", new LineSegment(new Position2D(8, 4), new Position2D(8, 2))); // 3
        b.addLineSegment("c", new LineSegment(new Position2D(8, 2), new Position2D(4, 2))); // 4
//        b.addLineSegment("c", new LineSegment(new Position2D(100, 100), new Position2D(102, 102))); // 5
//        b.addLineSegment("c", new LineSegment(new Position2D(102, 102), new Position2D(104, 104)));
////        b.addLineSegment("c", new LineSegment(new Position2D(104, 104), new Position2D(106, 106)));
//        b.addLineSegment("c", new LineSegment(new Position2D(106, 106), new Position2D(108, 108)));
//        b.addLineSegment("c", new LineSegment(new Position2D(108, 108), new Position2D(110, 110)));
        b.findIntersections();

        // getLines
        Map<String, List<Position>> expLines = new HashMap<String, List<Position>>();
        expLines.put("a", List.of(new Position2D(1, 1), new Position2D(2, 2), new Position2D(3, 3), new Position2D(4, 4),
                new Position2D(5, 5), new Position2D(6, 6), new Position2D(7, 7), new Position2D(7, 6), new Position2D(7, 5),
                new Position2D(7, 4), new Position2D(7, 3), new Position2D(7, 2), new Position2D(7, 1), new Position2D(6, 2),
                new Position2D(5, 3), new Position2D(4, 4), new Position2D(3, 5), new Position2D(2, 6)));
        expLines.put("c", List.of(new Position2D(1, 1), new Position2D(1, 2), new Position2D(1, 3), new Position2D(1, 4),
                new Position2D(2, 4), new Position2D(3, 4), new Position2D(4, 4), new Position2D(5, 4), new Position2D(6, 4),
                new Position2D(7, 4), new Position2D(8, 4), new Position2D(8, 3), new Position2D(8, 2), new Position2D(7, 2),
                new Position2D(6, 2), new Position2D(5, 2), new Position2D(4, 2)));
        if (!expLines.equals(b.getLines())) {
            System.err.println("error: getLines invalid");
            errorCount++;
        }

        // getIntersectionPositions
        Map<String, List<Position>> expPositions = new HashMap<String, List<Position>>();
        expPositions.put("a",
                List.of(new Position2D(4, 4), new Position2D(7, 4), new Position2D(7, 2), new Position2D(4, 4)));
        expPositions.put("c", List.of(new Position2D(7, 4), new Position2D(7, 2)));
        if (!expPositions.equals(b.getIntersectionPositions())) {
            System.err.println("error: getIntersectionPositions invalid");
            errorCount++;
        }

        // getIntersectionsWithLines
        Map<String, List<String>> expInter = new HashMap<String, List<String>>();
        expInter.put("a", List.of("a", "c", "c", "a"));
        expInter.put("c", List.of("a", "a"));
        if (!expInter.equals(b.getIntersectionsWithLines())) {
            System.err.println("error: getIntersectionsWithLines invalid");
            errorCount++;
        }



        // getIntersectionOfLinesPair
        Map<BusLineInterface.LinesPair, Set<Position>> expPair = new HashMap<BusLineInterface.LinesPair, Set<Position>>();
        Map<BusLineInterface.LinesPair, Set<Position>> tests = b.getIntersectionOfLinesPair();
        expPair.put(b.new LinesPair("b", "a"), new HashSet<Position>(List.of()));
        expPair.put(b.new LinesPair("c", "b"), new HashSet<Position>(List.of()));
        expPair.put(b.new LinesPair("a", "a"), new HashSet<Position>(List.of(new Position2D(4, 4))));
        expPair.put(b.new LinesPair("b", "b"), new HashSet<Position>(List.of()));
        expPair.put(b.new LinesPair("c", "c"), new HashSet<Position>(List.of()));
        expPair.put(b.new LinesPair("a", "b"), new HashSet<Position>(List.of()));
        expPair.put(b.new LinesPair("b", "c"), new HashSet<Position>(List.of()));
        expPair.put(b.new LinesPair("a", "c"), new HashSet<Position>(List.of(new Position2D(7, 2), new Position2D(7, 4))));
        expPair.put(b.new LinesPair("c", "a"), new HashSet<Position>(List.of(new Position2D(7, 2), new Position2D(7, 4))));

        if (expPair.size() != tests.size()) {
            System.err.println("error: getIntersectionOfLinesPair invalid");
            errorCount++;
        } else {
            boolean yes = true;
            for (BusLineInterface.LinesPair para : expPair.keySet()) {
                for (BusLineInterface.LinesPair test : tests.keySet()) {
                    if (para.getFirstLineName().equals(test.getFirstLineName()) && para.getSecondLineName().equals(test.getSecondLineName())) {
                        if (!expPair.get(para).equals(tests.get(test))) {
                            yes = false;
                        }
                    }
                }
            }
            if (!yes) {
                System.err.println("error: getIntersectionOfLinesPair invalid");
                errorCount++;
            }
        }
        System.out.println(b.getIntersectionOfLinesPair());
        System.out.println(expPair);
        System.out.println(b.getLines());
        System.out.println(expLines);
        System.out.println(b.getIntersectionPositions());
        System.out.println(expPositions);
        System.out.println(b.getIntersectionsWithLines());
        System.out.println(expInter);
        if (errorCount == 0)
            System.out.println("all tests passed");
    }
}