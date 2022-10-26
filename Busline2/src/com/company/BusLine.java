import java.util.*;

public class BusLine implements BusLineInterface
{
    class Bus
    {
        private String name;
        private Position start, end;
        private ArrayList<Line> lines = new ArrayList<>();
        private ArrayList<Position> wholeLine = new ArrayList<>();
        private ArrayList<Position> intt = new ArrayList<>();
        private ArrayList<String> intb = new ArrayList<>();

        public Bus(String busLineName, Position firstPoint, Position lastPoint) {
            name = busLineName;
            start = firstPoint;
            end = lastPoint;
        }

        public void sort()
        {
            ArrayList<Integer> a = new ArrayList<>();
            for (int i = 0; i < intt.size(); i++) {
                if (wholeLine.indexOf(intt.get(i)) != wholeLine.lastIndexOf(intt.get(i))) {
                    a.add(wholeLine.indexOf(intt.get(i)));
                    a.add(wholeLine.lastIndexOf(intt.get(i)));
                    i++;
                } else
                    a.add(wholeLine.indexOf(intt.get(i)));
            }
            for (int i = 0; i < intt.size() - 1; i++)
                for (int j = 0; j < intt.size() - 1; j++) {
                    if (a.get(j) > a.get(j + 1)) {
                        Collections.swap(a, j, j + 1);
                        Collections.swap(intt, j, j + 1);
                        Collections.swap(intb, j, j + 1);
                    }
                }
        }

        public void getWholeline()
        {
            ArrayList<Line> a = new ArrayList<>();
            for(int i=0; i<lines.size(); i++)
                if(lines.get(i).getFirstPosition().equals(start))
                    a.add(lines.get(i));

            while(a.size() != lines.size())
                for(int i=0; i<lines.size(); i++)
                    if(a.get(a.size()-1).getLastPosition().equals(lines.get(i).getFirstPosition()))
                        a.add(lines.get(i));
            lines = a;

            for (Line line : lines) wholeLine.addAll(line.points);
        }

        public void removeRep()
        {
            for(int i=0; i<wholeLine.size()-1; i++)
                if(wholeLine.get(i).equals(wholeLine.get(i+1)))
                    wholeLine.remove(i+1);
        }
    }

    ArrayList<Bus> busLines = new ArrayList<>();

    class Line extends LineSegment
    {
        private ArrayList<Position> points = new ArrayList<>();
        private int type;

        public int getType()
        {
            return type;
        }

        public Line(Position firstPosition, Position lastPosition)
        {
            super(firstPosition, lastPosition);

            int a = lastPosition.getCol() - firstPosition.getCol();
            int b = lastPosition.getRow() - firstPosition.getRow();
            if(a == 0)
            {
                if(b>0)
                {
                    for(int i = firstPosition.getRow(); i<= lastPosition.getRow(); i++)
                        points.add(new Position2D(firstPosition.getCol(), i));
                }
                else
                {
                    for(int i = firstPosition.getRow(); i >= lastPosition.getRow(); i--)
                        points.add(new Position2D(firstPosition.getCol(), i));
                }
                type = 1;
            }
            else if(b == 0)
            {
                if(a>0)
                {
                    for(int i = firstPosition.getCol(); i<= lastPosition.getCol(); i++)
                        points.add(new Position2D(i, firstPosition.getRow()));
                }
                else
                {
                    for(int i = firstPosition.getCol(); i >= lastPosition.getCol(); i--)
                        points.add(new Position2D(i, firstPosition.getRow()));
                }
                type = -1;
            }
            else if(Math.abs(a) == Math.abs(b))
            {
                if(b>0)
                {
                    if(a>0)
                    {
                        for(int i=0; i<=b; i++)
                            points.add(new Position2D(firstPosition.getCol() + i, firstPosition.getRow() + i));

                        type = 2;
                    }
                    else
                    {
                        for(int i=0; i<=b; i++)
                            points.add(new Position2D(firstPosition.getCol() - i, firstPosition.getRow() + i));

                        type = -2;
                    }
                }
                else
                {
                    if(a>0)
                    {
                        for(int i=0; i<=Math.abs(b); i++)
                            points.add(new Position2D(firstPosition.getCol() + i, firstPosition.getRow() - i));

                        type = -2;
                    }
                    else
                    {
                        for(int i=0; i<=Math.abs(b); i++)
                            points.add(new Position2D(firstPosition.getCol() - i, firstPosition.getRow() - i));

                        type = 2;
                    }
                }
            }
            else
            {
                type = 3;
            }
        }

    }

    class LinesPair implements BusLineInterface.LinesPair
    {
        private String name1, name2;
        LinesPair(String busline1, String busline2)
        {
            name1 = busline1;
            name2 = busline2;
        }

        @Override
        public String getFirstLineName() {
            return name1;
        }

        @Override
        public String getSecondLineName() {
            return name2;
        }
    }

    @Override
    public void addBusLine(String busLineName, Position firstPoint, Position lastPoint)
    {
        busLines.add(new Bus(busLineName, firstPoint, lastPoint));
    }

    @Override
    public void addLineSegment(String busLineName, LineSegment lineSegment)
    {
        for(int i=0; i<busLines.size(); i++)
        {
            if(busLines.get(i).name.equals(busLineName)) {
                Line l = new Line(lineSegment.getFirstPosition(), lineSegment.getLastPosition());
                if (l.getType() != 3) {
                    busLines.get(i).lines.add(l);
                    return;
                }
            }
        }
    }

    @Override
    public void findIntersections()
    {
        for (Bus line : busLines) line.getWholeline();
        for (Bus busLine : busLines) busLine.removeRep();

        for(int i=0; i<busLines.size(); i++)
            for(int j=0; j<busLines.size(); j++)
                for(int k=1; k<busLines.get(i).wholeLine.size()-1; k++) {
                    if(i == j) {
                        for(int l=k+1; l<busLines.get(j).wholeLine.size()-1; l++)
                            if(busLines.get(i).wholeLine.get(k).equals(busLines.get(j).wholeLine.get(l))) {
                                int a1 = busLines.get(i).wholeLine.get(k-1).getCol() - busLines.get(i).wholeLine.get(k+1).getCol();
                                int b1 = busLines.get(i).wholeLine.get(k-1).getRow() - busLines.get(i).wholeLine.get(k+1).getRow();
                                int a2 = busLines.get(j).wholeLine.get(l-1).getCol() - busLines.get(j).wholeLine.get(l+1).getCol();
                                int b2 = busLines.get(j).wholeLine.get(l-1).getRow() - busLines.get(j).wholeLine.get(l+1).getRow();
                                if((a1 != 0 && b1 == 0) && (a2 == 0 && b2 != 0) || ((a2 != 0 && b2 == 0) && (a1 == 0 && b1 != 0)) ||
                                        (a1 != 0 && a2 != 0 && b1 != 0 && b2 != 0 && Math.abs(a1) == Math.abs(a2) && (Math.abs(b1) == Math.abs(b2)))) {
                                    busLines.get(i).intt.add(busLines.get(i).wholeLine.get(k));
                                    busLines.get(j).intt.add(busLines.get(j).wholeLine.get(l));
                                    busLines.get(i).intb.add(busLines.get(j).name);
                                    busLines.get(j).intb.add(busLines.get(j).name);
                                }
                            }
                    }
                    else {
                        for(int l=1; l<busLines.get(j).wholeLine.size()-1; l++)
                            if(busLines.get(i).wholeLine.get(k).equals(busLines.get(j).wholeLine.get(l))) {
                                int a1 = busLines.get(i).wholeLine.get(k-1).getCol() - busLines.get(i).wholeLine.get(k+1).getCol();
                                int b1 = busLines.get(i).wholeLine.get(k-1).getRow() - busLines.get(i).wholeLine.get(k+1).getRow();
                                int a2 = busLines.get(j).wholeLine.get(l-1).getCol() - busLines.get(j).wholeLine.get(l+1).getCol();
                                int b2 = busLines.get(j).wholeLine.get(l-1).getRow() - busLines.get(j).wholeLine.get(l+1).getRow();
                                if((a1 != 0 && b1 == 0) && (a2 == 0 && b2 != 0) || ((a2 != 0 && b2 == 0) && (a1 == 0 && b1 != 0)) ||
                                        (a1 != 0 && a2 != 0 && b1 != 0 && b2 != 0 && Math.abs(a1) == Math.abs(a2) && (Math.abs(b1) == Math.abs(b2)))) {
                                    busLines.get(i).intt.add(busLines.get(i).wholeLine.get(k));
                                    busLines.get(i).intb.add(busLines.get(j).name);
                                }
                            }
                    }
                }
        for(int i=0; i< busLines.size(); i++)
            busLines.get(i).sort();
    }

    @Override
    public Map<String, List<Position>> getLines()
    {
        Map<String, List<Position>> map1 = new HashMap<>();
        for(int i=0; i<busLines.size(); i++)
            if(busLines.get(i).intt.size() > 0) {
                List<Position> l = new ArrayList<>();
                l.addAll(busLines.get(i).wholeLine);
                map1.put(busLines.get(i).name, l);
            }
        return map1;
    }

    @Override
    public Map<String, List<Position>> getIntersectionPositions()
    {
        Map<String, List<Position>> map1 = new HashMap<>();
        for(int i=0; i<busLines.size(); i++)
            if(busLines.get(i).intt.size() > 0) {
                List<Position> l = new ArrayList<>();
                l.addAll(busLines.get(i).intt);
                map1.put(busLines.get(i).name, l);
            }
        return map1;
    }

    @Override
    public Map<String, List<String>> getIntersectionsWithLines()
    {
        Map<String, List<String>> map1 = new HashMap<>();
        for(int i=0; i<busLines.size(); i++)
            if(busLines.get(i).intt.size() > 0) {
                List<String> l = new ArrayList<>();
                l.addAll(busLines.get(i).intb);
                map1.put(busLines.get(i).name, l);
            }
        return map1;

    }

    @Override
    public Map<BusLineInterface.LinesPair, Set<Position>> getIntersectionOfLinesPair()
    {
        Map<BusLineInterface.LinesPair, Set<Position>> map1 = new HashMap<>();

        for(int i=0; i<busLines.size(); i++)
            for(int j=0; j<busLines.size(); j++) {
                HashSet<Position> pos = new HashSet<>();
                for(int k=0; k<busLines.get(i).intt.size(); k++)
                    for(int l=0; l<busLines.get(j).intt.size(); l++) {
                        if(i == j) {
                            if(busLines.get(i).intt.indexOf(busLines.get(i).intt.get(k)) != busLines.get(i).intt.lastIndexOf(busLines.get(i).intt.get(k)))
                                pos.add(busLines.get(i).intt.get(k));
                        }
                        else {
                            if(busLines.get(i).intt.get(k).equals(busLines.get(j).intt.get(l)))
                                pos.add(busLines.get(i).intt.get(k));
                        }
                    }

                map1.put(new LinesPair(busLines.get(i).name, busLines.get(j).name), pos);
            }
        return map1;
    }
}
