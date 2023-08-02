package main;

import java.util.Comparator;

public class totalExecTimeComparator implements Comparator<Process>{

    @Override
    public int compare(Process p1, Process p2) {
        return Integer.compare(p1.getTotalExecTime(), p2.getTotalExecTime());
    }
    
}
