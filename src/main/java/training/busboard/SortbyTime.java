package training.busboard;

import java.util.Comparator;

class SortbyTime<T> implements Comparator<T>
{
	public int compare(T bus1, T bus2)
	{
        Bus a = (Bus) bus1;
        Bus b = (Bus) bus2;
        return (int)(a.getTimeToStation() - b.getTimeToStation());
	}
}
