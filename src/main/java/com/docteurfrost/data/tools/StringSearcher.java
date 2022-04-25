package com.docteurfrost.data.tools;

import java.util.ArrayList;
import java.util.List;

public class StringSearcher {
	
	public List<Integer> indexesOf( String target, String source ) {
		
		List<Integer> indexes = new ArrayList<>();
        int index = 0;
        while(index != -1){
        	index = source.indexOf( target, index);
            if (index != -1) {
            	indexes.add(index);
                index++;
            }
        }
        
        return indexes;      
	}

}
