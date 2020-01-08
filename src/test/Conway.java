package test;

import java.util.Random;

import javafx.scene.paint.Color;

public class Conway {
	
	private boolean[] oldFrame;
	private boolean[] newFrame;
	private int size;
	
	public Conway(int size) {
		
		Random generator = new Random();
		
		this.size = size;
		
		this.oldFrame = new boolean[size*size];
		this.newFrame = new boolean[size*size];
		
		for(int i=0; i<size*size; i++) {
			this.oldFrame[i] = (generator.nextInt(5) == 1);
		}
	}

	public boolean[] getOldFrame() {
		return oldFrame;
	}

	public void setOldFrame(boolean[] oldFrame) {
		this.oldFrame = oldFrame;
	}

	public boolean[] getNewFrame() {
		return newFrame;
	}

	public void setNewFrame(boolean[] newFrame) {
		this.newFrame = newFrame;
	}
	
	public void nextStep() {
		boolean cellState;
		boolean newCellState;
		int cellAdjCount;
		
		for(int i=0; i<size; i++) {
			
			for (int j=0; j < size; j++) {
					cellState = oldFrame[i*size + j];
					cellAdjCount = 0;
					for(int x=-1; x<=1; x++) {
						for(int y=-1; y<=1; y++) {
							
							if((i+x < 0) || (i+x >= size))
								continue;
							if((j+y < 0) || (j+y >= size))
								continue;
							if((x == 0) && (y == 0))
								continue;
							cellAdjCount += (oldFrame[(i+x)*size + j+y]) ? 1 : 0;
						}
					}
					
					
					if(cellState) {
						newCellState = ((cellAdjCount > 3) || (cellAdjCount < 2)) ? false : true;
					}
					else {
						newCellState = (cellAdjCount == 3) ? true : false;
					}
					newFrame[i*size + j] = newCellState;
			}
		}
		for(int i=0; i<size*size; i++) {
			oldFrame[i] = newFrame[i];
		}
	}
	
}
