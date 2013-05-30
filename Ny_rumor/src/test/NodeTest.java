package test;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import ou3.Event;
import ou3.Network;
import ou3.Node;
import ou3.Route;

public class NodeTest {

	private Node node;
	
	@Before 
	public void setupNetwork()
	{
        Node.setAgentProbability((float) 0.5 );
        Node.setEventProbability((float)  0.0001 );
		this.node = new Node( 3, 3 );

	}
	
	@Test
	public void CreateEventAndAgentTest()
	{
		for(int i = 0; i<10000*5*2; i++)
		{
			this.node.tryCreateEvent(i);
		}
		int total = this.node.getEvents().size();
		assertTrue("Is the totalt number of events as expected?",total > 5*1 && total < 5*3);
		int CurrentID = 0;
        for ( Event e : this.node.getEvents())
        {
        	// Is the id incrementet in correct order?
        	assertTrue("Is the event id incremeted based on a new event?:",e.getID() == CurrentID++);
        	// Is the event in the routing table?
        	assertTrue("Is the event in the routing table?:",this.node.getRoutingTable().containsKey(e.getID()));
        }
	}
	
	
	
	
	
	
	
	
}
