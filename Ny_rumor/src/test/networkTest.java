package test;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import ou3.Network;
import ou3.Node;

public class networkTest {

	private Network net;
	
	@Before 
	public void setupNetwork()
	{
		net = new Network( "H:\\git\\New folder\\klara\\Ny_rumor\\src\\configuration.xml" );
	}
	
	@Test
	public void isNetworkCreated()
	{
		for ( int x = 0 ; x < 50 ; x++ )
        {
			assertTrue("Could a network list be created as specified?:",net.getNetwork()[x].length == 50);
        }
	}
	
	@Test
	public void isNetworkNodesPositionCorrect()
	{
        for ( int x = 0 ; x < 50 ; x++ )
        {
            for ( int y = 0 ; y < 50; y++ )
            {
            	Node n = this.net.getNetwork()[x][y];
            	n.getPoint();
            	assertTrue("Is the nodes in the network list in order?",n.getPoint().getX() == x && n.getPoint().getY() == y);
            }
        }
	}
	
	@Test
	public void isNeigbourlistCorrect()
	{
        for ( int x = 0 ; x < 50 ; x++ )
        {
            for ( int y = 0 ; y < 50; y++ )
            {
            	Node orgin  = this.net.getNetwork()[x][y];
            	Node[] neighbours =orgin.getNeighbours();
            	// Check if distance is correct
            	for(Node n : neighbours)
            	{
            		assertNotNull( n );
            		double distance = orgin.getPoint().distance(n.getPoint());
            		assertTrue(distance < 1.5);
            	}
            	// Check if correct number of neighburs for each node was created
            	// If first or last row then
            	if(y == 0 || y == 49){
            		// If first or last colum
            		if(x == 0 || x == 49) 
            			assertTrue(neighbours.length == 3);
            		else 
            			assertTrue(neighbours.length == 5);
            	}
            	else
            	{
            		// If first colum or last
            		if(x == 0 || x == 49) 
            			assertTrue(neighbours.length == 5);
            		else 
            			assertTrue(neighbours.length == 8);
            	}
            	
            }
        }
	}
	
	@Test
	public void isQueryNodesCreated()
	{
		assertTrue("Is the total number of query nodes generated equal to 4?:" ,net.getQueryNodes().length == 4);
		for(Node n : net.getQueryNodes())
		{
			assertNotNull("Is the nodes in the list not null?",n );
		}
	}
	
	
	
	
	
	
}
