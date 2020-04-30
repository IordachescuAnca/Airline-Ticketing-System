package Comparator;

import Entities.Flight;

import java.util.Comparator;

public class SortByDate implements Comparator<Flight>{
    @Override
    public int compare(Flight o1, Flight o2) {
        return o1.getDateTo().compareTo(o2.getDateTo());
    }

}
