package training.busboard;

import java.util.Comparator;

class SortbyDistance<T> implements Comparator<T>
{
	public int compare(T busStop1, T busStop2)
	{
        BusStop a = (BusStop) busStop1;
        BusStop b = (BusStop) busStop2;
        return (int)(a.getDistance() - b.getDistance());
	}
}
