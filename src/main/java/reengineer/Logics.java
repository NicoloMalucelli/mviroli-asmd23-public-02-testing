package reengineer;

import java.util.Set;

public interface Logics{
	
	enum HitType {
		FIRST, SECOND
	}
	
	HitType hit(int x, int y);
	
	boolean isSelected(int x, int y);
	
	boolean isOver();
    
}
